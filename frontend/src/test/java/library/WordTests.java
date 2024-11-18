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
 
 public class WordTests {
     private Word word;
     private Word similarWord;
 
     @Before
     public void setUp() {
         word = new Word("foreignWord", "englishWord");
         similarWord = new Word("similarForeign", "similarEnglish");
     }
 
     @Test
     public void testIsCorrect() {
         assertTrue(word.isCorrect("foreignWord", "englishWord"));
         assertFalse(word.isCorrect("wrongForeign", "englishWord"));
         assertFalse(word.isCorrect("foreignWord", "wrongEnglish"));
         assertFalse(word.isCorrect(null, "englishWord"));
         assertFalse(word.isCorrect("foreignWord", null));
     }
 
     @Test
     public void testGetForeign() {
         assertEquals("foreignWord", word.getForeign());
     }
 
     @Test
     public void testGetEnglish() {
         assertEquals("englishWord", word.getEnglish());
     }
 
     @Test
     public void testAddSimilarWord() {
         word.addSimilarWord(similarWord);
         assertEquals(1, word.getSimilarWords().size());
         assertTrue(word.getSimilarWords().contains(similarWord));
     }
 
     @Test
     public void testRemoveSimilarWord() {
         word.addSimilarWord(similarWord);
         assertTrue(word.removeSimilarWord(similarWord));
         assertFalse(word.getSimilarWords().contains(similarWord));
     }
 
     @Test
     public void testGetSimilarWords() {
         word.addSimilarWord(similarWord);
         ArrayList<Word> expectedSimilarWords = new ArrayList<>();
         expectedSimilarWords.add(similarWord);
         assertEquals(expectedSimilarWords, word.getSimilarWords());
     }
 
     @Test
     public void testToString() {
         String expected = "Word [foreign=foreignWord, english=englishWord]";
         assertEquals(expected, word.toString());
     }
 
     @Test
     public void testEquals() {
         Word sameWord = new Word("foreignWord", "englishWord");
         Word differentWord = new Word("differentForeign", "differentEnglish");
         assertTrue(word.equals(sameWord));
         assertFalse(word.equals(differentWord));
         assertFalse(word.equals(null));
         assertFalse(word.equals(new Object()));
     }
 }
 