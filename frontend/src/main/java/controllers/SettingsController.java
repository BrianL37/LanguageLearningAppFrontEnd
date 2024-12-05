package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class SettingsController {

    @FXML
    private CheckBox notificationsToggle;

    @FXML
    private CheckBox lightModeToggle;

    @FXML
    private CheckBox textToSpeechToggle;

    @FXML
    private Label fontSizeLabel;

    private int fontSize = 12; // Default font size

    @FXML
    public void increaseFontSize() {
        fontSize++;
        fontSizeLabel.setText(String.valueOf(fontSize));
        System.out.println("Font size increased to: " + fontSize);
    }

    @FXML
    public void decreaseFontSize() {
        if (fontSize > 1) { // Prevent font size from going below 1
            fontSize--;
            fontSizeLabel.setText(String.valueOf(fontSize));
            System.out.println("Font size decreased to: " + fontSize);
        }
    }

    @FXML
    public void goToHome() {
        System.out.println("Navigating back to Home...");
        // Add navigation logic to go back to the home screen
    }
}