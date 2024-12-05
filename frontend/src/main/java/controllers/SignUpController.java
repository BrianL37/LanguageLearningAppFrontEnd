package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    private UserList userList = UserList.getInstance(); // Singleton for user management

    @FXML
    public void handleSignup() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate input
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return;
        }

        if (password.length() < 8) {
            showAlert("Error", "Password must be at least 8 characters long.");
            return;
        }

        // Add user to UserList
        UUID userID = UUID.randomUUID();
        userList.addUser(firstName, lastName, username, password, email, userID);

        // Save user data
        userList.saveUsers();

        showAlert("Success", "Account created successfully!");
        switchToLogin();
    }

    @FXML
    public void switchToLogin() {
        try {
            Stage stage = (Stage) firstNameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Login.fxml"));
            Scene loginScene = new Scene(loader.load());
            stage.setScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load Login.fxml. Check the file path.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
