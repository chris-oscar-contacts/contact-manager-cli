package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {

    Scanner sc = new Scanner(System.in);

    private String filename;
    private String directory;
    private List<String> fileData;

    private Path directoryPath;
    private Path filePath;

    // Read a file
    public ContactsManager(String filename, String directory) {
        this.filename = filename;
        this.directory = directory;

        // Paths for dir and files
        this.directoryPath = Paths.get(directory);
        this.filePath =  Paths.get(directory, filename);

        this.fileData = getFile();
    }

    private List<String> getFile() {

        // check if the directory exists and create if it doesn't
        try {
            if(Files.notExists(directoryPath)) Files.createDirectories(directoryPath);
        } catch (IOException e) {
            System.out.println("Error creating directories " + directoryPath.getFileName());
            e.printStackTrace();
        }

        // check if the files exists and create if it doesn't
        try {
            if(Files.notExists(filePath)) Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println("Error creating files " + filePath.getFileName());
            e.printStackTrace();
        }

        // If not create them (above)

        List<String> data = null;

        try {
            data = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("Error reading files " + filePath.getFileName());
            e.printStackTrace();
        }

        // Error Handling? added try catch around each potential IOException


        // readAllLines -> returns a List<String>
        return data;
    }

    public boolean writeFile() {

        try {
            Files.write(filePath ,fileData);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to file: " + filename);
            return false;
        }

        return true;
    }

    public List<String> addLine(String contact, String phone) {
        fileData.add(contact + " | " + phone);
        writeFile(); // as soon as the data is added, we write the file.
        return fileData;
    }

    // print all the lines of the file so that we can see its contents quickly
    public void printLines() {
        System.out.println("""
                Name  |  Phone Number
                --------------------------""");
        for (String line : fileData) {
            System.out.println(line);
        }
    }

    public void searchContact() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("data", "contacts.txt"));

        System.out.println("Enter contact name to search:");
        String contact = sc.nextLine();

        for (String line : lines) {
            if (line.contains(contact)) {
                System.out.println("Search result:" );
                System.out.println(line);
            }
        }
    }

    public void deleteLine() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("data", "contacts.txt"));
        List<String> newList = new ArrayList<>();

        System.out.println("Enter contact name to delete:");
        String contact = sc.next();

        for (String line : lines) {
            if (line.contains(contact)) {
                newList.remove(line);
                continue;
            }
            newList.add(line);
        }

        Files.write(Paths.get("data", "contacts.txt"), newList);
    }





}