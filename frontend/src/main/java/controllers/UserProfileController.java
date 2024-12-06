package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import model.*;
import narrator.*;

public class UserProfileController {
    @FXML private VBox root, informationSection;
    @FXML private Label firstNameLabel, lastNameLabel, usernameLabel, passwordLabel, emailLabel;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;
    private boolean narrator;
    private LanguageLearningSystemFacade facade;

    /**
     * Initializes the controller. This method is called automatically
     * after the FXML file has been loaded.
     */
    public void initialize() {
        // Get the instance of the facade
        facade = LanguageLearningSystemFacade.getInstance();
        narrator = facade.getUser().getSettings().getTextToSpeech() == 1;
        // Load current user data into fields
        User currentUser = facade.getUser();
        if (currentUser != null) {
            firstNameField.setText(currentUser.getFirstName());
            lastNameField.setText(currentUser.getLastName());
            usernameField.setText(currentUser.getUserName());
            passwordField.setText(currentUser.getPassword());
            emailField.setText(currentUser.getEmail());
        }

        if(facade.getUser().getSettings().getLightMode() == 0) {
            this.root.setStyle("-fx-background-color: #36454F; -fx-padding: 40;");
            this.informationSection.setStyle("-fx-background-color: #36454F; -fx-border-color: #8CE1F5;" +
                 "-fx-border-width: 2; -fx-border-radius: 10; -fx-padding: 20;");
            this.firstNameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #8CE1F5;");
            this.lastNameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #8CE1F5;");
            this.usernameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #8CE1F5;");
            this.passwordLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #8CE1F5;");
            this.emailLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #8CE1F5;");
        }

    }

    /**
     * Save the changes made to the user profile.
     */
    @FXML
    private void saveChanges() {
        // Retrieve input data
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String email = emailField.getText().trim();

        // Validate input fields
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "All fields are required.");
            return;
        }

        if (password.length() < 8) {
            showAlert(AlertType.ERROR, "Invalid Password", "Password must be at least 8 characters long.");
            return;
        }

        if (!email.contains("@")) {
            showAlert(AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
            return;
        }

        // Update user data using the facade
        try {
            facade.editUser(0, firstName); // Assuming 0 corresponds to firstName
            facade.editUser(1, lastName);  // Assuming 1 corresponds to lastName
            facade.editUser(2, username); // Assuming 2 corresponds to username
            facade.editUser(3, password); // Assuming 3 corresponds to password
            facade.editUser(4, email);    // Assuming 4 corresponds to email
            showAlert(AlertType.INFORMATION, "Success", "Profile updated successfully!");
            if(narrator) {
                Narrator.playSound("Profile updated successfully!", true);
            }   
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "Failed to update profile. Please try again.");
        }
        changePage();
    }

    /**
     * Utility method to show an alert dialog.
     *
     * @param alertType The type of the alert.
     * @param title     The title of the alert.
     * @param message   The message of the alert.
     */
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
              @FXML
    public void changePage() {
        try {
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Homepage.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
