package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.LanguageLearningSystemFacade;
import narrator.Narrator;

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

    @FXML
    private VBox root; // Root layout for the settings page

    private BoardGameController boardGameController;
    private QuestionController questionController;

    // Singleton instance of LanguageLearningSystemFacade
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
        lightModeToggle.setSelected(false);     // Default state is "Off"
        textToSpeechToggle.setSelected(false);  // Default state is "Off"

        // Apply dark mode style initially
        root.setStyle("-fx-background-color: #36454F;");

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
        facade.getUser().getSettings().setLightMode(1); // Set light mode on

        // Change background color of the settings page to light mode
        root.setStyle("-fx-background-color: #FFFFFF;");

        // Set styles for other UI elements to match light mode
        backButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
        notificationsToggle.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
        lightModeToggle.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
        textToSpeechToggle.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");

        // Update board game and question controller to light mode
        if (boardGameController != null) {
            boardGameController.updateBoardColors(false); // Set dark mode to false
        }
        if (questionController != null) {
            questionController.toggleDarkMode(); // Ensure questionController handles light mode properly
        }
    } else {
        facade.getUser().getSettings().setLightMode(0); // Set dark mode on

        // Change background color of the settings page to dark mode
        root.setStyle("-fx-background-color: #36454F;");

        // Set styles for other UI elements to match dark mode
        backButton.setStyle("-fx-background-color: #36454F; -fx-text-fill: #FFFFFF;");
        notificationsToggle.setStyle("-fx-background-color: #36454F; -fx-text-fill: #FFFFFF;");
        lightModeToggle.setStyle("-fx-background-color: #36454F; -fx-text-fill: #FFFFFF;");
        textToSpeechToggle.setStyle("-fx-background-color: #36454F; -fx-text-fill: #FFFFFF;");

        // Update board game and question controller to dark mode
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
