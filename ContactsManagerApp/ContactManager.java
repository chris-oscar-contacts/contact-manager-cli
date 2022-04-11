package ContactsManagerApp;

import util.Input;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactManager {


    List<String> fileData = new ArrayList<>();
    List<String> currentData = new ArrayList<>();
    Path filePath = Paths.get("data/contacts.txt");



    public void searchContact() {
        Input input = new Input();
        System.out.println("Search for contact name: ");
        String name = input.getString();
        try {
            Stream<String> stream = Files.lines(filePath);
            stream.filter(line -> line.contains(name)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeContact() {
        Input input = new Input();
        System.out.println();
        System.out.println("Enter the name of contact you would like to delete: ");
        String name = input.getString();
        try {
            List<String> preDeleteList = Files.lines(filePath).toList();
            List<String> selectedData = Files.lines(filePath)
                    .filter(line -> !line.contains(name))
                    .collect(Collectors.toList());
            if (preDeleteList.size() == selectedData.size()) {
                System.out.println("Not match");
            } else {
                System.out.println("Success! contact has been deleted.");
                deleteLines(selectedData);
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public void deleteLines(List<String> data) {
        try {
            Files.write(filePath, data, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch(IOException e) {
            System.out.println("Something went wrong. . . " + e);
        }
    }


    public void readLines() {
        try {
            fileData = Files.readAllLines(filePath);
            Collections.sort(fileData);
        } catch (IOException e) {
            System.out.println("Error reading files " + filePath.getFileName());
            e.printStackTrace();
        }
    }

    //    public void writeFile() {
//        try {
//            Files.write(filePath, currentData, StandardOpenOption.APPEND);
//        } catch(IOException e) {
//            System.out.println("Something went wrong. . . " + e);
//        }
//    }
    public void writeFile(List<String> data) {
        try {
            Files.write(filePath, data, StandardOpenOption.APPEND);
        } catch(IOException e) {
            System.out.println("Something went wrong. . . " + e);
        }
    }

    public String addContactNumber() {
        Input input = new Input();
        System.out.println("Enter a 10 digit contact number:");
        long phoneNumber = input.getLong();
        String stringNum = String.valueOf(phoneNumber);
        if (stringNum.length() == 10) {
            return stringNum.substring(0,3) + "-" +
                    stringNum.substring(3,6) + "-" +
                    stringNum.substring(6,10);
        }
        System.out.println("Invalid, contact number must be 10 digits!");
        return addContactNumber();
    }

    public void addContact() {
        Input input = new Input();
        System.out.println("Enter contact name:");
//        StringBuilder contactName = new StringBuilder(input.getString());
        String contactName = input.getString();
        int contactLength = contactName.length();
        if (contactLength < 20) {
            for (int i = 0; i < 21 - contactLength; i++) {
                contactName = contactName + " ";
            }
            contactName = contactName + "|";
//        contactName.append(" ".repeat(Math.max(0, 30 - contactLength)));
//        contactName.append("|");
            currentData.add(contactName + "  " + addContactNumber() + "  |");
            writeFile(currentData);
            System.out.println("Success! contact has been added.");
            currentData = new ArrayList<>();
        } else {
            System.out.println("Contact must contain less than 21 characters.");
            addContact();
        }
    }


    public void printLines() {
        readLines();
//        for (String line : fileData) {
//            System.out.println(line);
//        }
        fileData.forEach(System.out::println);
    }

    public void contactHeader() {
        System.out.println("Name                 |  Phone number  |");
        System.out.println("---------------------------------------");
    }

    public void mainMenu () {
        System.out.println("********** Contact Manager **********");
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
    }

//    public void returnToMainMenu () {
//        Input input = new Input();
//        System.out.println();
//        System.out.println("Return to main menu? (Yes/No):");
//
//        boolean answer = input.yesNo();
//        if (answer) {
//            mainMenu();
//            runApplication();
//        } else {
//            System.out.println("Goodbye!");
////            writeFile();
//        }
//    }

    public void returnToMainMenu () {
        Input input = new Input();
        System.out.println();
        System.out.println("Choose an option:");
        System.out.println("1. Return to Main Menu");
        System.out.println("2. Exit");

        int answer = input.getInt();
        if (answer == 1) {
            mainMenu();
            runApplication();
        } else if (answer == 2){
            System.out.println("Goodbye!");
        } else {
            returnToMainMenu();
        }
    }

    public void continueToAddContact () {
        Input input = new Input();
        System.out.println();
        System.out.println("Choose an option:");
        System.out.println("1. Create another contact");
        System.out.println("2. Return to Main Menu");

        int answer = input.getInt();
        if (answer == 1) {
            addContact();
            continueToAddContact();
        } else if (answer == 2){
            mainMenu();
            runApplication();
        } else {
            continueToAddContact();
        }
    }

    public void runApplication() {
        Input input = new Input();
        int num = input.getInt(1, 5);

        if (num == 0) {
            System.out.println("Enter an option (1, 2, 3, 4 or 5):");
            runApplication();
        } else if (num == 1) {
            contactHeader();
            printLines();
            returnToMainMenu();
        } else if (num == 2) {
            addContact();
            continueToAddContact();
        } else if (num == 3) {
            searchContact();
            returnToMainMenu();
        } else if (num == 4) {
            contactHeader();
            printLines();
            removeContact();
            returnToMainMenu();
        } else if (num == 5) {
            System.out.println("Goodbye!");
//            writeFile();
        }



    }
}
