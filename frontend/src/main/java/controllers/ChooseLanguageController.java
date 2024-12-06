package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.LanguageLearningSystemFacade;
import model.ForeignLanguage;
import model.LanguageDifficulty;
import java.io.IOException;


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

    /**
     * Handles selecting the German language. Currently not available.
     */
    @FXML
    public void selectGerman() {
        showInfoAlert("German is currently not available. Please select another language.");
    }

    /**
     * Handles selecting the Spanish language and navigates to the Choose Difficulty screen.
     */
    @FXML
    public void selectSpanish() {
        try {
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/ChooseDifficulty.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) selectFrenchButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Handles selecting the French language. Currently not available.
     */
    @FXML
    public void selectFrench() {
        showInfoAlert("French is currently not available. Please select another language.");
    }

    /**
     * Navigates back to the homepage screen.
     */
          @FXML
    public void goToHome() {
        try {
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Homepage.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) selectFrenchButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays an error alert with the given message.
     *
     * @param message The error message to display.
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays an informational alert with the given message.
     *
     * @param message The informational message to display.
     */
    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
