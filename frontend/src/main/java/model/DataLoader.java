package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The DataLoader class is responsible for loading in all user data
 * including their personal data, their saved settings, and their 
 * friends list from a user.json file. This class is also responsible
 * for loading all of the user's languages, their progress in the 
 * languages, and the incomplete lessons remaining
 * @author Tony Lin
 */
public class DataLoader extends DataConstants{

    /**
     * Loads all users, their user settings, and their friends
     * list into a UserList and returns the full UserList
     * @param userList2 
     * @return UserList containing all information from JSON file
     */
  public static UserList loadUsers(UserList userList) {
    try {
      BufferedReader reader = getReaderFromFile(USER_JSON, USER_JUNIT);
      JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
      for(Object userObj : usersJSON) {
        JSONObject userJSON = (JSONObject) userObj;
        loadPersonalData(userList, usersJSON);
        JSONArray languages = (JSONArray) userJSON.get(LANGUAGES);
        for(User user : userList.getAllUsers()) {
        loadProgress(user,languages);
      }
    }
      loadFriends(usersJSON,userList);
      reader.close();
      return userList;
    } catch (Exception e) {
        System.out.println(e);
        return null;
      }
    }

 /**
   * Returns a casted object of one of the lesson objects depending on 
   * what is passed in to differentiate the different lesson objects
   * @param object to be differentiated
   * @return casted version of the object 
   */
  public static Object getQuestionType(Object object) {
    if (object instanceof MultipleChoice) 
      return (MultipleChoice) object;
    else if (object instanceof FillBlank) 
      return (FillBlank) object;
    else if (object instanceof Matching) 
      return (Matching) object;
    else if (object instanceof Flashcard) 
      return (Flashcard) object;
    else if (object instanceof PictureStory) 
      return (PictureStory) object;
    return null;
  }


  /**
   * Reads in all words from dictionary.json and returns a dictionary
   * manager fully populated with all the words
   * @return dictionary manager with all words from dictionary.json
   */
  public static DictionaryManager loadDictionary(DictionaryManager dictionaryManager) {
    try {
      BufferedReader reader = getReaderFromFile(DICTIONARY_JSON, DICTIONARY_JUNIT);
      JSONArray jsonArray = (JSONArray)new JSONParser().parse(reader);
      for (int i = 0; i < 1; ++i) {
        JSONObject jsonObject = (JSONObject)jsonArray.get(i);
        JSONArray difficultyArray = (JSONArray) jsonObject.get(DIFFICULTY);
        for(Object difficultyObj : difficultyArray) {
          JSONObject difficultyLevel = (JSONObject)difficultyObj;
          LanguageDifficulty difficulty = LanguageDifficulty.fromString((String)difficultyLevel.get(DIFFICULTY));
          JSONArray wordsByTopicArray= (JSONArray) difficultyLevel.get(WORDSBYTOPIC);
            for(Object topicObj : wordsByTopicArray) {
              JSONObject topicEntry = (JSONObject) topicObj;
              LessonTopic topic = LessonTopic.fromString((String)topicEntry.get(TOPIC));
              JSONArray wordsArray = (JSONArray) topicEntry.get(WORDS);
              for(Object wordObj : wordsArray) {
                JSONObject wordEntry = (JSONObject) wordObj;
                String foreign = (String) wordEntry.get(SPANISH);
                String english = (String) wordEntry.get(ENGLISH);
                String meaning = (String) wordEntry.get(MEANING);
                dictionaryManager.addWord(difficulty, topic, foreign, english, meaning);
              }
            }
        }
    }
    reader.close();
    return dictionaryManager;
   } catch (Exception e) {
      System.out.println(e);
      return dictionaryManager;
    }
  }

  private static void loadFriends(JSONArray usersJSON, UserList userList) {
    for(int i = 0; i < usersJSON.size(); ++i) {
      JSONObject userJSON = (JSONObject)usersJSON.get(i);
      JSONArray userFRIENDS = (JSONArray)userJSON.get(USER_FRIENDS);
      if(userFRIENDS != null) {
      UUID userID = UUID.fromString((String)userJSON.get(USER_ID));
      for(Object friend : userFRIENDS) {
        JSONObject friendJSON = (JSONObject) friend;
        UUID friendID = UUID.fromString((String)friendJSON.get(USER_ID));
        userList.getUser(userID).addFriend(userList.getUser(friendID));
      }
    }
  }
  }

