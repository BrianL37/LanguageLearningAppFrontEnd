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


    // Singleton instance of LanguageLearningSystemFacade
    private LanguageLearningSystemFacade facade = LanguageLearningSystemFacade.getInstance();

    @FXML
    public void initialize() {
        facade = LanguageLearningSystemFacade.getInstance();
        facade.login("JimSmith01", "SmithRocks");
        
        notificationsToggle.setSelected(facade.getUser().getSettings().getNotifications() == 1); 
        lightModeToggle.setSelected(facade.getUser().getSettings().getLightMode() == 1);     
        textToSpeechToggle.setSelected(facade.getUser().getSettings().getTextToSpeech() == 1);  


        // Add listener to lightModeToggle to call the method when the state changes
        lightModeToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            toggleLightMode(newValue);
        });
        textToSpeechToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            toggleTextToSpeech();
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


    } else {
        facade.getUser().getSettings().setLightMode(0); // Set dark mode on

        // Change background color of the settings page to dark mode
        root.setStyle("-fx-background-color: #36454F;");

        // Set styles for other UI elements to match dark mode
        backButton.setStyle("-fx-background-color: #36454F; -fx-text-fill: #FFFFFF;");
        notificationsToggle.setStyle("-fx-background-color: #36454F; -fx-text-fill: #FFFFFF;");
        lightModeToggle.setStyle("-fx-background-color: #36454F; -fx-text-fill: #FFFFFF;");
        textToSpeechToggle.setStyle("-fx-background-color: #36454F; -fx-text-fill: #FFFFFF;");

    }
}


    @FXML
    public void toggleTextToSpeech() {
        if (textToSpeechToggle.isSelected()) {
            System.out.println("Text to Speech: On");
            Narrator.playSound("Text to Speech is enabled.", true); // Provide immediate feedback
        } else {
            System.out.println("Text to Speech: Off");
        }
    }

    @FXML
    public void goToHome() {
        try {
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
