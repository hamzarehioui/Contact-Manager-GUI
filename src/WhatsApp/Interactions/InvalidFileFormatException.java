/*
* Invalid File Format Exception
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

public class InvalidFileFormatException extends Exception {

    public InvalidFileFormatException(String ext) {
        super("The extension " + ext + " cannot be sent on this platform!");
    }

    public InvalidFileFormatException() {
        super("The path you gave is not a file!");
    }

}