  private static void loadPersonalData(UserList userList, JSONArray usersJSON) {
    for (int i = 0; i < usersJSON.size(); ++i) {
      JSONObject userJSON = (JSONObject)usersJSON.get(i);
      JSONObject userSETTINGS = (JSONObject) userJSON.get(USER_SETTINGS);
      UUID userID = UUID.fromString((String)userJSON.get(USER_ID));
      String firstName = (String) userJSON.get(USER_FIRST_NAME);
      String lastName = (String) userJSON.get(USER_LAST_NAME);
      String userName = (String) userJSON.get(USER_USERNAME);
      String password = (String) userJSON.get(USER_PASSWORD);
      String email = (String) userJSON.get(USER_EMAIL);
      userList.addUser(firstName, lastName, userName, password, email, userID);
      User user = userList.getUser(userID);
      Long longNum = (Long) userSETTINGS.get(USER_NOTIFICATIONS);
      int updated = longNum.intValue();
      user.changeSetting(0, updated);
      longNum = (Long) userSETTINGS.get(USER_LIGHT_MODE);
      updated = longNum.intValue();
      user.changeSetting(1, updated);
      longNum = (Long) userSETTINGS.get(USER_TEXT_TO_SPEECH);
      updated = longNum.intValue();
      user.changeSetting(2, updated);
      longNum = (Long) userSETTINGS.get(USER_FONT_SIZE);
      updated = longNum.intValue();
      user.changeSetting(3, updated);
    }
  }

  private static void loadProgress(User user, JSONArray languages) {
    if(languages != null) {
      HashMap<LessonTopic, ArrayList<Object>> incompleteObjects = new HashMap<LessonTopic,ArrayList<Object>>();
      for(LessonTopic topic : LessonTopic.values()) {
        incompleteObjects.put(topic, new ArrayList<Object>());
      }
      for(Object langObj : languages) {
        JSONObject language = (JSONObject) langObj;  
        ForeignLanguage foreignLanguage = ForeignLanguage.fromString((String)language.get(FOREIGN_LANGUAGE));
        user.setLanguage(foreignLanguage);
        LanguageDifficulty difficulty = LanguageDifficulty.fromString((String)language.get(DIFFICULTY));
        user.setDifficulty(difficulty);
        Long longValue = (Long)language.get(MODULE);
        user.setModule(longValue.intValue());
        longValue = (Long)language.get(LANGUAGE_PROGRESS);
        user.setLanguageProgress(longValue.intValue());
        int languageProgress = 50;
        int module = 1;
        JSONArray incompleteArray = (JSONArray)language.get(INCOMPLETE);
        for(Object incompleteTopicObject : incompleteArray) {
          JSONObject incompleteTopic = (JSONObject) incompleteTopicObject;
          LessonTopic topic = LessonTopic.fromString((String)incompleteTopic.get(TOPIC));
          if(!incompleteTopic.containsKey(QUESTIONTYPE)) {
            user.complete(topic);
            module++;
            continue;
          }
          JSONArray questionTypes = (JSONArray) incompleteTopic.get(QUESTIONTYPE);
          Lesson tempNewLesson = new Lesson(difficulty,topic);
          for(Object object : questionTypes) {
            JSONObject question = (JSONObject) object;
            String type = String.valueOf(question.get(QUESTIONTYPE));
            --languageProgress;
            if(question.containsKey(QUESTION_ID))
              longValue = (long)question.get(QUESTION_ID);
            switch(type) {
              case MATCHING:
                incompleteObjects.get(topic).add(tempNewLesson.getMatching()); break;
              case FLASHCARD: 
                for(Flashcard card : tempNewLesson.getFlashcards()) {
                  incompleteObjects.get(topic).add(card);
                }
              case MULTIPLECHOICE:
                switch(longValue.intValue()) {
                  case 0:
                  incompleteObjects.get(topic).add(tempNewLesson.getMultipleChoice(0)); break;
                  case 1:
                  incompleteObjects.get(topic).add(tempNewLesson.getMultipleChoice(1)); break;
                  case 2:
                  incompleteObjects.get(topic).add(tempNewLesson.getMultipleChoice(2)); break;
                } break;
              case FILLBLANK:
                switch(longValue.intValue()) {
                case 0:
                incompleteObjects.get(topic).add(tempNewLesson.getFillBlank(0)); break;
                case 1:
                incompleteObjects.get(topic).add(tempNewLesson.getFillBlank(1)); break;
                case 2:
                incompleteObjects.get(topic).add(tempNewLesson.getFillBlank(2)); break;
              } break;
            }
          }
          user.setModule(module);
          user.setLanguageProgress(languageProgress);
          user.setIncomplete(topic, incompleteObjects.get(topic));
        }
      }
    }
  }

  private static BufferedReader getReaderFromFile(String fileName, String jsonFileName) {
    try {
      if(isJunitTest()) {
        InputStream inputStream = DataLoader.class.getResourceAsStream(jsonFileName);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
      } else {
        FileReader reader = new FileReader(fileName);
        return new BufferedReader(reader);
      }
    } catch (Exception e) {
      System.out.println(e);
      return null;
    } 
  }
  }