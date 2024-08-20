import java.util.ArrayList;
import java.util.List;

public class NewsFeed implements Observer {
    private User user;
    private List<String> feedContent;

    public NewsFeed(User user) {
        this.user = user;
        this.feedContent = new ArrayList<>();
    }

    @Override
    public void update(String postContent) {
        feedContent.add(postContent); 
        System.out.println("News feed updated for " + user.getName() + ": " + postContent);
    }

    public void display() {
        System.out.println(user.getName() + "'s News Feed:");
        for (String post : feedContent) {
            System.out.println(post);
        }
    }
}
