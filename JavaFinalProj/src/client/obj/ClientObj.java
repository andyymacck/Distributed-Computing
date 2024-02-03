import java.util.Scanner;

public class FileSharingClientApp {

    public static void main(String[] args) {
        FileSharingClient client = new FileSharingClient();
        Scanner scanner = new Scanner(System.in);

        System.out.println("File Sharing Client Application");
        while (true) {
            System.out.println("1. Share a File");
            System.out.println("2. Search for Files");
            System.out.println("3. Download a File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    shareFile(client, scanner);
                    break;
                case 2:
                    searchFiles(client, scanner);
                    break;
                case 3:
                    downloadFile(client, scanner);
                    break;
                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void shareFile(FileSharingClient client, Scanner scanner) {
        System.out.print("Enter the file name to share: ");
        String fileName = scanner.nextLine().trim();

        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine().trim();

        client.shareFile(fileName, filePath);
        System.out.println("File shared successfully!");
    }

    private static void searchFiles(FileSharingClient client, Scanner scanner) {
        System.out.print("Enter the file name to search: ");
        String fileName = scanner.nextLine().trim();

        List<String> matchingFiles = client.searchFiles(fileName);
        if (matchingFiles.isEmpty()) {
            System.out.println("No matching files found.");
        } else {
            System.out.println("Matching files: " + matchingFiles);
        }
    }

    private static void downloadFile(FileSharingClient client, Scanner scanner) {
        System.out.print("Enter the file ID to download: ");
        long fileId = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character

        boolean success = client.downloadFile(fileId);
        if (success) {
            System.out.println("File downloaded successfully!");
        } else {
            System.out.println("Failed to download the file. Check the file ID and try again.");
        }
    }
}
