import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Serializable classes allow objects to be saved to and loaded from a file
class Contact implements Serializable 
{
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) 
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Getters for contact attributes
    public String getName() 
    {
        return name;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public String getEmailAddress() 
    {
        return emailAddress;
    }

    // Method to edit contact information
    public void editContact(String name, String phoneNumber, String emailAddress) 
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() 
    {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + emailAddress;
    }
}

class AddressBook implements Serializable 
{
    private ArrayList<Contact> contacts = new ArrayList<>();

    // Method to add a contact to the address book
    public void addContact(Contact contact) 
    {
        contacts.add(contact);
    }

    // Method to remove a contact from the address book
    public void removeContact(Contact contact) 
    {
        contacts.remove(contact);
    }

    // Method to search for a contact by name
    public Contact searchContact(String name) 
    {
        for (Contact contact : contacts) 
        {
            if (contact.getName().equalsIgnoreCase(name)) 
            {
                return contact;
            }
        }
        return null; // Return null if the contact is not found
    }

    // Method to display all contacts in the address book
    public void displayAllContacts() 
    {
        for (Contact contact : contacts) 
        {
            System.out.println(contact);
        }
    }
}

public class AddressBookSystem 
{
    public static void main(String[] args) 
    {
        AddressBook addressBook = loadAddressBook(); // Load existing address book or create a new one
        Scanner scanner = new Scanner(System.in);

        while (true) 
        {
            System.out.println("\nAddress Book System");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Remove Contact");
            System.out.println("4. Search Contact");
            System.out.println("5. Display All Contacts");
            System.out.println("6. Save and Exit");
            System.out.print("Enter your choice: ");

            int choice;
            while (true) 
            {
                if (scanner.hasNextInt()) 
                {
                    choice = scanner.nextInt();
                    break;
                } 
                else 
                {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }

            switch (choice) 
            {
                case 1:
                    Contact newContact = createContact(scanner);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added.");
                    break;
                case 2:
                    System.out.print("Enter the name to edit: ");
                    String editName = scanner.next();
                    Contact editContact = addressBook.searchContact(editName);
                    if (editContact != null) {
                        editContact(editContact, scanner);
                        System.out.println("Contact edited.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the name to remove: ");
                    String removeName = scanner.next();
                    Contact removeContact = addressBook.searchContact(removeName);
                    if (removeContact != null) {
                        addressBook.removeContact(removeContact);
                        System.out.println("Contact removed.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the name to search: ");
                    String searchName = scanner.next();
                    Contact foundContact = addressBook.searchContact(searchName);
                    if (foundContact != null) {
                        System.out.println("Contact found: " + foundContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 5:
                    System.out.println("All Contacts:");
                    addressBook.displayAllContacts();
                    break;
                case 6:
                    saveAddressBook(addressBook); // Save the address book to a file
                    System.out.println("Address Book saved. Exiting.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Contact createContact(Scanner scanner) 
    {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter email address: ");
        String emailAddress = scanner.next();
        return new Contact(name, phoneNumber, emailAddress);
    }

    private static void editContact(Contact contact, Scanner scanner) 
    {
        System.out.print("Enter new name: ");
        String name = scanner.next();
        System.out.print("Enter new phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter new email address: ");
        String emailAddress = scanner.next();
        contact.editContact(name, phoneNumber, emailAddress);
    }

    private static AddressBook loadAddressBook() 
    {
        AddressBook addressBook = null;
        try 
        {
            FileInputStream fileIn = new FileInputStream("addressbook.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            addressBook = (AddressBook) in.readObject();
            in.close();
            fileIn.close();
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            // If there's an issue loading the address book, create a new one.
            addressBook = new AddressBook();
        }
        return addressBook;
    }

    private static void saveAddressBook(AddressBook addressBook) 
    {
        try 
        {
            FileOutputStream fileOut = new FileOutputStream("addressbook.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(addressBook);
            out.close();
            fileOut.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
