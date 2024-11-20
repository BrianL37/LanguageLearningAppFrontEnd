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
 
 public class FillBlankTests {
     private FillBlank fillBlank;
     private Word answer;
 
     @Before
     public void setUp() {
         answer = new Word("correctAnswer", "correctAnswer");
         fillBlank = new FillBlank("What is the correct answer?", answer, 1);
     }
 
     @Test
     public void testGetAnswer() {
         ArrayList<Word> expected = new ArrayList<>();
         expected.add(answer);
         assertEquals(expected, fillBlank.getAnswer());
     }
 
     @Test
     public void testGetContent() {
         ArrayList<Word> expected = new ArrayList<>();
         expected.add(new Word("", "What is the correct answer?"));
         assertEquals(expected, fillBlank.getContent());
     }
 
     @Test
     public void testIsCorrectWord() {
         assertTrue(fillBlank.isCorrect(answer));
         assertFalse(fillBlank.isCorrect(new Word("wrongAnswer", "wrongAnswer")));
     }
 
     @Test
     public void testIsCorrectString() {
         assertTrue(fillBlank.isCorrect("correctAnswer"));
         assertFalse(fillBlank.isCorrect("wrongAnswer"));
     }
 
     @Test
     public void testSetAnswer() {
         Word newAnswer = new Word("newAnswer", "newAnswer");
         fillBlank.setAnswer(newAnswer);
         ArrayList<Word> expected = new ArrayList<>();
         expected.add(newAnswer);
         assertEquals(expected, fillBlank.getAnswer());
     }
 
     @Test
     public void testIsCorrectArrayList() {
         ArrayList<Word> correctList = new ArrayList<>();
         correctList.add(answer);
         assertTrue(fillBlank.isCorrect(correctList));
 
         ArrayList<Word> wrongList = new ArrayList<>();
         wrongList.add(new Word("wrongAnswer", "wrongAnswer"));
         assertFalse(fillBlank.isCorrect(wrongList));
     }
 
     @Test
     public void testGetId() {
         assertEquals(1, fillBlank.getId());
     }
 }
 
