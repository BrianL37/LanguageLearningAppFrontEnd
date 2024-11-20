package library;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


/**
 * This class tests the functions of the Lesson class using JUnit 
 * tests.
 * @author Tony Lin
 */
public class LessonTests {

  private static Lesson lesson;
  @BeforeAll
  public static void oneTimeSetup() {
    lesson = new Lesson(LanguageDifficulty.EASY, LessonTopic.FOOD);
  }
      
  @AfterAll
  public static void oneTimeTearDown() {
          
 }
      
  @BeforeClass
  public void setup() {

    }
      
  @AfterClass
  public void tearDown() {
    }

  @Test
  public void testLessonConstructorValidDifficultyAndTopic() {
    Lesson testLesson = new Lesson(LanguageDifficulty.EASY, LessonTopic.PETS);
    assertTrue(testLesson instanceof Lesson);
  }

  @Test void testGetMultipleChoiceGoodArgument() {
    MultipleChoice choice = lesson.getMultipleChoice(0);
    assertTrue(choice instanceof MultipleChoice);
  }
  @Test
  public void testGetMultipleChoiceBadArgument() {
    MultipleChoice choice = lesson.getMultipleChoice(5);
    assertTrue(choice instanceof MultipleChoice);
  }

  @Test
  public void testCheckMultipleChoiceCorrect() {
    MultipleChoice choice = lesson.getMultipleChoice(0);
    assertTrue(lesson.checkMultipleChoice(choice, choice.getCorrectPosition()));
  }

  @Test
  public void testCheckMultipleChoiceBadArgument() {
    MultipleChoice choice = lesson.getMultipleChoice(0);
    assertTrue(lesson.checkMultipleChoice(choice, 6));
  }

  @Test
  public void testCheckMultipleChoiceIncorrect() {
    int incorrect = 0;
    MultipleChoice choice = lesson.getMultipleChoice(0);
    if(choice.getCorrectPosition()==0) {
      incorrect = 1;
    } else {
      incorrect = choice.getCorrectPosition() - 1;
    }
    assertFalse(lesson.checkMultipleChoice(choice, incorrect));
  }

  @Test
  public void testGetFillBlankGoodArgument() {
    FillBlank blank = lesson.getFillBlank(0);
    assertTrue(blank instanceof FillBlank);
  }

  @Test
  public void testGetFillBlankBadArgument() {
    FillBlank blank = lesson.getFillBlank(3);
    assertTrue(blank instanceof FillBlank);
  }

  @Test
  public void testCheckFillBlankCorrect() {
    FillBlank blank = lesson.getFillBlank(0);
    assertTrue(lesson.checkFillBlank(0, blank.getAnswer().get(0).getForeign()));
  }

  @Test
  public void testCheckFillBlankIncorrect() {
    FillBlank blank = lesson.getFillBlank(0);
    assertFalse(lesson.checkFillBlank(0, "incorrect"));
  }

  @Test
  public void testCheckMatchingCorrect() {
    Matching match = lesson.getMatching();
    String prompt = lesson.matchPrompt();
    String[] split = prompt.split("\n");
    ArrayList<Integer> answers = new ArrayList<>();
    for(int i = 0; i < 3; ++i) {
      answers.add(i);
      for(int j = 3; j < 6; ++j) {
        if(match.isCorrect(split[i],split[j])) {
          answers.add(j);
        }
      }
    }
    int score = lesson.checkMatching(prompt, answers);
    assertTrue(score == 3, "Score should be 3");
  }

  @Test
  public void testCheckMatchingIncorrect() {
    String prompt = lesson.matchPrompt();
    ArrayList<Integer> answers = new ArrayList<>();
    for(int i = 0; i < 6; ++i) {
      answers.add(i);
    }
    int score = lesson.checkMatching(prompt, answers);
    assertFalse(score == 3, "Score should be 3");
  }

  @Test
  public void testCheckMatchingBadArguments() {
    String prompt = lesson.matchPrompt();
    ArrayList<Integer> answers = new ArrayList<>();
    assertFalse(lesson.checkMatching(prompt, answers) == 0);
  }

  @Test
  public void testGetTopic() {
    LessonTopic topic = lesson.getTopic();
    assertTrue(topic instanceof LessonTopic);
  }

  @Test
  public void testGetFlashcard() {
    ArrayList<Flashcard> cards = lesson.getFlashcards();
    assertTrue(cards != null);
  }

}
