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
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import narrator.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import library.App;
import model.*;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


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
    @FXML private int currentQuestionId, questionNumber;
    @FXML private TextArea questionCorrectAnswerBox;
    @FXML private StackPane card1, card2, card3, card4, card5, card6, selected1, selected2;
    @FXML private Text cardText1, cardText2, cardText3, cardText4, cardText5, cardText6;
    @FXML private ArrayList<Object> questions;

  public void initialize() {
  facade = LanguageLearningSystemFacade.getInstance();
  facade.login("JimSmith01", "SmithRocks");
  facade.continueLanguage(ForeignLanguage.SPANISH);
  facade.startLesson(LessonTopic.PETS);
  questions = facade.getLesson().getQuestions();
  questionNumber = 0;
  setQuestion(questions.get(0));
  buttonGroup = new ToggleGroup();
  option1.setToggleGroup(buttonGroup);
  option2.setToggleGroup(buttonGroup);
  option3.setToggleGroup(buttonGroup);
  option4.setToggleGroup(buttonGroup);
 }

 public void setQuestion(Object question) {
   questionCorrectAnswerBox.setVisible(false);
   multipleChoiceContainer.setVisible(false);
   card1.setVisible(false);
   card2.setVisible(false);
   card3.setVisible(false);
   card4.setVisible(false);
   card5.setVisible(false);
   card6.setVisible(false);
   flashcardLabel.setVisible(false);
   questionPrompt.setVisible(false);
   flashcardPane.setVisible(false);
   fillBlankUserInput.setVisible(false);
   currentQuestion = question;
   String questionType = DataLoader.getQuestionTypeString(question);
    questionTypeLabel.setText(questionType);
        switch (questionType) {
            case "Flashcard":
               Flashcard card = (Flashcard) currentQuestion;
                currentQuestionId = card.getId();
               flashcardPane.setOnMouseClicked(event -> {
              if (flashcardLabel.getText().equals(card.getCurrentWord().getForeign())) {
                  flashcardLabel.setText(card.getCurrentWord().getEnglish());
              } else {
                  flashcardLabel.setText(card.getCurrentWord().getForeign());
              }
              facade.getUser().correct(facade.getLesson().getTopic(), card, currentQuestionId);
              });
              flashcardPane.setStyle("-fx-background-color: lightblue;");
              flashcardPane.setVisible(true);
              questionPrompt.setVisible(false);
              flashcardLabel.setVisible(true);
              flashcardLabel.setText(card.getCurrentWord().getForeign());
              interactButton.setText("Advance");
                break;

            case "Multiple Choice":
              flashcardPane.setStyle("-fx-background-color: transparent;");
              flashcardPane.setVisible(true);
              questionPrompt.setVisible(true);
              MultipleChoice multipleChoice = (MultipleChoice) currentQuestion;
              currentQuestionId = multipleChoice.getId();
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
              currentQuestionId = 0;
              flashcardPane.setVisible(true);
              flashcardPane.setStyle("-fx-background-color: transparent;");
              flashcardLabel.setVisible(false);
              questionPrompt.setVisible(false);
              interactButton.setText("Check Pair");
              card1.setVisible(true);
              card2.setVisible(true);
              card3.setVisible(true);
              card4.setVisible(true);
              card5.setVisible(true);
              card6.setVisible(true);
              questionCorrectAnswerBox.setText("Please select a pair");
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
              flashcardPane.setStyle("-fx-background-color: transparent;");
              flashcardPane.setVisible(true);
              questionPrompt.setVisible(true);
              FillBlank fillBlank = (FillBlank) currentQuestion;
              currentQuestionId = fillBlank.getId();
              questionPrompt.setText(fillBlank.getContent().get(0).getEnglish());
              fillBlankUserInput.setVisible(true);
              interactButton.setText("Check Answer");
              break;
            default:
                throw new IllegalArgumentException("Unknown question type");
        }
    }

    @FXML
    private void handleClick() {
      switch(interactButton.getText()) {
        case "Try Again":
          questionNumber = 0;
          resetCards();
          initialize();
          return;
        case "Continue to Board":
          switchToBoardGame();
          return;
        case "Advance":       
        if(questionNumber == 9) {
          questionCorrectAnswerBox.setText("You scored " + facade.getUser().getLessonProgress(facade.getLesson().getTopic()) + "/10");
          if(facade.getUser().getLessonProgress(facade.getLesson().getTopic()) > 8){
            questionCorrectAnswerBox.appendText("\nYou have completed the lesson!");
            interactButton.setText("Continue to Board");
          } else {
            questionCorrectAnswerBox.appendText("\nYou have not completed the lesson. Please try again.");
            interactButton.setText("Try Again");
          }
          return;
        } else {
          setQuestion(questions.get(++questionNumber));
        }
          questionCorrectAnswerBox.setVisible(false);
          return;
        default:
          break;
      }
      questionCorrectAnswerBox.setVisible(true);
      String questionType = DataLoader.getQuestionTypeString(currentQuestion);
      switch (questionType) {
        case "Flashcard":
        break;
        case "Multiple Choice":
        Toggle selectedToggle = buttonGroup.getSelectedToggle();
        String selectedText = "";
        if (selectedToggle != null) {
            RadioButton selectedRadioButton = (RadioButton) selectedToggle;
            selectedText = selectedRadioButton.getText();
        }
        if(selectedText.equals(facade.getLesson().getMultipleChoice(currentQuestionId).getAnswer().get(0).getForeign())) {
          questionCorrectAnswerBox.setText("Correct!");
          facade.getUser().correct(facade.getLesson().getTopic(), currentQuestion, currentQuestionId);
        } else {
          questionCorrectAnswerBox.setText("Incorrect, the correct answer was \"" +
          facade.getLesson().getMultipleChoice(currentQuestionId).getAnswer().get(0).getForeign() + "\"");
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
          facade.getUser().correct(facade.getLesson().getTopic(), (Matching)currentQuestion, currentQuestionId);
          questionCorrectAnswerBox.setText("All pairs are correct!");
          interactButton.setText("Advance");
          break;
        }
        break;
        case "Fill in the Blank":
        String answer = fillBlankUserInput.getText();
        if (answer.equals(facade.getLesson().getFillBlank(currentQuestionId).getAnswer().get(0).getForeign())) {
          questionCorrectAnswerBox.setText("Correct!");
          facade.getUser().correct(facade.getLesson().getTopic(), (FillBlank)currentQuestion, currentQuestionId);
        } else {
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
    private void handlePaneClick1(MouseEvent event) {
        StackPane clickedPane = (StackPane) event.getSource();
        if (selected1 != null) {
            unselectCard(selected1);
        }
        selectCard(clickedPane);
        selected1 = clickedPane;
    }

    @FXML
    private void handlePaneClick2(MouseEvent event) {
        StackPane clickedPane = (StackPane) event.getSource();
        if (selected2 != null) {
            unselectCard(selected2);
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
    private void unselectCard(StackPane pane) {
      Rectangle rectangle = (Rectangle) pane.getChildren().get(0);
      rectangle.setStroke(null);
  }
    private void resetCards() {
      selected1 = null;
      selected2 = null;
      card1.setDisable(false);
      card2.setDisable(false);
      card3.setDisable(false);
      card4.setDisable(false);
      card5.setDisable(false);
      card6.setDisable(false);
      Rectangle rectangle1 = (Rectangle) card1.getChildren().get(0);
      Rectangle rectangle2 = (Rectangle) card2.getChildren().get(0);
      Rectangle rectangle3 = (Rectangle) card3.getChildren().get(0);
      Rectangle rectangle4 = (Rectangle) card4.getChildren().get(0);
      Rectangle rectangle5 = (Rectangle) card5.getChildren().get(0);
      Rectangle rectangle6 = (Rectangle) card6.getChildren().get(0);
      rectangle1.setFill(Color.web("#8CE1F5"));
      rectangle2.setFill(Color.web("#8CE1F5"));
      rectangle3.setFill(Color.web("#8CE1F5"));
      rectangle4.setFill(Color.web("#8CE1F5"));
      rectangle5.setFill(Color.web("#8CE1F5"));
      rectangle6.setFill(Color.web("#8CE1F5"));
      unselectCard(card1);
      unselectCard(card2);
      unselectCard(card3);
      unselectCard(card4);
      unselectCard(card5);
      unselectCard(card6);
    }

      @FXML
    public void switchToBoardGame() {
        try {
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/BoardGame.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) fillBlankUserInput.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
