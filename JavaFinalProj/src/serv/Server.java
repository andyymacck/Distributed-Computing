import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

@SpringBootApplication
public class FileSharingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileSharingServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, SharedFilesRepository sharedFilesRepository) {
        return (args) -> {
            User user = userRepository.save(new User("username", "password", "email"));

            // Share a file
            SharedFile sharedFile = new SharedFile(user, "file1.txt", "/path/to/file1.txt", true);
            sharedFilesRepository.save(sharedFile);

            // Remove the shared file
            sharedFilesRepository.delete(sharedFile);
        };
    }

    @RestController
    public class FileSearchController {

        private final SharedFilesRepository sharedFilesRepository;

        public FileSearchController(SharedFilesRepository sharedFilesRepository) {
            this.sharedFilesRepository = sharedFilesRepository;
        }

        @GetMapping("/searchFiles")
        public List<SharedFile> searchFiles(@RequestParam String fileName) {
            return sharedFilesRepository.findByFileNameContaining(fileName);
        }
    }

    @RestController
    public class FileDownloadController {

        private final SharedFilesRepository sharedFilesRepository;

        public FileDownloadController(SharedFilesRepository sharedFilesRepository) {
            this.sharedFilesRepository = sharedFilesRepository;
        }

        @GetMapping("/download/{fileId}")
        public void downloadFile(@PathVariable Long fileId) {
            // Retrieve file information from the database
            SharedFile sharedFile = sharedFilesRepository.findById(fileId).orElseThrow();

            User owner = sharedFile.getUser();

            String ownerIP = owner.getIp();
            int ownerPort = owner.getPort(); 

            // Connect to the owner's socket
            try (Socket socket = new Socket(ownerIP, ownerPort)) {
                // Build a socket connection and request the file
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(("DOWNLOAD:" + sharedFile.getFilePath()).getBytes());

                // Receive the file
                byte[] buffer = new byte[1024];
                int bytesRead;
                FileInputStream fileInputStream = new FileInputStream(new File(sharedFile.getFilePath()));
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

