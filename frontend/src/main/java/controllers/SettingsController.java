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
    private QuestionController questionController;

    // Initialize the facade
    private LanguageLearningSystemFacade facade = LanguageLearningSystemFacade.getInstance();

    public void setBoardGameController(BoardGameController boardGameController) {
        this.boardGameController = boardGameController;
    }

    public void setQuestionController(QuestionController questionController) {
        this.questionController = questionController;
    }

    private int fontSize = 12; // Default font size

    @FXML
    public void initialize() {
        notificationsToggle.setSelected(false); // Default state is "Off"
        lightModeToggle.setSelected(true);     // Default state is "On"
        textToSpeechToggle.setSelected(false); // Default state is "Off"
        
        // Add listener to lightModeToggle to call the method when the state changes
        lightModeToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            toggleLightMode(newValue);
        });
    }

    @FXML
    public void toggleNotifications() {
        if (notificationsToggle.isSelected()) {
            System.out.println("Notifications: On");
        } else {
            System.out.println("Notifications: Off");
        }
    }

    public void toggleLightMode(boolean isOn) {
        if (isOn) {
            System.out.println("Light Mode: On");
            facade.getUser().getSettings().setLightMode(0); // Set light mode on
            if (boardGameController != null) {
                boardGameController.updateBoardColors(false); // Set dark mode to false
            }
            if (questionController != null) {
                questionController.toggleDarkMode(); // Ensure questionController handles light mode properly
            }
        } else {
            System.out.println("Light Mode: Off");
            facade.getUser().getSettings().setLightMode(1); // Set dark mode on
            if (boardGameController != null) {
                boardGameController.updateBoardColors(true); // Set dark mode to true
            }
            if (questionController != null) {
                questionController.toggleDarkMode(); // Ensure questionController handles dark mode properly
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
