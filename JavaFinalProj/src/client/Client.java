import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSharingClient {

    private static final String BASE_URL = "http://localhost:8080"; 

    public static void main(String[] args) {
        FileSharingClient client = new FileSharingClient();
        client.shareFile("file.txt", "/path/to/file.txt");
        client.searchFiles("file");
        client.downloadFile(1); 
    }

    public void shareFile(String fileName, String filePath) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/shareFile?fileName=" + fileName + "&filePath=" + filePath))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("File shared successfully");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void searchFiles(String fileName) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/searchFiles?fileName=" + fileName))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<String> fileNames = List.of(response.body().split(","));

            if (fileNames.isEmpty() || fileNames.get(0).equals("no match result")) {
                System.out.println("No matching files found");
            } else {
                System.out.println("Matching files: " + fileNames);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void downloadFile(long fileId) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/download/" + fileId))
                    .build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() == 200) {
                saveFile(response.body(), "downloaded_file.txt");
                System.out.println("File downloaded successfully");
            } else {
                System.out.println("Failed to download file. Status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(InputStream inputStream, String filePath) throws IOException {
        Path path = Path.of(filePath);
        Files.copy(inputStream, path);
    }
}
