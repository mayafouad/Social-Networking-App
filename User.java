import java.util.ArrayList;
import java.util.List;

public class User implements Observable, Observer {
    private String id;
    private String name;
    private String email;
    private String password;
    private String bio;
    private List<User> friends;
    private List<Message> messages;
    private List<Observer> observers;

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = "";
        this.friends = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String postContent) {
        for (Observer observer : observers) {
            observer.update(postContent);
        }
    }

    @Override
    public void update(String postContent) {
        System.out.println(name + " received an update: " + postContent);
    }

    public Post createPost(String content) {
        Post post = new Post(this, content);
        notifyObservers(content); 
        return post;
    }

    public boolean sendFriendRequest(User friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
            friend.friends.add(this);
            addObserver(friend); 
            friend.addObserver(this); 
            return true;
        }
        return false;
    }

    public void sendMessage(User recipient, String content) {
        Message message = new Message(this, recipient, content);
        recipient.getMessages().add(message);
    }

    public void updateProfile(String newName, String newBio) {
        if (newName != null) {
            this.name = newName;
        }
        if (newBio != null) {
            this.bio = newBio;
        }
    }
}
