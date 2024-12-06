package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.*;
import narrator.*;
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
    // Initialize the facade
    private LanguageLearningSystemFacade facade;



    private int fontSize = 12; // Default font size

    @FXML
    public void initialize() {
        facade = LanguageLearningSystemFacade.getInstance();
        facade.login("JimSmith01", "SmithRocks");
        notificationsToggle.setSelected(false); // Default state is "Off"
        lightModeToggle.setSelected(true);     // Default state is "On"
        textToSpeechToggle.setSelected(false); // Default state is "Off"
        
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
        } else {
        }
    }

    public void toggleLightMode(boolean isOn) {
        if (isOn) {
            facade.getUser().getSettings().setLightMode(1); // Set light mode on
            //For Michael: This is where you would change the background color of the settings page
            //EX: root.setStyle("-fx-background-color: #36454F;");
        } else {
            facade.getUser().getSettings().setLightMode(0); // Set dark mode on
        }
    }

    @FXML
    public void toggleTextToSpeech() {
        if (textToSpeechToggle.isSelected()) {
            Narrator.playSound("Text to Speech is enabled.",true); 
        } else {
        }
    }

    @FXML
    public void goToHome() {
        System.out.println("Navigating to Home...");
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
