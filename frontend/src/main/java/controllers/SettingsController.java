package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.LanguageLearningSystemFacade;

public class SettingsController {
    @FXML
    private CheckBox notificationsToggle;

    @FXML
    private CheckBox lightModeToggle;

    @FXML
    private CheckBox textToSpeechToggle;

    @FXML
    private Label fontSizeLabel;

    @FXML
    private Button backButton;

    private BoardGameController boardGameController;

    public void setBoardGameController(BoardGameController boardGameController) {
        this.boardGameController = boardGameController;
    }

    private int fontSize = 12; // Default font size

    @FXML
    public void initialize() {
        notificationsToggle.setSelected(false); // Default state is "Off"
        lightModeToggle.setSelected(true);     // Default state is "On"
        textToSpeechToggle.setSelected(false); // Default state is "Off"
    }

    @FXML
    public void toggleNotifications() {
        if (notificationsToggle.isSelected()) {
            System.out.println("Notifications: On");
        } else {
            System.out.println("Notifications: Off");
        }
    }

    @FXML
    public void toggleLightMode() {
        if (lightModeToggle.isSelected()) {
            System.out.println("Light Mode: On");
            LightMode.set(1); // Activate light mode
            if (boardGameController != null) {
                boardGameController.updateBoardColors(false); // Set dark mode to false
            }
        } else {
            System.out.println("Light Mode: Off");
            LightMode.set(0); // Activate dark mode
            if (boardGameController != null) {
                boardGameController.updateBoardColors(true); // Set dark mode to true
            }
        }
    }

    @FXML
    public void toggleTextToSpeech() {
        if (textToSpeechToggle.isSelected()) {
            System.out.println("Text to Speech: On");
            Narrator.speak("Text to Speech is enabled."); // Provide immediate feedback
        } else {
            System.out.println("Text to Speech: Off");
        }
    }

    @FXML
    public void goToHome() {
        System.out.println("Navigating to Home...");
        try {
            // Navigate back to Homepage.fxml
            Stage stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Homepage.fxml"));
            Scene homeScene = new Scene(loader.load());
            stage.setScene(homeScene);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Failed to navigate back to the Home screen.");
        }
    }

    private void showErrorAlert(String message) {
        System.err.println(message);
    }
}
