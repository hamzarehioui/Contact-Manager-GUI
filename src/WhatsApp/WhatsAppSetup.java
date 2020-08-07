package WhatsApp;

/*
* WhatsApp
* BY:  Hamza Rehioui
* ID#: 79704
 */
import WhatsApp.Interactions.*;
import static WhatsApp.Account.allAccounts;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashMap;

public class WhatsAppSetup {

    public static Account testAccount;

    public static void setUp() throws Exception {

        initEverything();

    }

    public static void initEverything() {

        File fp = new File(System.getProperty("user.home") + "/whatsapp/allAccounts.ser");
        if (fp.exists()) {
            loadAccounts();
            testAccount = allAccounts.get("1");
        } else {
            try {
                Account totoAccount = new Account("1", "Toto Toto");
                Account account0 = new Account("(815)679-739", "Bethanie Reyes");
                totoAccount.addContact(new Contact(account0.getProfile()));
                Account account1 = new Account("(428)916-045", "Tymon Hollis");
                totoAccount.addContact(new Contact(account1.getProfile()));
                Account account2 = new Account("(715)563-466", "Allen Cross");
                totoAccount.addContact(new Contact(account2.getProfile()));
                Account account3 = new Account("(638)237-097", "Caleb Sargent");
                totoAccount.addContact(new Contact(account3.getProfile()));
                Account account4 = new Account("(930)859-279", "Amina Fuentes");
                totoAccount.addContact(new Contact(account4.getProfile()));
                Account account5 = new Account("(912)933-224", "Bessie Poole");
                totoAccount.addContact(new Contact(account5.getProfile()));
                Account account6 = new Account("(955)993-541", "Ilyas Lovell");
                totoAccount.addContact(new Contact(account6.getProfile()));
                Account account7 = new Account("(270)934-002", "Sabah Wu");
                totoAccount.addContact(new Contact(account7.getProfile()));
                Account account8 = new Account("(818)568-396", "Kennedy Grimes");
                totoAccount.addContact(new Contact(account8.getProfile()));
                Account account9 = new Account("(895)415-564", "Usama White");
                totoAccount.addContact(new Contact(account9.getProfile()));
                Account account10 = new Account("(604)650-630", "Ryley Lees");
                totoAccount.addContact(new Contact(account10.getProfile()));
                Account account11 = new Account("(910)937-374", "Hiba Knights");
                totoAccount.addContact(new Contact(account11.getProfile()));
                Account account12 = new Account("(513)329-801", "Callie Bridges");
                totoAccount.addContact(new Contact(account12.getProfile()));
                Account account13 = new Account("(765)461-310", "Edith Cash");
                totoAccount.addContact(new Contact(account13.getProfile()));
                Account account14 = new Account("(969)979-920", "Samanta Perry");
                totoAccount.addContact(new Contact(account14.getProfile()));
                Account account15 = new Account("(841)635-798", "Rhona Howells");
                totoAccount.addContact(new Contact(account15.getProfile()));
                Account account16 = new Account("(687)332-477", "Kwame Landry");
                totoAccount.addContact(new Contact(account16.getProfile()));
                Account account17 = new Account("(317)978-104", "Lisa Oliver");
                totoAccount.addContact(new Contact(account17.getProfile()));
                Account account18 = new Account("(358)562-949", "Kerrie Dorsey");
                totoAccount.addContact(new Contact(account18.getProfile()));
                Account account19 = new Account("(235)930-791", "Stella Mccann");
                totoAccount.addContact(new Contact(account19.getProfile()));
                Account account20 = new Account("(495)713-966", "Joseph Woods");
                totoAccount.addContact(new Contact(account20.getProfile()));
                Account account21 = new Account("(488)826-937", "Kayan Rodriquez");
                totoAccount.addContact(new Contact(account21.getProfile()));
                Account account22 = new Account("(730)490-540", "Saeed Michael");
                totoAccount.addContact(new Contact(account22.getProfile()));
                Account account23 = new Account("(957)849-658", "Devan Mill");
                totoAccount.addContact(new Contact(account23.getProfile()));
                Account account24 = new Account("(826)239-203", "Chace Nairn");
                totoAccount.addContact(new Contact(account24.getProfile()));
                Account account25 = new Account("(512)362-650", "Katelyn Pace");
                totoAccount.addContact(new Contact(account25.getProfile()));
                Account account26 = new Account("(989)690-810", "Stacey Bennett");
                totoAccount.addContact(new Contact(account26.getProfile()));
                Account account27 = new Account("(947)213-810", "Alan Day");
                totoAccount.addContact(new Contact(account27.getProfile()));
                Account account28 = new Account("(293)315-644", "Prisha Cooper");
                totoAccount.addContact(new Contact(account28.getProfile()));
                Account account29 = new Account("(704)647-511", "Juliette Flynn");
                totoAccount.addContact(new Contact(account29.getProfile()));                
                testAccount = totoAccount;
                saveAccounts();
            } catch (AlreadyExistingContactException | InvalidPhoneNumberException ex) {}
        }

    }

    @SuppressWarnings("unchecked")
    public static void loadAccounts() {
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.home") + "/whatsapp/allAccounts.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            allAccounts = (HashMap<String, Account>) in.readObject();
            in.close();
            file.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void saveAccounts() {
        File folder = new File(System.getProperty("user.home") + "/whatsapp");
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            File f = new File(System.getProperty("user.home") + "/whatsapp/allAccounts.ser");
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream file = new FileOutputStream(folder.getPath() + "/allAccounts.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(allAccounts);
            out.close();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static boolean isValidPhoneNumber(String phone) {
        try {
            String temp = phone.replace("(", "").replace(")", "").replace("+", "").replace(" ", "").replace("-", "");
            int checkPhone = Integer.parseInt(temp);
        } catch (NumberFormatException er) {
            return false;
        }
        return true;
    }

    public String getStringURL(String a) {
        return this.getClass().getResource(a).toString();
    }

    public URL getURL(String a) {
        return this.getClass().getResource(a);
    }

}
