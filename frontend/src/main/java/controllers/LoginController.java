package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.User;
import model.Language;
import model.LanguageLearningSystemFacade;

import java.io.IOException;

public class LoginController {
    @FXML private LanguageLearningSystemFacade facade;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML private void initialize() {
        facade = LanguageLearningSystemFacade.getInstance();
    }

    /**
     * Handles the login process by validating the username and password.
     */
    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password are required!");
            return;
        }

        // Authenticate the user using the facade
        LanguageLearningSystemFacade facade = LanguageLearningSystemFacade.getInstance();
        User user = facade.login(username, password);

        if (user != null) {
            showAlert("Success", "Login successful! Welcome, " + user.getFirstName());
            redirectToHome(); // Redirect to the homepage
        } else {
            showAlert("Error", "Invalid username or password!");
        }
    }

    /**
     * Redirects the user to the home screen after a successful login.
     */
    @FXML
    public void redirectToHome() {
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

    /**
     * Switches the view to the SignUp screen.
     */
          @FXML
    public void switchToSignup() {
        try {
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/SignUp.fxml"));
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
     *
     * @param title   The title of the alert.
     * @param message The message to display in the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
