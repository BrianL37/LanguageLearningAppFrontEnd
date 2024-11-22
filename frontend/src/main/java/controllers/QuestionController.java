package controllers;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import narrator.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import library.App;
import model.*;
import javafx.scene.control.Button;

public class QuestionController {
    @FXML private LanguageLearningSystemFacade facade;
    @FXML private VBox multipleChoiceContainer;
    @FXML private VBox fillBlankContainer;
    @FXML private VBox matchingContainer;
    @FXML private VBox flashCardContainer;
    @FXML private Label questionLabel;
    @FXML RadioButton option1;
    @FXML RadioButton option2;
    @FXML RadioButton option3;
    @FXML RadioButton option4;
    @FXML TextArea fillBlankAnswer;

 public void initialize() {
  facade = LanguageLearningSystemFacade.getInstance();
  facade.login("JimSmith01", "SmithRocks");
  facade.continueLanguage(ForeignLanguage.SPANISH);
  facade.startLesson(LessonTopic.FOOD);
 }

 public void setQuestion(Object question) {
    String questionType = DataLoader.getQuestionTypeString(question);
        questionLabel.setText(questionType);
        switch (questionType) {
            case DataConstants.FLASHCARD:
                multipleChoiceContainer.setVisible(false);
                fillBlankContainer.setVisible(false);
                flashCardContainer.setVisible(true);
                matchingContainer.setVisible(false);
                break;

            case DataConstants.MULTIPLECHOICE:
              multipleChoiceContainer.setVisible(true);
              fillBlankContainer.setVisible(false);
              flashCardContainer.setVisible(false);
              matchingContainer.setVisible(false);
                break;

            case DataConstants.MATCHING:
              multipleChoiceContainer.setVisible(false);
              fillBlankContainer.setVisible(false);
              flashCardContainer.setVisible(false);
              matchingContainer.setVisible(true);
                break;

            case DataConstants.FILLBLANK:
              multipleChoiceContainer.setVisible(false);
              fillBlankContainer.setVisible(true);
              flashCardContainer.setVisible(false);
              matchingContainer.setVisible(false);
              break;
            default:
                throw new IllegalArgumentException("Unknown question type");
        }
    }

    @FXML
    private void handleSubmit() {
      ArrayList<Flashcard> flashcards = facade.getLesson().getFlashcards();
      setQuestion(facade.getLesson().getFillBlank(0));
    }

    @FXML
    private void playNarrator() {
        Narrator.playSound("Hola Mundo");

    }

}
