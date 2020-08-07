/*
* Contact Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

import WhatsApp.Account;
import static WhatsApp.Account.allAccounts;
import WhatsApp.Profile;
import java.io.Serializable;

public class Contact implements Comparable<Contact>, Serializable  {

    // Attributes
    private boolean blocked;
    private final Profile profile;

    // Constructor
    public Contact(Profile profile) {
        this.profile = profile;
        blocked = false;
    }

    public Contact(String phoneNumber)
            throws UserNotRegisteredException {
        Account acc = allAccounts.get(phoneNumber);
        if (acc == null) {
            throw new UserNotRegisteredException();
        }
        this.profile = acc.getProfile();
        blocked = false;
    }

    // Getters 
    public boolean isBlocked() {
        return blocked;
    }

    public Profile getProfile() {
        return profile;
    }

    // Methods
    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }

    @Override
    public int compareTo(Contact contact) {
        return this.profile.getName().compareTo(contact.getProfile().getName());
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Contact
                && this.getProfile().getPhoneNumber().equals(((Contact) obj).getProfile().getPhoneNumber());
    }

    // To String
    @Override
    public String toString() {
        return getProfile().getName();
    }

}
