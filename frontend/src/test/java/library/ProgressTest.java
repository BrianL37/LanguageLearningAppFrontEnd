package library;
/**
 * @Author Brian Lee
 * 
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

class ProgressTest {

    private Progress progress;
    private final LessonTopic topic = LessonTopic.SCHOOL; 

    @BeforeEach
    void setUp() {
        progress = new Progress();
    }

    @Test
    void testSetIncomplete() {
        ArrayList<Object> questions = new ArrayList<>();
        questions.add(new QuestionMock("What is 'clase' in English?")); 
        questions.add(new QuestionMock("What is 'libro' in English?")); 

        progress.setIncomplete(topic, questions);

        assertEquals(6, progress.getLessonProgress(topic)); // Assuming 8 total questions per lesson
        assertEquals(questions, progress.getIncomplete(topic));
    }

    @Test
    void testUpdateCorrect() {
        ArrayList<Object> questions = new ArrayList<>();
        Object question = new QuestionMock("A piece of furniture with a flat surface for writing or reading."); 
        questions.add(question);
        progress.setIncomplete(topic, questions);
    
        // The lesson progress should initially be 7 (8 total - 1 incomplete)
        assertEquals(7, progress.getLessonProgress(topic));
    
        progress.updateCorrect(topic, question);
    
        // Now that one question is marked as correct, progress should be 8 (completed all)
        assertEquals(8, progress.getLessonProgress(topic));
        assertFalse(progress.getIncomplete(topic).contains(question));
    }

    @Test
    void testUpdateIncomplete() {
        Object question = new QuestionMock("A set of written or printed pages, bound together.");
        
        // Initialize the incomplete list for the topic before updating
        ArrayList<Object> initialIncompleteQuestions = new ArrayList<>();
        progress.setIncomplete(topic, initialIncompleteQuestions); // Ensure the topic is set up
    
        // Update the incomplete questions with a new question
        progress.updateIncomplete(topic, question);
    
        // Verify that the question has been added to the incomplete list
        assertTrue(progress.getIncomplete(topic).contains(question));
    }

    @Test
    void testGetLanguageProgress() {
        // Set up the initial state by initializing the incomplete questions
        ArrayList<Object> initialIncompleteQuestions = new ArrayList<>();
        Object question = new QuestionMock("What is 'mesa' in English?"); 
        initialIncompleteQuestions.add(question);
        progress.setIncomplete(topic, initialIncompleteQuestions); // Ensure the topic is set up

        // Confirm the initial language progress is 0
        assertEquals(0, progress.getLanguageProgress());

        // Now mark the question as correct
        progress.updateCorrect(topic, question);

        // Assert the language progress has increased
        assertEquals(1, progress.getLanguageProgress());
    }

    @Test
    void testCompleteLesson() {
        ArrayList<Object> questions = new ArrayList<>();
        questions.add(new QuestionMock("What is 'lapiz' in English?")); 
        progress.setIncomplete(topic, questions);

        progress.completeLesson(topic);

        assertEquals(8, progress.getLessonProgress(topic)); // All questions marked as complete
        assertNull(progress.getIncomplete(topic)); // Incomplete should be removed
    }

    @Test
    void testRemoveTrouble() {
        HashMap<Question, Integer> troubleMap = new HashMap<>();
        Question troubleQuestion = new QuestionMock("What is escuela in english?"); 
        troubleMap.put(troubleQuestion, 1);
        progress.setTrouble(new HashMap<>() {{ put(topic, troubleMap); }});

        progress.removeTrouble(topic, troubleQuestion);

        assertFalse(progress.getTrouble().get(topic).containsKey(troubleQuestion));
    }

    @Test
    void testSetAndGetLanguageProgress() {
        progress.setLanguageProgress(5);
        assertEquals(5, progress.getLanguageProgress());
    }

    @Test
    void testSetAndGetDifficulty() {
        LanguageDifficulty difficulty = LanguageDifficulty.EASY; 
        progress.setDifficulty(difficulty);
        assertEquals(difficulty, progress.getDifficulty());
    }

    // Mock class for testing
    private static class QuestionMock implements Question {
        private final String question;

        public QuestionMock(String question) {
            this.question = question;
        }

        @Override
        public String toString() {
            return question;
        }
    }
}
