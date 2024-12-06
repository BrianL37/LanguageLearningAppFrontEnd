package controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;
import narrator.*;
import javafx.geometry.Orientation;
import javafx.util.Callback;

public class ChooseLessonController {
    private LanguageLearningSystemFacade facade;
    @FXML
    private ListView<String> lessonListView;
    @FXML
    private VBox root;
    private boolean narrator;
    public void initialize() {
        facade = LanguageLearningSystemFacade.getInstance();
        narrator = facade.getUser().getSettings().getTextToSpeech() == 1;
        for (LessonTopic topic : LessonTopic.values()) {
            lessonListView.getItems().add(topic.name());
        }
    
        if (narrator) {
            Narrator.playSound("Please choose a lesson", true);
            lessonListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    Narrator.playSound(newValue, true);
                }
            }
        });
        }

        if (facade.getUser().getSettings().getLightMode() == 0) {
            root.setStyle("-fx-background-color: #36454F;");
            lessonListView.setStyle("-fx-pref-width: 400; -fx-pref-height: 500; -fx-border-color: #8CE1F5; -fx-border-width: 2;" +
                    "-fx-background-color: #36454F; -fx-font-size: 18px; -fx-padding: 10;");
    
            // Add a listener to apply the stylesheet once the scene is set
            lessonListView.sceneProperty().addListener((observable, oldScene, newScene) -> {
                if (newScene != null) {
                    newScene.getStylesheets().add(getClass().getResource("/library/darkstyles.css").toExternalForm());
                }
            });
    
            // Set the cell factory to apply the styles
            lessonListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> listView) {
                    return new ListCell<String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item);
                            } else {
                                setText(null);
                                setStyle(""); // Reset style for empty cells
                            }
                        }
                    };
                }
            });
        }
    }

    
    
    

    @FXML
    private void selectLesson() {        
        String selectedLesson = lessonListView.getSelectionModel().getSelectedItem();
        if (selectedLesson != null) {
            if(facade.getUser().getLessonProgress(LessonTopic.fromString(selectedLesson)) > 8) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You have already completed this lesson");
                if(narrator) {
                    Narrator.playSound("You have already completed this lesson." +
                    "Are you sure you want to redo this lesson?", true);
                }
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to redo this lesson?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        if(narrator) {
                            Narrator.playSound("You selected lesson", true);
                            Narrator.playSound(selectedLesson, true);
                        }
                        facade.startLesson(LessonTopic.fromString(selectedLesson));
                        switchToQuestion();
                }
            });
                } else {
                    if(narrator) {
                        Narrator.playSound("You selected lesson", true);
                        Narrator.playSound(selectedLesson, true);
                    }
                    facade.startLesson(LessonTopic.fromString(selectedLesson));
                    switchToQuestion();
                }
        } else {
            showAlert("Please select a lesson first.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

          @FXML
    public void switchToQuestion() {
    try {
        // Load the login.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/QuestionController.fxml"));
        Parent root = loader.load();
        // Get the current stage
        Stage stage = (Stage) lessonListView.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
}
