package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

//@author De'Juan Carson

public class LanguageLearningSystemUI extends JFrame {
    private LanguageLearningSystemFacade facade; // Facade for the language learning system
    private JTextField usernameField, firstNameField, lastNameField, addFriendField, signUpUsernameField;
    private JPasswordField passwordField, signUpPasswordField, editPasswordField;
    private JTextArea friendListArea, languagesArea, lessonArea;
    private JComboBox<String> languageDropdown, difficultyDropdown;
    private JButton loginButton, signUpButton, logoutButton, editProfileButton, viewLanguagesButton, startGameButton,
            addFriendButton, removeFriendButton, saveChangesButton, startLanguageButton, chooseTopicButton,
            startLessonButton, endLessonButton, backButton, signUpMenuButton, submitEditButton, lessonTopicsButton,
            friendListButton, gameBackButton, friendListBackButton, lessonBackButton; // Declare lessonBackButton

    private String firstName = ""; // User's first name
    private String lastName = ""; // User's last name
    private String userName = ""; // User's username
    private String password = ""; // User's password

    public LanguageLearningSystemUI() {
        setTitle("Language Learning System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
         // Initialize the facade using the singleton pattern
         facade = LanguageLearningSystemFacade.getInstance();
         initializeUI();

        // Login Screen
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signUpButton);

        // Sign Up Screen
        JPanel signUpPanel = new JPanel(new GridLayout(5, 2));
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        signUpUsernameField = new JTextField();
        signUpPasswordField = new JPasswordField();
        JButton submitSignUpButton = new JButton("Submit");
        signUpPanel.add(new JLabel("First Name:"));
        signUpPanel.add(firstNameField);
        signUpPanel.add(new JLabel("Last Name:"));
        signUpPanel.add(lastNameField);
        signUpPanel.add(new JLabel("Username:"));
        signUpPanel.add(signUpUsernameField);
        signUpPanel.add(new JLabel("Password:"));
        signUpPanel.add(signUpPasswordField);
        signUpPanel.add(submitSignUpButton);

        // Main Dashboard
        JPanel dashboardPanel = new JPanel(new GridLayout(7, 1));
        logoutButton = new JButton("Logout");
        editProfileButton = new JButton("Edit Profile");
        viewLanguagesButton = new JButton("View Languages");
        lessonTopicsButton = new JButton("Lesson Topics");
        startGameButton = new JButton("Start Game");
        signUpMenuButton = new JButton("Sign Up");
        friendListButton = new JButton("Friend List");

        dashboardPanel.add(new JLabel("Welcome, " + firstName + " " + lastName + "!"));
        dashboardPanel.add(logoutButton);
        dashboardPanel.add(editProfileButton);
        dashboardPanel.add(viewLanguagesButton);
        dashboardPanel.add(lessonTopicsButton);
        dashboardPanel.add(startGameButton);
        dashboardPanel.add(friendListButton);
        dashboardPanel.add(signUpMenuButton);

        // Edit Profile Screen
        JPanel editProfilePanel = new JPanel(new GridLayout(5, 2));
        editPasswordField = new JPasswordField();
        submitEditButton = new JButton("Save Changes");
        JTextField editFirstNameField = new JTextField(firstName);
        JTextField editLastNameField = new JTextField(lastName);
        JTextField editUserNameField = new JTextField(userName);

        editProfilePanel.add(new JLabel("Edit First Name:"));
        editProfilePanel.add(editFirstNameField);
        editProfilePanel.add(new JLabel("Edit Last Name:"));
        editProfilePanel.add(editLastNameField);
        editProfilePanel.add(new JLabel("Edit Username:"));
        editProfilePanel.add(editUserNameField);
        editProfilePanel.add(new JLabel("Edit Password:"));
        editProfilePanel.add(editPasswordField);
        editProfilePanel.add(submitEditButton);

        // Friend List Screen
        JPanel friendListPanel = new JPanel(new GridLayout(4, 2)); // Adjusted to 4 rows for the back button
        friendListArea = new JTextArea();
        addFriendField = new JTextField();
        addFriendButton = new JButton("Add Friend");
        removeFriendButton = new JButton("Remove Friend");
        friendListBackButton = new JButton("Back to Main Menu"); // New back button for friend list

        friendListPanel.add(new JLabel("Friend List:"));
        friendListPanel.add(new JScrollPane(friendListArea));
        friendListPanel.add(new JLabel("Add Friend:"));
        friendListPanel.add(addFriendField);
        friendListPanel.add(addFriendButton);
        friendListPanel.add(removeFriendButton);
        friendListPanel.add(friendListBackButton); // Add back button to friend list

        // Languages Screen
        JPanel languagesPanel = new JPanel(new GridLayout(3, 2));
        languagesArea = new JTextArea();
        languageDropdown = new JComboBox<>(new String[] { "Spanish", "German", "French", "Chinese" });
        difficultyDropdown = new JComboBox<>(new String[] { "Easy", "Medium", "Hard" });
        startGameButton = new JButton("Start Language");
        backButton = new JButton("Back to Main Menu");
        languagesPanel.add(new JLabel("Available Languages:"));
        languagesPanel.add(new JScrollPane(languagesArea));
        languagesPanel.add(languageDropdown);
        languagesPanel.add(difficultyDropdown);
        languagesPanel.add(startGameButton);
        languagesPanel.add(backButton);

        // Game Screen
        JPanel gamePanel = new JPanel(new GridLayout(4, 1));
        gamePanel.add(new JLabel("Choose a Topic for the Game:"));

        // Add topic dropdown to game panel
        JComboBox<String> topicDropdown = new JComboBox<>(
                new String[] { "School", "Family", "Weather", "Pets", "Food" });
        gamePanel.add(topicDropdown);

        startGameButton = new JButton("Start Game");
        gamePanel.add(startGameButton);

        gameBackButton = new JButton("Back to Main Menu");
        gamePanel.add(gameBackButton);

        // Lesson Screen
        JPanel lessonPanel = new JPanel(new GridLayout(3, 2));
        lessonArea = new JTextArea();
        chooseTopicButton = new JButton("Choose Topic");
        startLessonButton = new JButton("Start Lesson");
        endLessonButton = new JButton("End Lesson");
        lessonPanel.add(new JLabel("Current Lesson:"));
        lessonPanel.add(new JScrollPane(lessonArea));
        lessonPanel.add(chooseTopicButton);
        lessonPanel.add(startLessonButton);
        lessonPanel.add(endLessonButton);

        // Lesson Topics Screen
        JPanel lessonTopicsPanel = new JPanel(new GridLayout(7, 1)); // Grid layout for lesson topics
        lessonBackButton = new JButton("Back to Main Menu");

        lessonTopicsPanel.add(new JLabel("Choose a Lesson Topic:"));
        JButton schoolButton = new JButton("School");
        JButton familyButton = new JButton("Family");
        JButton weatherButton = new JButton("Weather");
        JButton petsButton = new JButton("Pets");
        JButton foodButton = new JButton("Food");

        lessonTopicsPanel.add(schoolButton);
        lessonTopicsPanel.add(familyButton);
        lessonTopicsPanel.add(weatherButton);
        lessonTopicsPanel.add(petsButton);
        lessonTopicsPanel.add(foodButton);
        lessonTopicsPanel.add(lessonBackButton); // Back button

        // Add panels to CardLayout
        add(loginPanel, "Login");
        add(signUpPanel, "Sign Up");
        add(dashboardPanel, "Dashboard");
        add(editProfilePanel, "Edit Profile");
        add(friendListPanel, "Friend List");
        add(languagesPanel, "Languages");
        add(gamePanel, "Game");
        add(lessonPanel, "Lesson");
        add(lessonTopicsPanel, "Lesson Topics"); // Add lesson topics panel

        // Show login panel initially
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "Login");

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // For demonstration purposes, using dummy credentials
                userName = usernameField.getText();
                password = new String(passwordField.getPassword());

