package library;
/**
 * @Author Brian Lee
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class LanguageTest {
    private Language language;
    private LanguageDifficulty difficulty;
    private ForeignLanguage foreignLanguage;

    @BeforeEach
    void setUp() {
        difficulty = LanguageDifficulty.EASY; // Set initial difficulty to EASY
        foreignLanguage = ForeignLanguage.SPANISH; // Set the language to SPANISH
        language = new Language(foreignLanguage, difficulty); // Create a new Language instance
    }

    @Test
    void testConstructorCreatesLessons() {
        assertNotNull(language.getAllLessons(), "Lessons should not be null");
        assertEquals(LessonTopic.values().length, language.getAllLessons().size(), "Should have one lesson for each topic");
    }

    @Test
    void testGetAllLessons() {
        ArrayList<Lesson> lessons = language.getAllLessons();
        assertEquals(LessonTopic.values().length, lessons.size(), "Should return all lessons");
        
        // Check specific topics if needed
        for (LessonTopic topic : LessonTopic.values()) {
            assertNotNull(language.getLesson(topic), "Lesson for " + topic + " should exist");
        }
    }

    @Test
    void testGetLesson() {
        Lesson lesson = language.getLesson(LessonTopic.SCHOOL); // Assuming SCHOOL is a valid topic
        assertNotNull(lesson, "Should return the lesson for SCHOOL topic");
        assertEquals(LessonTopic.SCHOOL, lesson.getTopic(), "The topic of the lesson should be SCHOOL");
    }

    @Test
    void testSetAndGetDifficulty() {
        language.setDifficulty(LanguageDifficulty.MEDIUM); // Set difficulty to MEDIUM
        assertEquals(LanguageDifficulty.MEDIUM, language.getDifficulty(), "Difficulty should be set to MEDIUM");
        
        language.setDifficulty(LanguageDifficulty.HARD); // Set difficulty to HARD
        assertEquals(LanguageDifficulty.HARD, language.getDifficulty(), "Difficulty should be set to HARD");
    }

    @Test
    void testGetForeignLanguage() {
        assertEquals(ForeignLanguage.SPANISH, language.getForeignLanguage(), "Language should be Spanish");
    }
}