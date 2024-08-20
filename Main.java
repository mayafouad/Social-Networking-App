import java.util.Scanner;

public class Main {
    private static SocialNetworkApp app = new SocialNetworkApp();
    private static Scanner scanner = new Scanner(System.in);
    private static User loggedInUser = null;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            if (loggedInUser == null) {
                displayLoginMenu();
            } else {
                displayMainMenu();
            }
        }
    }

    private static void displayLoginMenu() {
        System.out.println("\n=== Welcome to the Social Network ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                loginUser();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Post an update");
        System.out.println("2. View News Feed");
        System.out.println("3. Send Friend Request");
        System.out.println("4. Send Message");
        System.out.println("5. View Messages");
        System.out.println("6. Update Profile");
        System.out.println("0. Logout");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                postUpdate();
                break;
            case 2:
                viewNewsFeed();
                break;
            case 3:
                sendFriendRequest();
                break;
            case 4:
                sendMessage();
                break;
            case 5:
                viewMessages();
                break;
            case 6:
                updateProfile();
                break;
            case 0:
                loggedInUser = null;
                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void registerUser() {
        System.out.println("\n=== Register ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = app.registerUser(name, email, password);
        if (user != null) {
            System.out.println("Registration successful! Please login.");
        } else {
            System.out.println("User already exists. Try logging in.");
        }
    }

    private static void loginUser() {
        System.out.println("\n=== Login ===");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        loggedInUser = app.loginUser(email, password);
        if (loggedInUser != null) {
            System.out.println("Login successful! Welcome, " + loggedInUser.getName() + ".");
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    private static void postUpdate() {
        System.out.println("\n=== Post an Update ===");
        System.out.print("Enter your post content: ");
        String content = scanner.nextLine();
        app.postUpdate(loggedInUser, content);
        System.out.println("Post successful!");
    }

    private static void viewNewsFeed() {
        app.displayNewsFeed(loggedInUser);
    }

    private static void sendFriendRequest() {
        System.out.println("\n=== Send Friend Request ===");
        System.out.print("Enter the email of the friend: ");
        String email = scanner.nextLine();
        User friend = app.getUserByEmail(email);

        if (friend != null) {
            boolean success = loggedInUser.sendFriendRequest(friend);
            if (success) {
                System.out.println("Friend request sent!");
            } else {
                System.out.println("You are already friends with this user.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    private static void sendMessage() {
        System.out.println("\n=== Send a Message ===");
        System.out.print("Enter the email of the recipient: ");
        String email = scanner.nextLine();
        User recipient = app.getUserByEmail(email);

        if (recipient != null) {
            System.out.print("Enter your message: ");
            String content = scanner.nextLine();
            loggedInUser.sendMessage(recipient, content);
            System.out.println("Message sent!");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void viewMessages() {
        app.displayMessages(loggedInUser);
    }

    private static void updateProfile() {
        System.out.println("\n=== Update Profile ===");
        System.out.print("Enter new name (or press Enter to skip): ");
        String newName = scanner.nextLine();
        System.out.print("Enter new bio (or press Enter to skip): ");
        String newBio = scanner.nextLine();

        loggedInUser.updateProfile(newName, newBio);
        System.out.println("Profile updated!");
    }
}
