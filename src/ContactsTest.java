public class ContactsTest {
    public static void main(String[] args) {

        ContactsManager contact1 = new ContactsManager( "contacts.txt", "data");

        ContactsManager contact2 = new ContactsManager("contacts2.txt", "data");

        contact1.addLine("Hello");


    }
}
