/*
* Group Conversation Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

import java.util.Date;
import java.util.GregorianCalendar;

public class GroupConversation extends Conversation {

    // Group Attributes
    static final int MAXMEMBERS = 256;
    private Contact admin; // i think it should be profile
    // Contact(0..25)//Members of the group 
    private String groupName;
    private Date dateCreated;
    // Icon

    // Constructors
    public GroupConversation(Contact admin, String groupName) {
        super();
        this.admin = admin;
        // Allocate space for the members
        this.groupName = groupName;
        dateCreated = GregorianCalendar.getInstance().getTime();
    }

    // METHODS
    public void invite(Contact contact) {
        if (memberCount() == MAXMEMBERS) {
            return;
        }
        // ADD A CONTACT AS A MEMBER
    }

    public void unjoin(Contact contact) {
        // REMOVE A CONTACT FROM BEING A MEMBER
    }

    private int memberCount() {
        int NUM = 200; //RETURN NUMBER OF MEMBERS
        return NUM;
    }

    // GETTERS AND SETTERS
    // ADMIN
    public Contact getAdmin() {
        return admin;
    }

    public void setAdmin(Contact admin) {
        this.admin = admin;
    }
    // GROUP NAME

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    // DATE CREATED

    public Date getDateCreated() {
        return dateCreated;
    }

    // TO STRING
    @Override
    public String toString() {
        return "Group: " + getGroupName() + "\n"
                + /* "Icon: " + icon + "\n" + */ "Administrator: " + getAdmin().getProfile().getName() + "\n"
                + super.toString();

    }
}
