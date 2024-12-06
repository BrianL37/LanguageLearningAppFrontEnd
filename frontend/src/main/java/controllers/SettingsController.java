package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    @FXML private Label notificationLabel, ttsLabel, lightLabel;
    private boolean narrator;

    // Singleton instance of LanguageLearningSystemFacade
    private LanguageLearningSystemFacade facade = LanguageLearningSystemFacade.getInstance();

    @FXML
    public void initialize() {
        facade = LanguageLearningSystemFacade.getInstance();
        narrator = facade.getUser().getSettings().getTextToSpeech() == 1;
        
        notificationsToggle.setSelected(facade.getUser().getSettings().getNotifications() == 1); 
        lightModeToggle.setSelected(facade.getUser().getSettings().getLightMode() == 1);     
        textToSpeechToggle.setSelected(facade.getUser().getSettings().getTextToSpeech() == 1);  
        if(facade.getUser().getSettings().getLightMode() == 0) {
          toggleLightMode();
        }

        notificationsToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            toggleNotifications();
        });

        // Add listener to lightModeToggle to call the method when the state changes
        lightModeToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            toggleLightMode();
        });

        textToSpeechToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            toggleTextToSpeech();
        });
    }

    @FXML
    public void toggleNotifications() {
        if(narrator) {
            Narrator.playSound("Notifications toggled.", true);
        }
        if (notificationsToggle.isSelected()) {
        } else {
        }
    }

    public void toggleLightMode() {
        if(narrator) {
            Narrator.playSound("Light mode toggled.", true);
        }
        boolean isOn = lightModeToggle.isSelected(); // Get the state of the toggle
        if (isOn) {
            facade.getUser().getSettings().setLightMode(1); // Set light mode on

            // Change background color of the settings page to light mode
            root.setStyle("-fx-background-color: #FFFFFF;");

            // Set styles for other UI elements to match light mode
            backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white;" + 
                   "-fx-border-radius: 10; -fx-padding: 15;" );
            notificationsToggle.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white;");
            lightModeToggle.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white;");
            textToSpeechToggle.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white;");
            notificationLabel.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white;" +
               "-fx-border-radius: 10; -fx-padding: 15;");
            ttsLabel.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white;" +
               "-fx-border-radius: 10; -fx-padding: 15;");
            lightLabel.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white;" +
               "-fx-border-radius: 10; -fx-padding: 15;");

        } else {
            facade.getUser().getSettings().setLightMode(0); // Set dark mode on

            // Change background color of the settings page to dark mode
            root.setStyle("-fx-background-color: #36454F;");

            // Set styles for other UI elements to match dark mode
            backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F;" + 
                   "-fx-border-radius: 10; -fx-padding: 15;" );
            notificationsToggle.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F;");
            lightModeToggle.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F;");
            textToSpeechToggle.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F;");
            notificationLabel.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F" +
            "-fx-border-radius: 10; -fx-padding: 15;");
             ttsLabel.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F;" +
            "-fx-border-radius: 10; -fx-padding: 15;");
            lightLabel.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F;" +
            "-fx-border-radius: 10; -fx-padding: 15;");
        }
    }

    @FXML
    public void toggleTextToSpeech() {
        if (textToSpeechToggle.isSelected()) {
            facade.getUser().getSettings().setTextToSpeech(1); // Set text to speech on
        } else {
            facade.getUser().getSettings().setTextToSpeech(0); // Set text to speech off
        }
        narrator = facade.getUser().getSettings().getTextToSpeech() == 1;
        Narrator.playSound("Text to Speech toggled.", true);
    }

    @FXML
    public void goToHome() {
        try {
            if(narrator) {
                Narrator.playSound("Back to home", true);
            }
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Homepage.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) notificationsToggle.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showErrorAlert(String message) {
        System.err.println(message);
    }
}
