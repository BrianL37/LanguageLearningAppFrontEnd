package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.User;

public class HomepageController {

    private User currentUser;

    @FXML
    private Label welcomeLabel;

    /**
     * Initializes the homepage with the logged-in user's information.
     * @param user The logged-in user.
     */
    public void initializeUser(User user) {
        this.currentUser = user;

        // Example: Use user data to personalize the homepage
        String welcomeMessage = "Welcome, " + user.getFirstName() + "!";
        if (welcomeLabel != null) {
            welcomeLabel.setText(welcomeMessage);
        }
        System.out.println(welcomeMessage);
    }

    @FXML
    private void openSettings(ActionEvent event) {
        showAlert("Navigating to Settings...");
        // Logic to open Settings screen
    }

    @FXML
    private void logout(ActionEvent event) {
        showAlert("Logging out...");
        // Logic to navigate to Login screen
    }

    @FXML
    private void startLesson(ActionEvent event) {
        showAlert("Starting Lessons...");
        // Logic to start a lesson
    }

    @FXML
    private void selectLanguage(ActionEvent event) {
        showAlert("Opening Language Selection...");
        // Logic for language selection
    }

    @FXML
    private void viewUserProfile(ActionEvent event) {
        showAlert("Opening User Profile...");
        // Logic to view the user's profile
    }

    @FXML
    private void selectDifficulty(ActionEvent event) {
        showAlert("Opening Difficulty Selection...");
        // Logic for selecting difficulty
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
