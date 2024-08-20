public class Post {
    private User user;
    private String content;

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public String display() {
        return user.getName() + ": " + content;
    }
}
