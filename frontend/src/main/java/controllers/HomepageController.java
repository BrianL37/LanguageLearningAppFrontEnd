package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.LanguageLearningSystemFacade;
import model.User;

public class HomepageController {
    @FXML private BorderPane root;

    private LanguageLearningSystemFacade facade;
    @FXML
    private Label welcomeLabel;

    /**
     * Initializes the homepage with the logged-in user's information.
     * @param user The logged-in user.
     */
    public void initialize() {
        facade = LanguageLearningSystemFacade.getInstance();
        facade.login("JimSmith01", "SmithRocks");
        // Example: Use user data to personalize the homepage
        String welcomeMessage = "Welcome, " + facade.getUser().getFirstName() + "!";
        welcomeLabel = new Label();
        welcomeLabel.setText(welcomeMessage);
 

    }

    @FXML
    private void openSettings(ActionEvent event) {
        changePage("/library/Settings.fxml");
        // Logic to open Settings screen
    }

    @FXML
    private void logout(ActionEvent event) {
        showAlert("Logging out...");
        // Logic to navigate to Login screen
    }

    @FXML
    private void startLesson(ActionEvent event) {
        if(facade.getLanguage() == null) {
            showAlert("Please select a language and difficulty level first.");
            return;
        }
        changePage("/library/ChooseLesson.fxml");
        // Logic to start a lesson
    }

    @FXML
    private void selectLanguage(ActionEvent event) {
        changePage("/library/ChooseLanguage.fxml");
        // Logic for language selection
    }

    @FXML
    private void viewUserProfile(ActionEvent event) {
        changePage("/library/UserProfile.fxml");
        // Logic to view the user's profile
    }

    @FXML
    private void selectDifficulty(ActionEvent event) {
        if(facade.getLanguage() == null) {
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
        alert.showAndWait();
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
