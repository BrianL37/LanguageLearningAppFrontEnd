package library;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ author Dejuan Carson

class DataWriterTest {

    private ArrayList<User> mockUserList;
    private User mockUser;

    @BeforeEach
    void setUp() {
        // Setting up a mock user for testing
        mockUser = mock(User.class);
        when(mockUser.getUserID()).thenReturn(UUID.randomUUID());
        when(mockUser.getFirstName()).thenReturn("John");
        when(mockUser.getLastName()).thenReturn("Doe");
        when(mockUser.getUserName()).thenReturn("johndoe");
        when(mockUser.getPassword()).thenReturn("password123");
        when(mockUser.getEmail()).thenReturn("john.doe@example.com");

        // Setting up mock settings for the user
        Settings mockSettings = mock(Settings.class);
        when(mockSettings.getNotifications()).thenReturn(1);
        when(mockSettings.getLightMode()).thenReturn(1);
        when(mockSettings.getTextToSpeech()).thenReturn(0);
        when(mockSettings.getFontSize()).thenReturn(12);
        when(mockUser.getSettings()).thenReturn(mockSettings);

        // Mocking user languages, progress, and friends
        when(mockUser.getLanguage()).thenReturn(ForeignLanguage.SPANISH);
        when(mockUser.getLanguageProgress()).thenReturn(50);
        when(mockUser.getDifficulty()).thenReturn(LanguageDifficulty.MEDIUM);
        when(mockUser.getModule()).thenReturn(2);

        // Mocking user friends
        ArrayList<User> friendsList = new ArrayList<>();
        User mockFriend = mock(User.class);
        when(mockFriend.getUserID()).thenReturn(UUID.randomUUID());
        when(mockFriend.getUserName()).thenReturn("janedoe");
        friendsList.add(mockFriend);
        when(mockUser.getFriendList()).thenReturn(friendsList);

        // Mocking user trouble topics and incomplete topics
        HashMap<LessonTopic, HashMap<Question, Integer>> mockTrouble = new HashMap<>();
        when(mockUser.getTrouble()).thenReturn(mockTrouble);
        
        HashMap<LessonTopic, ArrayList<Object>> mockIncomplete = new HashMap<>();
        when(mockUser.getIncomplete(any())).thenReturn(new ArrayList<>());

        // Adding user to user list
        mockUserList = new ArrayList<>();
        mockUserList.add(mockUser);
    }

    @Test
    void testSaveUsers() {
        // Run saveUsers method
        DataWriter.saveUsers(mockUserList);

        // Check if user.json or test file was written correctly
        String filePath = "path/to/test_user.json"; // Adjust this path for testing purposes
        File file = new File(filePath);
        assertTrue(file.exists(), "The JSON file should be created.");

        // Verify the contents of the JSON file
        try (FileReader reader = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(reader);

            JSONObject userJSON = (JSONObject) ((JSONArray) json.get("users")).get(0);
            assertEquals("John", userJSON.get("firstName"), "First name should match.");
            assertEquals("Doe", userJSON.get("lastName"), "Last name should match.");
            assertEquals("johndoe", userJSON.get("userName"), "Username should match.");
            assertEquals("password123", userJSON.get("password"), "Password should match.");
            assertEquals("john.doe@example.com", userJSON.get("email"), "Email should match.");
            
            // Verify settings
            JSONObject settings = (JSONObject) userJSON.get("settings");
            assertEquals(1, settings.get("notifications"), "Notifications setting should match.");
            assertEquals(1, settings.get("lightMode"), "Light mode setting should match.");
            assertEquals(0, settings.get("textToSpeech"), "Text-to-speech setting should match.");
            assertEquals(12, settings.get("fontSize"), "Font size setting should match.");
            
        } catch (Exception e) {
            fail("Exception occurred while reading the JSON file: " + e.getMessage());
        } finally {
            file.delete(); // Cleanup the test file after verification
        }
    }

    @Test
    void testSavePersonalData() {
        // Create a JSONObject to hold personal data
        JSONObject userJSON = new JSONObject();

        // Call savePersonalData method
        DataWriter.savePersonalData(mockUser, userJSON);

        // Verify that userJSON contains the expected data
        assertEquals("John", userJSON.get("firstName"), "First name should be saved correctly.");
        assertEquals("Doe", userJSON.get("lastName"), "Last name should be saved correctly.");
        assertEquals("johndoe", userJSON.get("userName"), "Username should be saved correctly.");
        assertEquals("password123", userJSON.get("password"), "Password should be saved correctly.");
        assertEquals("john.doe@example.com", userJSON.get("email"), "Email should be saved correctly.");
    }

    @Test
    void testSaveFriends() {
        // Create a JSONObject to hold user data
        JSONObject userJSON = new JSONObject();

        // Call saveFriends method
        DataWriter.saveFriends(mockUser, userJSON);

        // Verify that userJSON contains friends information
        JSONArray friendsJSON = (JSONArray) userJSON.get("friends");
        JSONObject friendJSON = (JSONObject) friendsJSON.get(0);

        assertEquals("janedoe", friendJSON.get("userName"), "Friend's username should match.");
    }

    @Test
    void testGetQuestionType() {
        // Test for each question type by creating mock objects for each
        MultipleChoice mockMCQ = mock(MultipleChoice.class);
        FillBlank mockFillBlank = mock(FillBlank.class);
        Matching mockMatching = mock(Matching.class);
        Flashcard mockFlashcard = mock(Flashcard.class);
        PictureStory mockPictureStory = mock(PictureStory.class);

        assertEquals(mockMCQ, DataWriter.getQuestionType(mockMCQ), "MultipleChoice should be recognized.");
        assertEquals(mockFillBlank, DataWriter.getQuestionType(mockFillBlank), "FillBlank should be recognized.");
        assertEquals(mockMatching, DataWriter.getQuestionType(mockMatching), "Matching should be recognized.");
        assertEquals(mockFlashcard, DataWriter.getQuestionType(mockFlashcard), "Flashcard should be recognized.");
        assertEquals(mockPictureStory, DataWriter.getQuestionType(mockPictureStory), "PictureStory should be recognized.");
    }

    @Test
    void testGetFileWritingPath() {
        // Test file writing path for normal and JUnit test cases
        String path = DataWriter.getFileWritingPath("user.json", "test_user.json");
        assertNotNull(path, "File path should not be null.");
        assertTrue(path.contains("user.json") || path.contains("test_user.json"), "File path should be valid.");
    }
}
