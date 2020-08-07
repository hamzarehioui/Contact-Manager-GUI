/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhatsApp;

/**
 *
 * @author Hamza Rehioui
 */
public class AlreadyExistingContactException extends Exception {

    public AlreadyExistingContactException() {
        super("A contact with this phone number already exists in your contacts!");
    }

}
