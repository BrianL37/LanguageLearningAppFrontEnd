package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import narrator.*;


public class ChooseLanguageController {

    @FXML
    private Button selectGermanButton;

    @FXML
    private Button selectSpanishButton;

    @FXML
    private Button selectFrenchButton;

    @FXML
    private Button goToDifficultyButton;

    @FXML private VBox root;

    private LanguageLearningSystemFacade facade;
    private boolean narrator;
    
    @FXML private void initialize() {
        facade = LanguageLearningSystemFacade.getInstance();
        narrator = facade.getUser().getSettings().getTextToSpeech() == 1;

        if (facade.getUser().getSettings().getLightMode() == 0) {
            root.setStyle("-fx-background-color: #36454F;");
            selectGermanButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");
            selectSpanishButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");
            selectFrenchButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");
            goToDifficultyButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");
        } else {
            selectGermanButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
            selectSpanishButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
            selectFrenchButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
            goToDifficultyButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
        }
    }

    /**
     * Handles selecting the German language. Currently not available.
     */
    @FXML
    public void selectGerman() {
        if(narrator) {
            Narrator.playSound("Language currently not available", true);
        }
        showInfoAlert("German is currently not available. Please select another language.");
    }

    /**
     * Handles selecting the Spanish language and navigates to the Choose Difficulty screen.
     */
    @FXML
    public void selectSpanish() {
        if(narrator) {
            Narrator.playSound("Spanish selected", true);
        }
        if(facade.getUser().getSettings().getLightMode() == 1) {
            selectSpanishButton.setStyle(selectSpanishButton.getStyle() + "; -fx-border-color: black; -fx-border-width: 3;");
        } else {
            selectSpanishButton.setStyle(selectSpanishButton.getStyle() + "; -fx-border-color: white; -fx-border-width: 3;");
        }
        
    }
    

    /**
     * Handles selecting the French language. Currently not available.
     */
    @FXML
    public void selectFrench() {
        if(narrator) {
            Narrator.playSound("Language currently not available", true);
        }
        showInfoAlert("French is currently not available. Please select another language.");
    }

    /**
     * Navigates back to the homepage screen.
     */
          @FXML
    public void goToChooseDifficulty() {
        if(!selectSpanishButton.getStyle().contains("-fx-border-color:")) {
            if(narrator) {
                Narrator.playSound("Please select a language before proceeding.", true);
            }
            showErrorAlert("Please select a language before proceeding.");
            return;
        } 
        try {
            // Load the login.fxml
            if(narrator) {
                Narrator.playSound("Choose difficulty", true);
            }
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
