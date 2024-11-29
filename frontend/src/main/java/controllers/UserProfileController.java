package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;

public class UserProfileController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    private User user;

    public void initialize() {
        // Example user setup (replace with real user data as needed)
        user = new User("Alice", "Johnson", "alice123", "securePass1", "alice@example.com", java.util.UUID.randomUUID());
        nameLabel.setText("Name: " + user.getFirstName() + " " + user.getLastName());
        usernameLabel.setText("Username: " + user.getUserName());
        emailLabel.setText("Email: " + user.getEmail());
    }

    @FXML
    private void logout() {
        try {
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Login.fxml"));
            Scene loginScene = new Scene(loader.load());
            stage.setScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load Login.fxml. Check the file path.");
        }
    }
}
