/*
* Account Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp;

// import WhatsApp.Interactions.*; // FOR GROUPS, CALLS, CONVERSATIONS...
import TypeValues.MediaType;
import TypeValues.NetworkType;
import WhatsApp.Interactions.InvalidPhoneNumberException;

public class Account {

    // Attributes
    private Profile profile;
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
    // Contact (0.. n) //contact list
    // Conversation (0.. n) //chat log
    // Call (0.. n) //call log
    // GroupConversation (0..n)

    // Constructors 
    public Account(String phoneNumber, String name) throws InvalidPhoneNumberException {
        profile = new Profile(phoneNumber, name);
        setupDataStorageSettings();
    }

    public Account(Profile profile) {
        this.profile = profile;
        setupDataStorageSettings();
    }

    // Getters
    public Profile getProfile() {
        return profile;
    } // NO LOGICAL NEED FOR SETPROFILE()

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

    // PRIVATE METHOD FOR SETTING UP DATA STORAGE
    private void setupDataStorageSettings() {
        photoWIFI = new DataStorageSettings(MediaType.PHOTO, NetworkType.WIFI);
        photoMOBILEDATA = new DataStorageSettings(MediaType.PHOTO, NetworkType.MOBILEDATA);
        photoMOBILEDATA.turnOff(); // default off
        photoROAMING = new DataStorageSettings(MediaType.PHOTO, NetworkType.ROAMING);
        photoROAMING.turnOff(); // default off
        audioWIFI = new DataStorageSettings(MediaType.AUDIO, NetworkType.WIFI);
        audioMOBILEDATA = new DataStorageSettings(MediaType.AUDIO, NetworkType.MOBILEDATA);
        audioMOBILEDATA.turnOff(); // default off
        audioROAMING = new DataStorageSettings(MediaType.AUDIO, NetworkType.ROAMING);
        audioROAMING.turnOff(); // default off
        videoWIFI = new DataStorageSettings(MediaType.VIDEO, NetworkType.WIFI);
        videoMOBILEDATA = new DataStorageSettings(MediaType.VIDEO, NetworkType.MOBILEDATA);
        videoMOBILEDATA.turnOff(); // default off
        videoROAMING = new DataStorageSettings(MediaType.VIDEO, NetworkType.ROAMING);
        videoROAMING.turnOff(); // default off
        documentWIFI = new DataStorageSettings(MediaType.DOCUMENT, NetworkType.WIFI);
        documentMOBILEDATA = new DataStorageSettings(MediaType.DOCUMENT, NetworkType.MOBILEDATA);
        documentMOBILEDATA.turnOff(); // default off
        documentROAMING = new DataStorageSettings(MediaType.DOCUMENT, NetworkType.ROAMING);
        documentROAMING.turnOff(); // default off
    }

    // To String Method
    @Override
    public String toString() {
        return "This account belongs to " + profile.getName()
                + " who has the following settings: \n"
                + getPhotoWIFI() + getPhotoMOBILEDATA() + getPhotoROAMING() + "\n"
                + getAudioWIFI() + getAudioMOBILEDATA() + getAudioROAMING() + "\n"
                + getVideoWIFI() + getVideoMOBILEDATA() + getVideoROAMING() + "\n"
                + getDocumentWIFI() + getDocumentMOBILEDATA() + getDocumentROAMING();
    }
}
