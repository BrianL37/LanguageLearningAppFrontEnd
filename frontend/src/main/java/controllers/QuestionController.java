package controllers;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import narrator.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import library.App;
import model.*;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class QuestionController {
    @FXML private LanguageLearningSystemFacade facade;
    @FXML private TextArea questionPrompt;
    @FXML private VBox multipleChoiceContainer;
    @FXML private TextField fillBlankUserInput;
    @FXML private VBox matchingContainer;
    @FXML private StackPane flashcardPane;
    @FXML private Label flashcardLabel;
    @FXML private Label questionTypeLabel;
    @FXML private Button interactButton; 
    @FXML private ToggleGroup buttonGroup;
    @FXML RadioButton option1;
    @FXML RadioButton option2;
    @FXML RadioButton option3;
    @FXML RadioButton option4;
    @FXML private Object currentQuestion;
    @FXML private int currentQuestionId;
    @FXML private TextArea questionCorrectAnswerBox;


  public void initialize() {
  facade = LanguageLearningSystemFacade.getInstance();
  facade.login("JimSmith01", "SmithRocks");
  facade.continueLanguage(ForeignLanguage.SPANISH);
  facade.startLesson(LessonTopic.PETS);
  setQuestion(facade.getLesson().getFillBlank(0), 0);
  buttonGroup = new ToggleGroup();
  option1.setToggleGroup(buttonGroup);
  option2.setToggleGroup(buttonGroup);
  option3.setToggleGroup(buttonGroup);
  option4.setToggleGroup(buttonGroup);
 }

 public void setQuestion(Object question, int id) {;
    currentQuestion = question;
    currentQuestionId = id;
    String questionType = DataLoader.getQuestionTypeString(question);
    questionTypeLabel.setText(questionType);
        switch (questionType) {
            case "Flashcard":
               flashcardPane.setOnMouseClicked(event -> {
              if (flashcardLabel.getText().equals(facade.getLesson().getFlashcards().get(currentQuestionId).getCurrentWord().getForeign())) {
                  flashcardLabel.setText(facade.getLesson().getFlashcards().get(currentQuestionId).getCurrentWord().getEnglish());
              } else {
                  flashcardLabel.setText(facade.getLesson().getFlashcards().get(currentQuestionId).getCurrentWord().getForeign());
              }
          });


              flashcardPane.setStyle("-fx-background-color: lightblue;");
              flashcardPane.setVisible(true);
              interactButton.setText("Advance");
              questionPrompt.setVisible(false);
              flashcardLabel.setVisible(true);
              flashcardLabel.setText(facade.getLesson().getFlashcards().get(0).getCurrentWord().getForeign());
                break;

            case "Multiple Choice":
              multipleChoiceContainer.setVisible(true);
              interactButton.setText("Check Answer");
              questionPrompt.setText(facade.getLesson().multipleChoicePrompt((MultipleChoice) currentQuestion));
              String choices = facade.getLesson().multipleChoiceAnswers((MultipleChoice) currentQuestion);
              String[] choiceArray = choices.split("\n");
              option1.setText(choiceArray[0]);
              option2.setText(choiceArray[1]);
              option3.setText(choiceArray[2]);
              option4.setText(choiceArray[3]);
                break;

            case "Matching":
              matchingContainer.setVisible(true);
              interactButton.setText("Check Answer");
              break;

            case "Fill in the Blank":
              questionPrompt.setText(facade.getLesson().getFillBlank(0).getContent().get(0).getEnglish());
              fillBlankUserInput.setVisible(true);
              interactButton.setText("Check Answer");
              break;
            default:
                throw new IllegalArgumentException("Unknown question type");
        }
    }

    @FXML
    private void handleClick() {
      questionCorrectAnswerBox.setVisible(true);
      String questionType = DataLoader.getQuestionTypeString(currentQuestion);
      switch (questionType) {
        case "Flashcard":
        break;
        case "Multiple Choice":
        Toggle selectedToggle = buttonGroup.getSelectedToggle();
        if (selectedToggle != null) {
            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            String selectedText = selectedRadioButton.getText();
            if(selectedText.equals(facade.getLesson().getMultipleChoice(currentQuestionId).getAnswer().get(0).getForeign())) {
              questionCorrectAnswerBox.setText("Correct!");
              facade.getUser().correct(facade.getLesson().getTopic(), facade.getLesson().getMultipleChoice(currentQuestionId));
            } else {
              facade.getUser().incorrect(facade.getLesson().getTopic(), facade.getLesson().getMultipleChoice(currentQuestionId));
              questionCorrectAnswerBox.setText("Incorrect, the correct answer was \"" +
              facade.getLesson().getMultipleChoice(currentQuestionId).getAnswer().get(0).getForeign() + "\"");
            }
        }
        break;
        case "Matching":
        break;
        case "Fill in the Blank":
        String answer = fillBlankUserInput.getText();
        if (facade.getLesson().checkFillBlank(currentQuestionId, answer) && !answer.isEmpty()) {
          questionCorrectAnswerBox.setText("Correct!");
          facade.getUser().correct(facade.getLesson().getTopic(), facade.getLesson().getFillBlank(currentQuestionId));
        } else {
          facade.getUser().incorrect(facade.getLesson().getTopic(), facade.getLesson().getFillBlank(currentQuestionId));
          questionCorrectAnswerBox.setText("Incorrect, the correct answer was \"" +
          facade.getLesson().getFillBlank(currentQuestionId).getAnswer().get(0).getForeign() + "\"");
        }
        break;
      }
      interactButton.setText("Advance");
    }

    @FXML
    private void playNarrator() {
        Narrator.playSound("Hola Mundo");
    }
}
