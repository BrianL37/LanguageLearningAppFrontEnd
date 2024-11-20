package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

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

    @FXML
    public void handleSignup() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Add signup logic here (e.g., save user details to database or file)
        System.out.println("Signing up with details:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
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
            System.err.println("Failed to load Login.fxml. Check the file path.");
        }
    }
}
