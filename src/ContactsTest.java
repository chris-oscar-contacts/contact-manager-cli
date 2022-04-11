package src;

import src.ContactsManager;

import java.io.IOException;
import java.util.Scanner;

public class ContactsTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());

        ContactsManager contact1 = new ContactsManager( "contacts.txt", "data");


        while(true) {
            System.out.println("""
                --------------------------
                1. View contacts.
                2. Add a new contact.
                3. Search a contact by name.
                4. Delete an existing contact.
                5. Exit.
                Enter an option (1, 2, 3, 4 or 5):
                """);
            String menuSelection = sc.next();
            if (menuSelection.equals("1")) {
                contact1.printLines();
            } else if (menuSelection.equals("2")){
                System.out.println("Enter contact info name:");
                String contactName = sc.next();
                System.out.println("Enter contact phone number:");
                String phoneNumber = sc.next();
                contact1.addLine(contactName, phoneNumber);
            } else if (menuSelection.equals("3")) {
                try {
                    contact1.searchContact();
                } catch(IOException e){
                    System.out.println("Error => " + e);
                }
            } else if (menuSelection.equals("4")) {
                try {
                    contact1.deleteLine();
                    System.out.println("Contact deleted.");
                } catch (IOException e){
                    System.out.println("Error => " + e);
                }
            } else if (menuSelection.equals("5")) {
                System.out.println("Goodbye");
                break;
            }

        }






    }
}
