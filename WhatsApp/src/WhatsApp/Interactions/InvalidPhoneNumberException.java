/*
* Invalid Phone Number Exception
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

public class InvalidPhoneNumberException extends Exception {

    public InvalidPhoneNumberException() {
        super("This is an invalid phone number!");
    }
}
