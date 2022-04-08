import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ContactsManager {

    private String filename;
    private String directory;
    private List<String> fileData; // Returned data from the getFiles method

    private Path directoryPath;
    private Path filePath;

    // Read a file!
    public ContactsManager(String filename, String directory) {
        this.filename = filename;
        this.directory = directory;

        // Paths for dir and files
        this.directoryPath = Paths.get(directory);
        this.filePath =  Paths.get(directory, filename);

        this.fileData = getFile();
    }

    private List<String> getFile() {

        // Make sure the directory exists
        try {
            if(Files.notExists(directoryPath)) Files.createDirectories(directoryPath);
        } catch (IOException e) {
            System.out.println("Error creating directories " + directoryPath.getFileName());
            e.printStackTrace();
        }

        // Make sure the files exists
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

    public List<String> addLine(String string) {
        fileData.add(string);
        writeFile(); // as soon as the data is added, we write the file.
        return fileData;
    }

    // print all the lines of the file so that we can see its contents quickly
    public void printLines() {
        for (String line : fileData) {
            System.out.println(line);
        }


    }




}