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

public class FlashcardTests {
    private Flashcard flashcard;
    private Word word;

    @Before
    public void setUp() {
        word = new Word("foreignWord", "englishWord");
        flashcard = new Flashcard(word);
    }

    @Test
    public void testFlipCard() {
        // front of flashcard shows
        assertEquals("foreignWord", flashcard.flipCard());

        // back of flashcard shows after flip
        assertEquals("englishWord", flashcard.flipCard());

        // front shows again after second flip 
        assertEquals("foreignWord", flashcard.flipCard());
    }

    @Test
    public void testGetCurrentWord() {
        assertEquals(word, flashcard.getCurrentWord());
    }

    @Test
    public void testGetContent() {
        ArrayList<Word> expectedContent = new ArrayList<>();
        expectedContent.add(word);
        assertEquals(expectedContent, flashcard.getContent());
    }
}

