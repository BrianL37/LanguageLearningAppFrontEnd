package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;



public class ChooseDifficultyController {
    private LanguageLearningSystemFacade facade;;
    @FXML private VBox root;
    @FXML
    private Button easyButton;

    @FXML
    private Button mediumButton;

    @FXML
    private Button hardButton;

    @FXML private Button homePageButton;

    @FXML
    public void initialize() {
        // Initialize the facade
        facade = LanguageLearningSystemFacade.getInstance();
        if(facade.getUser().getSettings().getLightMode() == 0) {
            root.setStyle("-fx-background-color: #36454F;");
            easyButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");
            mediumButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");
            hardButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");
            homePageButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");

        } else {
            easyButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
            mediumButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
            hardButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
            }
        }
    

    @FXML
    public void selectEasy(ActionEvent event) {
        highlightButton(easyButton);
        facade.startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.EASY);
    }

    @FXML
    public void selectMedium(ActionEvent event) {
        highlightButton(mediumButton);
        facade.startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.MEDIUM);
    }

    @FXML
    public void selectHard(ActionEvent event) {
        highlightButton(hardButton);
        facade.startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.HARD);
    }


    private void highlightButton(Button selectedButton) {
        if(facade.getUser().getSettings().getLightMode() == 0) {
            easyButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");
            mediumButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");
            hardButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: #36454F; -fx-border-radius: 10; -fx-padding: 15;");
        } else {
            easyButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
            mediumButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
            hardButton.setStyle("-fx-font-size: 18px; -fx-background-color: #8CE1F5; -fx-text-fill: white; -fx-border-radius: 10; -fx-padding: 15;");
            }
        // Highlight the selected button
        selectedButton.setStyle(selectedButton.getStyle() + "; -fx-border-color: black; -fx-border-width: 3;");
    }

    @FXML
    public void goToHomepage() {
        try {
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Homepage.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) easyButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
