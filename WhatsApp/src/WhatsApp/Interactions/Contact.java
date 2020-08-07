/*
* Contact Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

import WhatsApp.Profile;

public class Contact {

    // Attributes
    private boolean blocked;
    private Profile profile;

    // Constructor
    public Contact(Profile profile) {
        this.profile = profile;
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

    // To String
    @Override
    public String toString() {
        if (isBlocked()) {
            return getProfile().getName() + " is blocked. \n";
        } else {
            return getProfile().toString();
        }
    }

}
