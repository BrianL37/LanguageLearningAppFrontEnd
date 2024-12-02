package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserProfileController {

    @FXML
    private TextField firstNameField; // Updated nameField to firstNameField

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    /**
     * Initializes the controller. This method is called automatically
     * after the FXML file has been loaded.
     */
    public void initialize() {
        // Placeholder: Initialize fields with default or existing user data if available
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

        if (!email.contains("@")) {
            showAlert(AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
            return;
        }

        // Placeholder: Save data to database or other storage
        System.out.println("User Profile Updated:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);

        // Notify user of successful update
        showAlert(AlertType.INFORMATION, "Success", "Profile updated successfully!");
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
}
