import java.util.*;

public class SocialNetworkApp {
    private Map<String, User> users;
    private List<Post> posts;

    public SocialNetworkApp() {
        users = new HashMap<>();
        posts = new ArrayList<>();
    }

    public User registerUser(String name, String email, String password) {
        if (!users.containsKey(email)) {
            User newUser = new User(UUID.randomUUID().toString(), name, email, password);
            users.put(email, newUser);
            return newUser;
        }
        return null;
    }

    public User loginUser(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User getUserByEmail(String email) {
        return users.get(email);
    }

    public void postUpdate(User user, String content) {
        Post post = user.createPost(content);
        posts.add(post);
    }

    public void displayNewsFeed(User user) {
        System.out.println(user.getName() + "'s News Feed:");
        for (Post post : posts) {
            if (user.getFriends().contains(post.getUser())) {
                System.out.println(post.display());
            }
        }
    }

    public void displayMessages(User user) {
        for (Message message : user.getMessages()) {
            System.out.println(message.display());
        }
    }
}
