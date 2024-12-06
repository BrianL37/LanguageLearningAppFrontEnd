package controllers;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

        if(facade.getUser().getSettings().getLightMode() == 0) {
            root.setStyle("-fx-background-color: #36454F;");
            homepageText.setFill(Color.web("#8CE1F5"));
            descriptionText.setFill(Color.web("#8CE1F5"));
        }

    }

    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
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
            Narrator.playSound("Logging Out", true);
            }
        showAlert("Logging out...");
        // Logic to navigate to Login screen
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
