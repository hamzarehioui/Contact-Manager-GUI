/*
* Text Message SubClass
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

public class TextMessage extends Message {

    // Attribute
    private String text;

    // Constructor
    public TextMessage(Contact sender, String text) {
        super(sender);
        this.text = text;
    }

    // Getter
    public String getText() {
        return text;
    }

    // To String
    @Override
    public String toString() {
        return sender.getProfile().getName() + ": " + getText();
    }
}
