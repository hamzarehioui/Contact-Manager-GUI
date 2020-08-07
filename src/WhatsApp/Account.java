/*
* Account Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp;

// import WhatsApp.Interactions.*; // FOR GROUPS, CALLS, CONVERSATIONS...
import TypeValues.MediaType;
import TypeValues.NetworkType;
import static WhatsApp.Account.allAccounts;
import WhatsApp.Interactions.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Account implements Serializable {

    public static HashMap<String, Account> allAccounts; // Key is phone number

    // Attributes
    private Profile profile;
    private ArrayList<Contact> contacts;
    private ArrayList<Conversation> conversations;
    private ArrayList<Call> calls;
    private ArrayList<GroupConversation> groupConversations;
    private DataStorageSettings photoWIFI;
    private DataStorageSettings photoMOBILEDATA;
    private DataStorageSettings photoROAMING;
    private DataStorageSettings audioWIFI;
    private DataStorageSettings audioMOBILEDATA;
    private DataStorageSettings audioROAMING;
    private DataStorageSettings videoWIFI;
    private DataStorageSettings videoMOBILEDATA;
    private DataStorageSettings videoROAMING;
    private DataStorageSettings documentWIFI;
    private DataStorageSettings documentMOBILEDATA;
    private DataStorageSettings documentROAMING;

    // Constructors 
    public Account(String phoneNumber, String name) throws InvalidPhoneNumberException {
        profile = new Profile(phoneNumber, name);
        setupDataStorageSettings();
        contacts = new ArrayList<>();
        conversations = new ArrayList<>();
        calls = new ArrayList<>();
        groupConversations = new ArrayList<>();
        if (allAccounts == null) {
            allAccounts = new HashMap<>();
        }
        allAccounts.put(phoneNumber, this);
    }

    public Account(Profile profile) {
        this.profile = profile;
        setupDataStorageSettings();
        contacts = new ArrayList<>();
        conversations = new ArrayList<>();
        calls = new ArrayList<>();
        groupConversations = new ArrayList<>();
    }

    public ArrayList<Contact> findContact(String criterion, String phone, String name) {
        ArrayList<Contact> results = new ArrayList<>();

        if (criterion.equals("Name")) {
            for (Contact cur : contacts) {
                String con_name = cur.getProfile().getName().toLowerCase();
                if (con_name.contains(name.toLowerCase())) {
                    results.add(cur);
                }
            }
        } else if (criterion.equals("Phone Number")) {
            for (Contact cur : contacts) {
                if (cur.getProfile().getPhoneNumber().contains(phone)) {
                    results.add(cur);
                }
            }
        } else if (criterion.equals("Both")) {
            for (Contact cur : contacts) {
                if (cur.getProfile().getName().toLowerCase().contains(name.toLowerCase())
                        && cur.getProfile().getPhoneNumber().contains(phone)) {
                    results.add(cur);
                }
            }
        }
        return results;
    }

    public Profile getProfile() {
        return profile;
    } // NO LOGICAL NEED FOR SETPROFILE()

    public ArrayList<Contact> getAllContacts() {
        return contacts;
    }

    public void addContact(Contact contact)
            throws AlreadyExistingContactException {
        if (contacts.contains(contact)) {
            throw new AlreadyExistingContactException();
        }
        contacts.add(contact);
        Collections.sort(contacts);
    }

    public void removeContact(Contact contact) {
        if (contacts.contains(contact)) {
            contacts.remove(contact);
            Collections.sort(contacts);
        }
    }

    public ArrayList<Conversation> getAllConversations() {
        return conversations;
    }

    public void addConversation(Conversation conversation) {
        if (conversation instanceof GroupConversation) {
            groupConversations.add((GroupConversation) conversation);
        } else {
            conversations.add(conversation);
        }
    }

    public ArrayList<Call> getAllCalls() {
        return calls;
    }

    public void addCall(Call call) {
        calls.add(call);
    }

    public ArrayList<GroupConversation> getAllGroupConversations() {
        return groupConversations;
    }

    private Account getAccount() {
        return this;
    }

    // PRIVATE METHOD FOR SETTING UP DATA STORAGE
    private void setupDataStorageSettings() {
        setPhotoWIFI(new DataStorageSettings(MediaType.PHOTO, NetworkType.WIFI));
        setPhotoMOBILEDATA(new DataStorageSettings(MediaType.PHOTO, NetworkType.MOBILEDATA));
        photoMOBILEDATA.turnOff(); // default off
        setPhotoROAMING(new DataStorageSettings(MediaType.PHOTO, NetworkType.ROAMING));
        photoROAMING.turnOff(); // default off
        setAudioWIFI(new DataStorageSettings(MediaType.AUDIO, NetworkType.WIFI));
        setAudioMOBILEDATA(new DataStorageSettings(MediaType.AUDIO, NetworkType.MOBILEDATA));
        audioMOBILEDATA.turnOff(); // default off
        setAudioROAMING(new DataStorageSettings(MediaType.AUDIO, NetworkType.ROAMING));
        audioROAMING.turnOff(); // default off
        setVideoWIFI(new DataStorageSettings(MediaType.VIDEO, NetworkType.WIFI));
        setVideoMOBILEDATA(new DataStorageSettings(MediaType.VIDEO, NetworkType.MOBILEDATA));
        videoMOBILEDATA.turnOff(); // default off
        setVideoROAMING(new DataStorageSettings(MediaType.VIDEO, NetworkType.ROAMING));
        videoROAMING.turnOff(); // default off
        setDocumentWIFI(new DataStorageSettings(MediaType.DOCUMENT, NetworkType.WIFI));
        setDocumentMOBILEDATA(new DataStorageSettings(MediaType.DOCUMENT, NetworkType.MOBILEDATA));
        documentMOBILEDATA.turnOff(); // default off
        setDocumentROAMING(new DataStorageSettings(MediaType.DOCUMENT, NetworkType.ROAMING));
        documentROAMING.turnOff(); // default off
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Account
                && this.getProfile().getPhoneNumber().equals(((Account) obj).getProfile().getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return this.getProfile().getPhoneNumber().hashCode();
    }

    public String getDataStorageSettings() {
        return (String) getPhotoWIFI().toString() + getPhotoMOBILEDATA() + getPhotoROAMING() + "\n"
                + getAudioWIFI() + getAudioMOBILEDATA() + getAudioROAMING() + "\n"
                + getVideoWIFI() + getVideoMOBILEDATA() + getVideoROAMING() + "\n"
                + getDocumentWIFI() + getDocumentMOBILEDATA() + getDocumentROAMING();
    }

    // To String Method
    @Override
    public String toString() {
        return this.profile + "\n" + getDataStorageSettings();
    }

    // DataStorageSettings Getters and Setters
    public DataStorageSettings getPhotoWIFI() {
        return photoWIFI;
    }

    public DataStorageSettings getPhotoMOBILEDATA() {
        return photoMOBILEDATA;
    }

    public DataStorageSettings getPhotoROAMING() {
        return photoROAMING;
    }

    public DataStorageSettings getAudioWIFI() {
        return audioWIFI;
    }

    public DataStorageSettings getAudioMOBILEDATA() {
        return audioMOBILEDATA;
    }

    public DataStorageSettings getAudioROAMING() {
        return audioROAMING;
    }

    public DataStorageSettings getVideoWIFI() {
        return videoWIFI;
    }

    public DataStorageSettings getVideoMOBILEDATA() {
        return videoMOBILEDATA;
    }

    public DataStorageSettings getVideoROAMING() {
        return videoROAMING;
    }

    public DataStorageSettings getDocumentWIFI() {
        return documentWIFI;
    }

    public DataStorageSettings getDocumentMOBILEDATA() {
        return documentMOBILEDATA;
    }

    public DataStorageSettings getDocumentROAMING() {
        return documentROAMING;
    }

    public void setPhotoWIFI(DataStorageSettings photoWIFI) {
        this.photoWIFI = photoWIFI;
    }

    public void setPhotoMOBILEDATA(DataStorageSettings photoMOBILEDATA) {
        this.photoMOBILEDATA = photoMOBILEDATA;
    }

    public void setPhotoROAMING(DataStorageSettings photoROAMING) {
        this.photoROAMING = photoROAMING;
    }

    public void setAudioWIFI(DataStorageSettings audioWIFI) {
        this.audioWIFI = audioWIFI;
    }

    public void setAudioMOBILEDATA(DataStorageSettings audioMOBILEDATA) {
        this.audioMOBILEDATA = audioMOBILEDATA;
    }

    public void setAudioROAMING(DataStorageSettings audioROAMING) {
        this.audioROAMING = audioROAMING;
    }

    public void setVideoWIFI(DataStorageSettings videoWIFI) {
        this.videoWIFI = videoWIFI;
    }

    public void setVideoMOBILEDATA(DataStorageSettings videoMOBILEDATA) {
        this.videoMOBILEDATA = videoMOBILEDATA;
    }

    public void setVideoROAMING(DataStorageSettings videoROAMING) {
        this.videoROAMING = videoROAMING;
    }

    public void setDocumentWIFI(DataStorageSettings documentWIFI) {
        this.documentWIFI = documentWIFI;
    }

    public void setDocumentMOBILEDATA(DataStorageSettings documentMOBILEDATA) {
        this.documentMOBILEDATA = documentMOBILEDATA;
    }

    public void setDocumentROAMING(DataStorageSettings documentROAMING) {
        this.documentROAMING = documentROAMING;
    }
}
