package controllers;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import narrator.Narrator;
import javafx.scene.paint.Color;


public class HomepageController {
    @FXML private BorderPane root;
    @FXML private Text homepageText, descriptionText;
    private LanguageLearningSystemFacade facade;
    @FXML
    private Label welcomeLabel;
    private boolean narrator;
    @FXML Button settingsButton, logoutButton, lessonsButton, languageButton, profileButton, difficultyButton;

    /**
     * Initializes the homepage with the logged-in user's information.
     * @param user The logged-in user.
     */
    public void initialize() {
        facade = LanguageLearningSystemFacade.getInstance();
        facade.login("JimSmith01", "SmithRocks");
        narrator = facade.getUser().getSettings().getTextToSpeech() == 1;
        // Example: Use user data to personalize the homepage
        String welcomeMessage = "Welcome, " + facade.getUser().getFirstName() + "!";
        homepageText.setFill(Color.web("#8CE1F5"));
        descriptionText.setFill(Color.web("#8CE1F5"));
        if(facade.getUser().getSettings().getLightMode() == 0) {
            root.setStyle("-fx-background-color: #36454F;");
            settingsButton.setStyle(settingsButton.getStyle() + "-fx-text-fill: #36454F;");
            logoutButton.setStyle(logoutButton.getStyle() + "-fx-text-fill: #36454F;");
            lessonsButton.setStyle(lessonsButton.getStyle() + "-fx-text-fill: #36454F;");
            languageButton.setStyle(languageButton.getStyle() + "-fx-text-fill: #36454F;");
            profileButton.setStyle(profileButton.getStyle() + "-fx-text-fill: #36454F;");
            difficultyButton.setStyle(difficultyButton.getStyle() + "-fx-text-fill: #36454F;");

        }

    }

    @FXML
    private void openSettings(ActionEvent event) {
        if(narrator) {
        Narrator.playSound("Opening Settings", true);
        }
        changePage("/library/Settings.fxml");
        // Logic to open Settings screen
    }

    @FXML
    private void logout(ActionEvent event) {
        if(narrator) {
            Narrator.playSound("Are you sure you want to log out?", true);
            }
                    Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                facade.logout();
                Platform.exit(); // Close the application if the user confirms
            }
        });
    }
    

    @FXML
    private void startLesson(ActionEvent event) {
        if(facade.getLanguage() == null) {
            if(narrator) {
                Narrator.playSound("Please select a language and difficulty level first", true);
                }
            showAlert("Please select a language and difficulty level first.");
            return;
        }
        if(narrator) {
            Narrator.playSound("Opening Choose Lesson", true);
            }
        changePage("/library/ChooseLesson.fxml");
        // Logic to start a lesson
    }

    @FXML
    private void selectLanguage(ActionEvent event) {
        if(narrator) {
            Narrator.playSound("Opening Choose Language", true);
            }
        changePage("/library/ChooseLanguage.fxml");
        // Logic for language selection
    }

    @FXML
    private void viewUserProfile(ActionEvent event) {
        if(narrator) {
            Narrator.playSound("Opening User Profile", true);
            }
        changePage("/library/UserProfile.fxml");
        // Logic to view the user's profile
    }

    @FXML
    private void selectDifficulty(ActionEvent event) {
        if(facade.getLanguage() == null) {
            if(narrator) {
                Narrator.playSound("Please select a language first", true);
                }
            showAlert("Please select a language first.");
            return;
        }
        if(narrator) {
            Narrator.playSound("Opening Choose Difficulty", true);
            }
        changePage("/library/ChooseDifficulty.fxml");
        // Logic for selecting difficulty
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

          @FXML
    public void changePage(String file) {
        try {
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) this.root.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
