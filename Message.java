public class Message {
    private User sender;
    private User recipient;
    private String content;

    public Message(User sender, User recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    public String display() {
        return "Message from " + sender.getName() + " to " + recipient.getName() + ": " + content;
    }
}
