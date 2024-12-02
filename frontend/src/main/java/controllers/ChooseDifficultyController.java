package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class ChooseDifficultyController {

    @FXML
    public void selectEasy(ActionEvent event) {
        handleDifficultySelection("Easy");
    }

    @FXML
    public void selectMedium(ActionEvent event) {
        handleDifficultySelection("Medium");
    }

    @FXML
    public void selectHard(ActionEvent event) {
        handleDifficultySelection("Hard");
    }

    @FXML
    public void goToHomepage(ActionEvent event) {
        // Logic to navigate to the Homepage
        showAlert("Navigating to Homepage");
    }

    private void handleDifficultySelection(String difficulty) {
        // Logic to handle difficulty selection
        showAlert("You selected: " + difficulty);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
