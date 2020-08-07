/*
* Conversation Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {

    private ArrayList<Message> messages;

    // Constructor
    public Conversation() {
        messages = new ArrayList<>();
    }

    // Methods
    public void send_msg(Message message) {
        messages.add(message);
    }

    public Message receive_msg() {
        return messages.get(messages.size() - 1);
    }

    // Getter
    // PUBLIC METHOD TO RETURN COLLECTION OF MESSAGES
    public ArrayList<Message> getMessageList() {
        return messages;
    }

    // To String 
    @Override
    public String toString() {
        String messageLog = "";
        if (messages.isEmpty()) {
            return "";
        }
        for (Message m : messages) {
            messageLog += (m.toString() + "\n");
        }
        return "Message Log: \n" + messageLog + "\n";
    }

}