                // Simulate a successful login
                if (!userName.isEmpty() && !password.isEmpty()) {
                    firstName = "John"; // Set dummy name for demonstration
                    lastName = "Doe"; // Set dummy name for demonstration
                    updateDashboardUserName();
                    cl.show(getContentPane(), "Dashboard");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Sign Up");
            }
        });

        submitSignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Capture sign-up data
                firstName = firstNameField.getText();
                lastName = lastNameField.getText();
                userName = signUpUsernameField.getText();
                password = new String(signUpPasswordField.getPassword());

                JOptionPane.showMessageDialog(null, "Sign Up successful! Welcome, " + firstName + "!");
                cl.show(getContentPane(), "Login");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = "";
                password = "";
                firstName = "";
                lastName = "";
                cl.show(getContentPane(), "Login");
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Edit Profile");
            }
        });

        submitEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update user's profile with new information
                firstName = editFirstNameField.getText();
                lastName = editLastNameField.getText();
                userName = editUserNameField.getText();
                password = new String(editPasswordField.getPassword());
                JOptionPane.showMessageDialog(null, "Profile updated successfully!");
                cl.show(getContentPane(), "Dashboard");
            }
        });

        friendListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Friend List");
            }
        });

        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friend = addFriendField.getText();
                friendListArea.append(friend + "\n");
                addFriendField.setText("");
            }
        });

        removeFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] friends = friendListArea.getText().split("\n");
                String friendToRemove = addFriendField.getText();
                StringBuilder updatedList = new StringBuilder();
                for (String friend : friends) {
                    if (!friend.equals(friendToRemove) && !friend.isEmpty()) {
                        updatedList.append(friend).append("\n");
                    }
                }
                friendListArea.setText(updatedList.toString());
                addFriendField.setText("");
            }
        });

        viewLanguagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languagesArea.setText("Available Languages:\nSpanish\nGerman\nFrench\nChinese");
                cl.show(getContentPane(), "Languages");
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Game");
            }
        });

        gameBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Dashboard");
            }
        });

        endLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonArea.setText("");
                cl.show(getContentPane(), "Dashboard");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Dashboard");
            }
        });

        // Add action listener for lesson topics button
        lessonTopicsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Lesson Topics"); // Show the lesson topics menu
            }
        });

        // Add action listener for lesson topic buttons
        schoolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonArea.setText("Selected Topic: School");
                cl.show(getContentPane(), "Lesson");
            }
        });

        familyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonArea.setText("Selected Topic: Family");
                cl.show(getContentPane(), "Lesson");
            }
        });

        weatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonArea.setText("Selected Topic: Weather");
                cl.show(getContentPane(), "Lesson");
            }
        });

        petsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonArea.setText("Selected Topic: Pets");
                cl.show(getContentPane(), "Lesson");
            }
        });

        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessonArea.setText("Selected Topic: Food");
                cl.show(getContentPane(), "Lesson");
            }
        });

        // Add action listener for back button on lesson topics
        lessonBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Dashboard"); // Back to the main menu
            }
        });

        // Add action listener for friend list back button
        friendListBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(getContentPane(), "Dashboard");
            }
        });
    }

    private void updateDashboardUserName() {
        // Method to update the dashboard user name
        JLabel welcomeLabel = new JLabel("Welcome, " + firstName + " " + lastName + "!");
        // Assuming dashboardPanel is accessible here
        // dashboardPanel.add(welcomeLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LanguageLearningSystemUI().setVisible(true);
            }
        });
    }

    private void initializeUI() {
    }

     
    

     //* Handles the login action for the user. It retrieves the username and password
     //* from the input fields and attempts to log the user in through the facade.
     //* Displays success or failure message.
 

     private void handleLogin() {
    String userName = usernameField.getText();
    String password = new String(passwordField.getPassword());

    // Perform login using the facade
    try {
        User user = facade.login(userName, password);
        loadUserFriends();  // Load user's friend list after successful login
        loadUserLanguages();  // Load available languages for the user
        loadLesson();  // Load current lesson data
        JOptionPane.showMessageDialog(null, "Login Successful!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Login Failed! Please try again.");
    }      
}


