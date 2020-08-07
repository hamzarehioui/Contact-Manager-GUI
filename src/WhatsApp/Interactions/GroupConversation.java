/*
* Group Conversation Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

import WhatsApp.Account;
import WhatsApp.Profile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.Icon;
import static WhatsApp.Account.allAccounts;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupConversation extends Conversation {

    // Group Attributes
    static final int MAXMEMBERS = 256;
    private Account admin;
    private ArrayList<Contact> members;
    private Contact chatBot; // To announce events like added or removed member
    private String groupName;
    private Date dateCreated;
    private Icon icon;

    // Constructors
    public GroupConversation(Account admin, String groupName) {
        super();
        this.admin = admin;
        this.members = new ArrayList<>();
        this.groupName = groupName;
        dateCreated = GregorianCalendar.getInstance().getTime();
        icon = null;
        try {
            chatBot = new Contact(new Profile("0", "GroupChatBot"));
        } catch (InvalidPhoneNumberException ex) {
            // THIS WILL NEVER BE REACHED BECAUSE THE VALUES ARE UNCHANGED
        }
        super.send_msg(new TextMessage(chatBot, groupName + " has been successfully created "
                + "by " + admin.getProfile().getName() + " on " + dateCreated + "."));
    }

    // METHODS
    public void invite(Contact contact) {
        if (memberCount() == MAXMEMBERS) {
            return;
        }
        members.add(contact);
        String contactPhoneNumber = contact.getProfile().getPhoneNumber();
        allAccounts.get(contactPhoneNumber).addConversation(this);
        Collections.sort(members);
    }

    public void unjoin(Contact contact) {
        if (members.contains(contact)) {
            members.remove(contact);
        }
        super.send_msg(new TextMessage(chatBot,
                contact.getProfile().getName()
                + " is no longer in the group."));
    }

    public void unjoin(Account member) {
        if (member == admin) {
            return;
        }
        int index = getMembersList().indexOf(new Contact(member.getProfile()));
        if (index != -1) {
            getMembersList().remove(index);
            super.send_msg(new TextMessage(chatBot,
                    member.getProfile().getName()
                    + " is no longer in the group."));
        }
    }

    private int memberCount() {
        return members.size();
    }

    // GETTERS AND SETTERS
    // ADMIN
    public Profile getAdmin() {
        return admin.getProfile();
    }

    public void setAdmin(Account admin) {
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
    // MEMBERS' LIST

    public ArrayList<Contact> getMembersList() {
        return members;
    }

    // TO STRING
    @Override
    public String toString() {
        String groupMembers = "";
        for (Contact member : members) {
            groupMembers += (member.getProfile().getName() + ", ");
        }
        return "Group: " + getGroupName() + "\n"
                + "Administrator: " + getAdmin().getName() + "\n"
                + "Group Members: " + groupMembers + "\n"
                + super.toString();

    }
}
