package controllers;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    @FXML private TextField fillBlankUserInput;;
    @FXML private StackPane flashcardPane;
    @FXML private Label flashcardLabel;
    @FXML private Label questionTypeLabel;
    @FXML private Button interactButton; 
    @FXML private ToggleGroup buttonGroup;
    @FXML RadioButton option1, option2, option3, option4;
    @FXML private Object currentQuestion;
    @FXML private int currentQuestionId;
    @FXML private TextArea questionCorrectAnswerBox;
    @FXML private StackPane card1, card2, card3, card4, card5, card6, selected1, selected2;
    @FXML private Text cardText1, cardText2, cardText3, cardText4, cardText5, cardText6;


  public void initialize() {
  facade = LanguageLearningSystemFacade.getInstance();
  facade.login("JimSmith01", "SmithRocks");
  facade.continueLanguage(ForeignLanguage.SPANISH);
  facade.startLesson(LessonTopic.PETS);
  setQuestion(facade.getLesson().getMatching(), 0);
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
              flashcardPane.setVisible(true);
              flashcardPane.setStyle("-fx-background-color: transparent;");
              questionPrompt.setVisible(false);
              interactButton.setText("Check Pairs");
              card1.setVisible(true);
              card2.setVisible(true);
              card3.setVisible(true);
              card4.setVisible(true);
              card5.setVisible(true);
              card6.setVisible(true);
              String prompt = facade.getLesson().matchPrompt();
              String[] split = prompt.split("\n");
              cardText1.setText(split[0]);
              cardText2.setText(split[1]);
              cardText3.setText(split[2]);
              cardText4.setText(split[3]);
              cardText5.setText(split[4]);
              cardText6.setText(split[5]);
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
        interactButton.setText("Advance");
        break;
        case "Matching":
        if (selected1 != null && selected2 != null) {
          String card1Text = ((Text) selected1.getChildren().get(1)).getText();
          String card2Text = ((Text) selected2.getChildren().get(1)).getText();
          for(Word word : facade.getLesson().getWords()) {
            if(word.getForeign().equals(card1Text) && word.getEnglish().equals(card2Text)) {
              correctPair(selected1, selected2);
              questionCorrectAnswerBox.setText("That pair is correct!");
              break;
            }
            else {
             questionCorrectAnswerBox.setText("So close! Try again!");
            }
          }
        }
        if(card1.isDisabled() && card2.isDisabled() && card3.isDisabled()) {
          facade.getUser().correct(facade.getLesson().getTopic(), (Matching)currentQuestion);
          questionCorrectAnswerBox.setText("All pairs are correct!");
          interactButton.setText("Advance");
          break;
        }
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
        interactButton.setText("Advance");
        break;
      }
    }

    @FXML
    private void playNarrator() {
        Narrator.playSound("Hola Mundo");
    }

    @FXML
    public void handlePaneClick1(MouseEvent event) {
        StackPane clickedPane = (StackPane) event.getSource();
        if (selected1 != null) {
            resetPane(selected1);
        }
        selectCard(clickedPane);
        selected1 = clickedPane;
    }

    @FXML
    public void handlePaneClick2(MouseEvent event) {
        StackPane clickedPane = (StackPane) event.getSource();
        if (selected2 != null) {
            resetPane(selected2);
        }
        selectCard(clickedPane);
        selected2 = clickedPane;
    }
    private void correctPair(StackPane pane1, StackPane pane2) {
      Rectangle rectangle1 = (Rectangle) pane1.getChildren().get(0);
      Rectangle rectangle2 = (Rectangle) pane2.getChildren().get(0);
      rectangle1.setFill(Color.GREEN);
      rectangle2.setFill(Color.GREEN);
      pane1.setDisable(true);
      pane2.setDisable(true);
  }

    private void selectCard(StackPane pane) {
        Rectangle rectangle = (Rectangle) pane.getChildren().get(0);
        rectangle.setStroke(Color.BLACK); 
        rectangle.setStrokeWidth(3);
    }
    private void resetPane(StackPane pane) {
      Rectangle rectangle = (Rectangle) pane.getChildren().get(0);
      rectangle.setStroke(null);
  }
}
