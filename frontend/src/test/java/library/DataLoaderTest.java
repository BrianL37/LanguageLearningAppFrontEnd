package library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.UUID;
import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@Author Dejuan Carson

class DataLoaderTest {

    private UserList mockUserList;
    private DictionaryManager mockDictionaryManager;
    private BufferedReader mockReader;
    
    @BeforeEach
    void setUp() {
        mockUserList = new UserList();
        mockDictionaryManager = new DictionaryManager();
        mockReader = mock(BufferedReader.class);
    }

    @Test
    void testLoadUsers() {
        // Mocking JSON data structure
        JSONArray usersJSON = new JSONArray();
        JSONObject userJSON = new JSONObject();
        userJSON.put("userID", UUID.randomUUID().toString());
        userJSON.put("firstName", "John");
        userJSON.put("lastName", "Doe");
        userJSON.put("userName", "johndoe");
        userJSON.put("password", "password123");
        userJSON.put("email", "john.doe@example.com");
        usersJSON.add(userJSON);

        // Assuming loadUsers uses the mockReader
        UserList userList = DataLoader.loadUsers(mockUserList);
        
        // Validating UserList contains expected data
        assertNotNull(userList);
        assertEquals(1, userList.getAllUsers().size());
        User user = userList.getAllUsers().get(0);
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    @Test
    void testGetQuestionType() {
        MultipleChoice mockMultipleChoice = new MultipleChoice();
        FillBlank mockFillBlank = new FillBlank();
        Matching mockMatching = new Matching();
        Flashcard mockFlashcard = new Flashcard();
        PictureStory mockPictureStory = new PictureStory();

        assertEquals(mockMultipleChoice, DataLoader.getQuestionType(mockMultipleChoice));
        assertEquals(mockFillBlank, DataLoader.getQuestionType(mockFillBlank));
        assertEquals(mockMatching, DataLoader.getQuestionType(mockMatching));
        assertEquals(mockFlashcard, DataLoader.getQuestionType(mockFlashcard));
        assertEquals(mockPictureStory, DataLoader.getQuestionType(mockPictureStory));
    }

    @Test
    void testLoadDictionary() {
        // Mocking JSON structure for dictionary
        JSONArray dictionaryJSON = new JSONArray();
        JSONObject dictionaryEntry = new JSONObject();
        dictionaryEntry.put("difficulty", "Easy");
        dictionaryEntry.put("topic", "Greetings");
        dictionaryEntry.put("spanish", "Hola");
        dictionaryEntry.put("english", "Hello");
        dictionaryEntry.put("meaning", "A greeting");
        dictionaryJSON.add(dictionaryEntry);

        DictionaryManager dictionaryManager = DataLoader.loadDictionary(mockDictionaryManager);
        
        assertNotNull(dictionaryManager);
        assertTrue(dictionaryManager.hasWord("Hola"));
    }

    @Test
    void testLoadFriends() {
        // Mocking user JSON data for friends
        JSONArray usersJSON = new JSONArray();
        JSONObject userJSON = new JSONObject();
        userJSON.put("userID", UUID.randomUUID().toString());
        JSONArray friendsJSON = new JSONArray();
        JSONObject friendJSON = new JSONObject();
        friendJSON.put("userID", UUID.randomUUID().toString());
        friendsJSON.add(friendJSON);
        userJSON.put("userFriends", friendsJSON);
        usersJSON.add(userJSON);

        DataLoader.loadFriends(usersJSON, mockUserList);

        assertNotNull(mockUserList);
        assertEquals(1, mockUserList.getAllUsers().get(0).getFriendList().size());
    }

    @Test
    void testLoadPersonalData() {
        // Mocking user JSON structure
        JSONArray usersJSON = new JSONArray();
        JSONObject userJSON = new JSONObject();
        userJSON.put("userID", UUID.randomUUID().toString());
        userJSON.put("firstName", "Jane");
        userJSON.put("lastName", "Doe");
        userJSON.put("userName", "janedoe");
        userJSON.put("password", "pass1234");
        userJSON.put("email", "jane.doe@example.com");
        
        DataLoader.loadPersonalData(mockUserList, usersJSON);

        assertEquals(1, mockUserList.getAllUsers().size());
        User user = mockUserList.getAllUsers().get(0);
        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    @Test
    void testLoadProgress() {
        // Mocking JSON structure for languages and user
        User mockUser = new User("John", "Doe", "johndoe", "password", "john.doe@example.com", null);
        JSONArray languagesJSON = new JSONArray();
        JSONObject languageJSON = new JSONObject();
        languageJSON.put("foreignLanguage", "Spanish");
        languageJSON.put("difficulty", "Intermediate");
        languageJSON.put("module", 1L);
        languageJSON.put("languageProgress", 75L);
        languagesJSON.add(languageJSON);

        DataLoader.loadProgress(mockUser, languagesJSON);

        assertEquals("Spanish", mockUser.getLanguage().toString());
        assertEquals("Intermediate", mockUser.getDifficulty().toString());
        assertEquals(1, mockUser.getModule());
        assertEquals(75, mockUser.getLanguageProgress());
    }

    @Test
    void testGetReaderFromFile() {
        // Testing file reading functionality
        BufferedReader reader = DataLoader.getReaderFromFile("testfile.json", "testfile.json");
        assertNotNull(reader);
    }
}
