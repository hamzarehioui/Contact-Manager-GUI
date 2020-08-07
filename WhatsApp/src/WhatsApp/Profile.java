/*
* Profile Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp;

import WhatsApp.Interactions.InvalidPhoneNumberException;
import java.util.Date;
import java.util.GregorianCalendar;

public class Profile {

    // Attributes
    private String phoneNumber;
    private String name;
    private String about;
    private String status;
    private Date lastSeen;
    private boolean online; // true or false
    // private Image photo;

    // Constructor
    public Profile(String phoneNumber, String name) throws InvalidPhoneNumberException {
        try {
            int checkPhone = Integer.parseInt(phoneNumber);
        } catch (NumberFormatException er) {
            throw new InvalidPhoneNumberException();
        }
        // THE CHECK PHONE IS TO CHECK IF THE PHONE NUMBER IF FULLY NUMERIC
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.about = "";
        this.status = "Available"; // This is the default one
        setLastSeen();
        online = true;
    }

    // Getter and setter 
    // PHONE NUMBER
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    // NAME

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    // ABOUT

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    // STATUS

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // LAST SEEN

    public Date getLastSeen() {
        return lastSeen;
    }

    private void setLastSeen() {
        this.lastSeen = GregorianCalendar.getInstance().getTime();
    }
    // ONLINE

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    // To String
    @Override
    public String toString() {
        String onlineS;
        if (isOnline()) {
            onlineS = " is ONLINE";
        } else {
            onlineS = " is not ONLINE";
        }

        return getName() + onlineS + "\n"
                + "Phone Number: " + getPhoneNumber() + "\n"
                + "About: " + getName() + "\n"
                + "Status: " + getStatus() + "\n"
                + "Last Seen at: " + getLastSeen() + "\n";
    }

}
