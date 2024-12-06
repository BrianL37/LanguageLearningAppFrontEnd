package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.LanguageLearningSystemFacade;
import model.User;
import model.UserList;

import java.io.IOException;
import java.util.UUID;

public class SignUpController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private LanguageLearningSystemFacade facade;

    @FXML
    private void initialize() {
        facade = LanguageLearningSystemFacade.getInstance();
    }

    /**
     * Handles the signup process by validating the inputs, creating a new user,
     * and saving it to persistent storage.
     */
    @FXML
    public void handleSignup() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate input
        if (!validateInputs(firstName, lastName, email, username, password)) {
            return;
        }

        // Check for existing username
        if (isUsernameTaken(username)) {
            showAlert("Error", "Username is already taken. Please choose another one.");
            return;
        }

        // Add user to UserList
        UUID userID = UUID.randomUUID();
        facade.signUp(firstName, lastName, username, password, email, userID);
        showAlert("Success", "Account created successfully!");
        switchToLogin();
    }

    /**
     * Validates the inputs for signup.
     * @return true if all inputs are valid; false otherwise.
     */
    private boolean validateInputs(String firstName, String lastName, String email, String username, String password) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return false;
        }

        if (password.length() < 8) {
            showAlert("Error", "Password must be at least 8 characters long.");
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            showAlert("Error", "Invalid email format.");
            return false;
        }

        return true;
    }

    /**
     * Checks if the given username is already taken.
     * @param username The username to check.
     * @return true if the username is taken; false otherwise.
     */
    private boolean isUsernameTaken(String username) {
        for (User user : facade.getAllUsers()) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Switches to the login screen.
     */
    @FXML
    public void switchToLogin() {
        try {
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Login.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Displays an alert dialog with the given title and message.
     * @param title   The title of the alert.
     * @param message The message to display in the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
