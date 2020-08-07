package WhatsApp;

/*
* WhatsApp Classes Homework 2
* BY:  Hamza Rehioui
* ID#: 79704
 */
import WhatsApp.Interactions.*; // FOR POSSIBLE INTERACTIONS BETWEEN USERS
import java.util.Scanner;

public class WhatsApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account myAccount;
        Profile myProfile, myContactProfile;
        String myPhone, myName, contactName, contactPhone, myPath;
        MediaMessage mediaMessage;

        // CREATE MY ACCOUNT TEST
        System.out.println("Welcome to WHATSAPP Messenger! \n");
        System.out.print("Please enter your name:  ");
        myName = sc.nextLine();
        System.out.print("Please enter your phone number:  ");
        myPhone = sc.nextLine();
        while (true) {
            try {
                myProfile = new Profile(myPhone, myName);
                break;
            } catch (InvalidPhoneNumberException er) {
                System.err.println(er.getMessage());
                System.err.print("Please enter A VALID phone number (NO SYMBOLS or LETTERS):  ");
                myPhone = sc.nextLine();
            }
        }
        myAccount = new Account(myProfile);
        System.out.println("Congratulations, your account is now set up!  \n");
        System.out.println("Account.toString():  \n" + myAccount);
        System.out.println("Profile.toString():  \n" + myAccount.getProfile());

        // DATA STORAGE SETTINGS TEST
        myAccount.getPhotoWIFI().turnOff();
        myAccount.getDocumentROAMING().turnOff();
        myAccount.getDocumentMOBILEDATA().turnOff();
        System.out.println("The new DataStorageSettings through the account:  \n"
                + myAccount);

        // CREATE A CONTACT TEST
        System.out.println("Now, let's make a *test* profile for a contact! ");
        System.out.print("What is their name? ");
        contactName = sc.nextLine();
        System.out.print("What is their phone number? ");
        contactPhone = sc.nextLine();
        while (true) {
            try {
                myContactProfile = new Profile(contactPhone, contactName);
                break;
            } catch (InvalidPhoneNumberException er) {
                System.err.println(er.getMessage());
                System.err.print("Please enter A VALID phone number (NO SYMBOLS or LETTERS):  ");
                contactPhone = sc.nextLine();
            }
        }
        Contact myContact = new Contact(myContactProfile);
        System.out.println("Contact.toString():  \n" + myContact);
        myContact.block();
        System.out.println("Contact.toString() when contact is blocked:  \n" + myContact);
        myContact.unblock();
        System.out.println("Now, " + myContact.getProfile().getName() + " is blocked:  \n");

        // SEND A MEDIA MESSAGE TEST
        System.out.println("Now, let's test the MEDIA MESSAGE class: \n");
        System.out.println("Please input a path to the file that you want to receive from "
                + myContact.getProfile().getName() + ": ");
        System.out.print("Please enter the path:  ");
        myPath = sc.nextLine();
        while (true) {
            try {
                mediaMessage = new MediaMessage(myContact, myPath);
                break;
            } catch (InvalidPathException er) {
                System.err.println(er.getMessage());
                System.err.print("Please enter A VALID File Path:  ");
                myPath = sc.nextLine();
            } catch (InvalidFileFormatException er) {
                System.err.println(er.getMessage());
                System.err.print("Please enter A VALID File Format: "
                        + "(jpg,png,gif,doc,pdf,mp4,avi,mp3)  ");
                myPath = sc.nextLine();
            }
        }
        System.out.println("MediaMessage.toString():  \n" + mediaMessage);

        System.out.print("\n4 Tests Complete!\n");

    }

}
