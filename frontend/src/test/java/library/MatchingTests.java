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
 
 public class MatchingTests {
     private Matching matching;
     private Word word1;
     private Word word2;
 
     @Before
     public void setUp() {
         word1 = new Word("foreign1", "english1");
         word2 = new Word("foreign2", "english2");
         ArrayList<Word> words = new ArrayList<>();
         words.add(word1);
         matching = new Matching(words);
     }
 
     @Test
     public void testAddWordPair() {
         matching.addWordPair(word2);
         ArrayList<Word> expectedWords = new ArrayList<>();
         expectedWords.add(word1);
         expectedWords.add(word2);
         assertEquals(expectedWords, matching.getContent());
     }
 
     @Test
     public void testIsCorrect() {
         assertTrue(matching.isCorrect("foreign1", "english1"));
         assertFalse(matching.isCorrect("foreign1", "wrongEnglish"));
         assertFalse(matching.isCorrect("wrongForeign", "english1"));
     }
 
     @Test
     public void testGetContent() {
         ArrayList<Word> expectedWords = new ArrayList<>();
         expectedWords.add(word1);
         assertEquals(expectedWords, matching.getContent());
     }
 
     @Test
     public void testSetPrompt() {
         ArrayList<Word> newPrompt = new ArrayList<>();
         newPrompt.add(word2);
         matching.setPrompt(newPrompt);
         assertEquals(newPrompt, matching.getContent());
     }
 
     @Test
     public void testGetAnswer() {
         ArrayList<Word> expectedWords = new ArrayList<>();
         expectedWords.add(word1);
         assertEquals(expectedWords, matching.getAnswer());
     }
 
     @Test
     public void testIsCorrectArrayList() {
         ArrayList<Word> correctAnswer = new ArrayList<>();
         correctAnswer.add(word1);
         assertTrue(matching.isCorrect(correctAnswer));
 
         ArrayList<Word> wrongAnswer = new ArrayList<>();
         wrongAnswer.add(word2);
         assertFalse(matching.isCorrect(wrongAnswer));
     }
 }
 
