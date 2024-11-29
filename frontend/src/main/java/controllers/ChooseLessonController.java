package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.LessonTopic;

public class ChooseLessonController {

    @FXML
    private ListView<String> lessonListView;

    public void initialize() {
        // Populate the ListView with lesson topics
        for (LessonTopic topic : LessonTopic.values()) {
            lessonListView.getItems().add(topic.name());
        }
    }

    @FXML
    private void selectLesson() {
        String selectedLesson = lessonListView.getSelectionModel().getSelectedItem();
        if (selectedLesson != null) {
            System.out.println("Selected Lesson: " + selectedLesson);
            // Handle logic for loading the selected lesson
        } else {
            System.out.println("No lesson selected.");
        }
    }
}
