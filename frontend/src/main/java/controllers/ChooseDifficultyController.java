package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChooseDifficultyController {

    @FXML
    private Button easyButton;

    @FXML
    private Button mediumButton;

    @FXML
    private Button hardButton;

    @FXML
    public void selectEasy(ActionEvent event) {
        highlightButton(easyButton);
    }

    @FXML
    public void selectMedium(ActionEvent event) {
        highlightButton(mediumButton);
    }

    @FXML
    public void selectHard(ActionEvent event) {
        highlightButton(hardButton);
    }

    @FXML
    public void goToHomepage(ActionEvent event) {
        // Logic to navigate to the Homepage
        System.out.println("Navigating to Homepage");
        // Implement actual navigation logic here
    }

    private void highlightButton(Button selectedButton) {
        // Reset all buttons
        easyButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
        mediumButton.setStyle("-fx-font-size: 18px; -fx-background-color: #FFD700; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
        hardButton.setStyle("-fx-font-size: 18px; -fx-background-color: #FF4500; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");

        // Highlight the selected button
        selectedButton.setStyle(selectedButton.getStyle() + "; -fx-border-color: black; -fx-border-width: 3;");
    }
}