/**
 * Handles the sign-up action for a new user. It retrieves the user's information
 * from the input fields and attempts to create a new user through the facade.
 * Displays success or failure message.
 */

     private void handleSignUp() {
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String userName = signUpUsernameField.getText();
    String password = new String(signUpPasswordField.getPassword());

    // Use try-catch block to handle potential errors
    try {
        // Attempt to sign up the new user
        User newUser = facade.signUp(firstName, lastName, userName, password, UUID.randomUUID());
        
        // Notify user of success and load their related data
        JOptionPane.showMessageDialog(null, "Sign-Up Successful!");
        loadUserFriends();  // Load the user's friend list
        loadUserLanguages();  // Load the available languages for the user
        loadLesson();  // Load the current lesson
    } catch (Exception e) {
        // Notify user of failure in case of an error
        JOptionPane.showMessageDialog(null, "Sign-Up Failed! Please try again.");
    }
}


    /**
     * Loads the user's friends from the facade and displays them in the friend list area.
     */
    private void loadUserFriends() {
        // Clear existing friends
        friendListArea.setText("");

        // Load friend list from facade
        for (User friend : facade.getFriendList()) {
            friendListArea.append(friend.getUserName());
        }
    }

    /**
     * Loads the languages that the user has started from the facade
     * and displays them in the language selection dropdown.
     */
    private void loadUserLanguages() {
        // Clear existing languages
       // languageListBox.removeAllItems();

        // Load languages from facade
        //for (Language language : facade.getLanguages()) {
        //    languageListBox.addItem(language); // Add the language object directly
        //}
    }

    /**
     * Loads and displays lesson information in the lesson area.
     * In a real implementation, you'd pull lesson details from the facade.
     */
    private void loadLesson() {
      //  lessonArea.setText("Lesson information goes here...");
        // In a real implementation, you'd pull lesson details from the facade
    }

    /**
     * Main method to launch the Language Learning System UI.
     * 
     * @param args command line arguments
     */
}

