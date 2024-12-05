package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.LanguageLearningSystemFacade;
import model.ForeignLanguage;

public class ChooseLanguageController {
@FXML
private Button selectGermanButton;

@FXML
private Button selectSpanishButton;

@FXML
private Button selectFrenchButton;

@FXML
private Button goToHomeButton;

    private LanguageLearningSystemFacade facade = LanguageLearningSystemFacade.getInstance();

    @FXML
    public void selectGerman() {
        System.out.println("German selected!");
        // Optional: Add functionality if needed
    }

    @FXML
    public void selectSpanish() {
        System.out.println("Spanish selected!");
        try {
            // Start Spanish language for the user
            facade.startLanguage(ForeignLanguage.SPANISH, null); // Set difficulty later on the next screen

            // Navigate to ChooseDifficulty.fxml
            Stage stage = (Stage) selectSpanishButton.getScene().getWindow(); // Ensure you add fx:id="selectSpanishButton" in FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/ChooseDifficulty.fxml"));
            Scene difficultyScene = new Scene(loader.load());
            stage.setScene(difficultyScene);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("An error occurred while starting the Spanish language.");
        }
    }

    @FXML
    public void selectFrench() {
        System.out.println("French selected!");
        // Optional: Add functionality if needed
    }

    @FXML
    public void goToHome() {
        System.out.println("Navigating to Home...");
        try {
            // Navigate back to Homepage.fxml
            Stage stage = (Stage) goToHomeButton.getScene().getWindow(); // Ensure you add fx:id="goToHomeButton" in FXML
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
