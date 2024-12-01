package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

public class UserProfileController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    private User user;

    public void initialize() {
        // Example user setup (replace with real user data as needed)
        user = new User("Alice", "Johnson", "alice123", "securePass1", "alice@example.com", java.util.UUID.randomUUID());
        nameField.setText(user.getFirstName() + " " + user.getLastName());
        usernameField.setText(user.getUserName());
        emailField.setText(user.getEmail());
    }

    @FXML
    private void saveChanges() {
        String[] names = nameField.getText().split(" ", 2);
        if (names.length == 2) {
            user.setFirstName(names[0]);
            user.setLastName(names[1]);
        }
        user.setUserName(usernameField.getText());
        user.setEmail(emailField.getText());

        System.out.println("User details updated: " + user);
    }

    @FXML
    private void goToHomepage() {
        try {
            Stage stage = (Stage) nameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Homepage.fxml")); // Update with actual path
            Scene homepageScene = new Scene(loader.load());
            stage.setScene(homepageScene);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load Homepage.fxml. Check the file path.");
        }
    }
}
