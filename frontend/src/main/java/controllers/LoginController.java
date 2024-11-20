package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Add login validation logic here
        System.out.println("Logging in with username: " + username + " and password: " + password);
    }

    @FXML
    public void switchToSignup() {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/SignUp.fxml"));
            Scene signupScene = new Scene(loader.load());
            stage.setScene(signupScene);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load SignUp.fxml. Check the file path.");
        }
    }
}
