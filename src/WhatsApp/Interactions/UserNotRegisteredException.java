/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhatsApp.Interactions;

/**
 *
 * @author Hamza Rehioui
 */

// THIS WOULD HAVE BEEN CALLED HAD THERE BEEN A NEED TO ADD CONTACT FROM
// A PHONE DIRECTORY BECAUSE NOT ALL CONTACTS ARE NECESSARILY REGISTERED
// AS USERS OF THE APP.
public class UserNotRegisteredException extends Exception {

    public UserNotRegisteredException() {
        super("This phone number is not registered on Whatsapp! ");
    }

}
