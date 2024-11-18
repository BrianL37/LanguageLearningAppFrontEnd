package library;
/**
 * @author Michael Carson 
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MultipleChoiceTests {
    private MultipleChoice multipleChoice;
    private Word questionWord;
    private Word answer1;
    private Word answer2;
    private Word answer3;
    private Word answer4;

    @Before
    public void setUp() {
        questionWord = new Word("foreignQuestion", "englishQuestion");
        answer1 = new Word("foreign1", "english1");
        answer2 = new Word("foreign2", "english2");
        answer3 = new Word("foreign3", "english3");
        answer4 = new Word("foreign4", "english4");
        
        ArrayList<Word> question = new ArrayList<>();
        question.add(questionWord);

        ArrayList<Word> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);

        multipleChoice = new MultipleChoice(question, answers, 1, 1);
    }

    @Test
    public void testGetContent() {
        ArrayList<Word> expectedQuestion = new ArrayList<>();
        expectedQuestion.add(new Word("foreignQuestion", "englishQuestion"));
        assertEquals(expectedQuestion, multipleChoice.getContent());
    }

    @Test
    public void testGetAnswers() {
        ArrayList<Word> expectedAnswers = new ArrayList<>();
        expectedAnswers.add(new Word("foreign1", "english1"));
        expectedAnswers.add(new Word("foreign2", "english2"));
        expectedAnswers.add(new Word("foreign3", "english3"));
        expectedAnswers.add(new Word("foreign4", "english4"));
        assertEquals(expectedAnswers, multipleChoice.getAnswers());
    }

    @Test
    public void testIsCorrect() {
        ArrayList<Word> correctAnswer = new ArrayList<>();
        correctAnswer.add(new Word("foreign2", "english2"));
        assertTrue(multipleChoice.isCorrect(correctAnswer));

        ArrayList<Word> wrongAnswer = new ArrayList<>();
        wrongAnswer.add(new Word("foreign1", "english1"));
        assertFalse(multipleChoice.isCorrect(wrongAnswer));
    }

    @Test
    public void testGetAnswer() {
        ArrayList<Word> expectedAnswer = new ArrayList<>();
        expectedAnswer.add(new Word("foreign2", "english2"));
        assertEquals(expectedAnswer, multipleChoice.getAnswer());
    }

    @Test
    public void testSetPrompt() {
        ArrayList<Word> newPrompt = new ArrayList<>();
        newPrompt.add(new Word("newForeignQuestion", "newEnglishQuestion"));
        multipleChoice.setPrompt(newPrompt);
        assertEquals(newPrompt, multipleChoice.getContent());
    }

    @Test
    public void testGetId() {
        assertEquals(1, multipleChoice.getId());
    }
}

