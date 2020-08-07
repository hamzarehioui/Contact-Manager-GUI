/*
* Call Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

import java.time.Duration;
import TypeValues.CallType;
import TypeValues.CallStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class Call  implements Serializable {

    // Attributes
    private Contact callContact;
    private CallStatus status; // incoming, outgoing, missed
    private CallType type; // audio, video
    private Date dateAndTime;
    private Duration time;

    // Constructors
    public Call(Contact callContact, CallStatus status,
            CallType type, Date dateAndTime, Duration time) {
        this.callContact = callContact;
        this.status = status;
        this.type = type;
        this.dateAndTime = dateAndTime;
        // OR
        dateAndTime = GregorianCalendar.getInstance().getTime();
        this.time = time;
    }

    public Call(Contact callContact, String status,
            String type, Date dateAndTime, Duration time) {
        this.callContact = callContact;
        this.status = CallStatus.valueOf(status.toUpperCase());
        this.type = CallType.valueOf(type.toUpperCase());
        this.dateAndTime = dateAndTime;
        this.time = time;
    }

    // Setters (No logical need for getters)
    // CONTACT
    public Contact getCallContact() {
        return callContact;
    }
    // CALL STATUS

    public CallStatus getStatus() {
        return status;
    }
    // CALL TYPE

    public CallType getType() {
        return type;
    }
    // DATE AND TIME

    public Date getDateAndTime() {
        return dateAndTime;
    }
    // TIME (DURATION)

    public Duration getTime() {
        return time;
    }

    // To String
    @Override
    public String toString() {
        return getStatus() + " " + getType() + " call with "
                + getCallContact().getProfile().getName() + " at " + getDateAndTime()
                + " which lasted " + getTime() + ".\n";
    }

}
