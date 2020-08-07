/*
* Message SuperClass
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

import java.util.Date;
import java.util.GregorianCalendar;

public class Message {

    // PROTECTED ATTRIBUTES
    protected Date dateTimeSent;
    protected Contact sender;

    // CONSTRUCTOR
    public Message(Contact sender) {
        dateTimeSent = GregorianCalendar.getInstance().getTime();
        this.sender = sender;
    }

    // GETTERS
    public Date getDateTimeSent() {
        return dateTimeSent;
    }

    public Contact getSender() {
        return sender;
    }

    // To String 
    @Override
    public String toString() {
        return getSender().getProfile().getName() + " sent a message at " + getDateTimeSent();
    }
}
