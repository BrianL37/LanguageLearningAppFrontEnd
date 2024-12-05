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

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

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

        // Authenticate the user
        UserList userList = UserList.getInstance(); // Loads users from JSON if not already loaded
        User user = userList.login(username, password);

        if (user != null) {
            showAlert("Success", "Login successful! Welcome, " + user.getFirstName());
            redirectToHome(user); // Redirect to the homepage with the logged-in user
        } else {
            showAlert("Error", "Invalid username or password!");
        }
    }

    /**
     * Redirects the user to the home screen after a successful login.
     *
     * @param user The logged-in user object.
     */
    private void redirectToHome(User user) {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Homepage.fxml"));
            Scene homeScene = new Scene(loader.load());

            // Pass the user object to HomepageController
            HomepageController controller = loader.getController();
            controller.initializeUser(user);

            stage.setScene(homeScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Error loading Homepage.fxml. Please try again.");
        }
    }

    /**
     * Switches the view to the SignUp screen.
     */
    @FXML
    public void switchToSignup() {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/SignUp.fxml"));
            Scene signupScene = new Scene(loader.load());
            stage.setScene(signupScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load SignUp.fxml. Please try again.");
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
