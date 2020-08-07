/*
* Profile Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp;

import WhatsApp.Interactions.InvalidPhoneNumberException;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Profile  implements Serializable {

    // Attributes
    private String phoneNumber;
    private String name;
    private String about;
    private String status;
    private Date lastSeen;
    private boolean online;
    // private Image photo;

    // Constructor
    public Profile(String phoneNumber, String name) throws InvalidPhoneNumberException {
        try {
            String temp = phoneNumber.replace("(","").replace(")","").replace("+","").replace(" ","").replace("-","");
            int checkPhone = Integer.parseInt(temp);
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

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Profile
                && this.getName().equals(((Profile) obj).getName())
                && this.getPhoneNumber().equals(((Profile) obj).getPhoneNumber());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.phoneNumber);
        hash = 71 * hash + Objects.hashCode(this.name);
        return hash;
    }

    // To String
    @Override
    public String toString() {
        String onlineS;
        if (isOnline()) {
            onlineS = " is ONLINE";
        } else {
            onlineS = " is NOT ONLINE";
        }

        return getName() + onlineS + ", " + getPhoneNumber()
                + ", " + getStatus() + ", "
                + "Last Seen: " + getLastSeen();
    }

}
