/*
* Conversation Class
* BY:  Hamza Rehioui
* ID#: 79704
 */
package WhatsApp.Interactions;

public class Conversation {
    // PROTECTED Attribute
    // Message (1.. n) //exchanged messages

    // Constructor
    public Conversation() {
        // Initialize the messages list 
    }

    // Methods
    public void send_msg(Message message) {
        // add message to the list of messages
    }

    public void receive_msg() {
        // receive message from the list of messages
    }

    // Getter
    // PUBLIC METHOD TO RETURN COLLECTION OF MESSAGES
    // To String 
    @Override
    public String toString() {
        /* 
        String messageLog = "";
        for(Message m : messages ){
            messageLog += m.toString();
        }
         */
        return /* "Message Log: " + messageLog + */ "\n";
    }

}
