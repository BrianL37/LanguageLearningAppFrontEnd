package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.LanguageLearningSystemFacade;
import model.User;

public class UserProfileController {

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

    private LanguageLearningSystemFacade facade;

    /**
     * Initializes the controller. This method is called automatically
     * after the FXML file has been loaded.
     */
    public void initialize() {
        // Get the instance of the facade
        facade = LanguageLearningSystemFacade.getInstance();

        // Load current user data into fields
        User currentUser = facade.getUser();
        if (currentUser != null) {
            firstNameField.setText(currentUser.getFirstName());
            lastNameField.setText(currentUser.getLastName());
            usernameField.setText(currentUser.getUserName());
            passwordField.setText(currentUser.getPassword());
            emailField.setText(currentUser.getEmail());
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
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "Failed to update profile. Please try again.");
        }
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
