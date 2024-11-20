package library;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// @Author Dejuan Carson

class DictionaryManagerTest {

    private DictionaryManager dictionaryManager;

    @BeforeEach
    void setUp() {
        dictionaryManager = DictionaryManager.getInstance();
        // Adding some example words for testing purposes
        dictionaryManager.addWord(LanguageDifficulty.EASY, LessonTopic.SCHOOL, "Hola", "Hello", "A greeting in Spanish");
        dictionaryManager.addWord(LanguageDifficulty.MEDIUM, LessonTopic.FOOD, "Manzana", "Apple", "A type of fruit in Spanish");
    }

    @Test
    void testAddWord() {
        dictionaryManager.addWord(LanguageDifficulty.HARD, LessonTopic.WEATHER, "Nieve", "Snow", "Frozen precipitation in Spanish");
        
        // Verifying if the word was added correctly
        Word word = dictionaryManager.getWord(LanguageDifficulty.HARD, LessonTopic.WEATHER, "Nieve");
        assertNotNull(word, "Word should be added to dictionary");
        assertEquals("Snow", word.getEnglish(), "English translation should match");
    }

    @Test
    void testGetMeaning() {
        // Existing word
        String meaning = dictionaryManager.getMeaning(LanguageDifficulty.EASY, LessonTopic.SCHOOL, "Hola");
        assertEquals("A greeting in Spanish", meaning, "Meaning should match the added word");

        // Non-existing word
        String notFoundMeaning = dictionaryManager.getMeaning(LanguageDifficulty.EASY, LessonTopic.SCHOOL, "Adiós");
        assertEquals("Translation not found.", notFoundMeaning, "Meaning should return 'Translation not found.' for non-existing word");
    }

    @Test
    void testGetWordsByTopic() {
        ArrayList<Word> schoolWords = dictionaryManager.getWordsByTopic(LanguageDifficulty.EASY, LessonTopic.SCHOOL);
        
        // Verifying the number and content of the words for the SCHOOL topic
        assertEquals(1, schoolWords.size(), "Should have 1 word in the SCHOOL topic");
        assertEquals("Hola", schoolWords.get(0).getForeign(), "Foreign word should be 'Hola'");
        
        ArrayList<Word> foodWords = dictionaryManager.getWordsByTopic(LanguageDifficulty.MEDIUM, LessonTopic.FOOD);
        assertEquals(1, foodWords.size(), "Should have 1 word in the FOOD topic");
        assertEquals("Manzana", foodWords.get(0).getForeign(), "Foreign word should be 'Manzana'");
    }

    @Test
    void testGetWord() {
        // Existing word
        Word word = dictionaryManager.getWord(LanguageDifficulty.EASY, LessonTopic.SCHOOL, "Hola");
        assertNotNull(word, "Word should be found in dictionary");
        assertEquals("Hello", word.getEnglish(), "English translation should match 'Hello'");

        // Non-existing word
        Word nonExistentWord = dictionaryManager.getWord(LanguageDifficulty.EASY, LessonTopic.SCHOOL, "Adiós");
        assertNull(nonExistentWord, "Non-existing word should return null");
    }

    @Test
    void testSingletonInstance() {
        DictionaryManager instance1 = DictionaryManager.getInstance();
        DictionaryManager instance2 = DictionaryManager.getInstance();
        
        // Ensure both instances are the same (singleton)
        assertSame(instance1, instance2, "DictionaryManager should return the same instance each time");
    }
}
