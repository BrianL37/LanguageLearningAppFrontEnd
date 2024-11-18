package library;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.UUID;
import javax.swing.plaf.multi.MultiPanelUI;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The DataWriter is responsible for writing all stored data into 
 * the respective json files.
 */
public class DataWriter extends DataConstants{
  /**
   * Saves all users to user.json as well as all relevant
   * user information including: personal information, language
   * progress, friend list, and personal settings
   * @param userList
   */
  public static void saveUsers(ArrayList<User> userList) {
    try {
    JSONArray usersJSON = new JSONArray();
    for(User user : userList) {
      JSONObject userJSON = new JSONObject();
      savePersonalData(user, userJSON);
      saveFriends(user, userJSON);
      saveProgress(user, userJSON);
      usersJSON.add(userJSON);
    }   
      String filePath = getFileWritingPath(USER_JSON, USER_JUNIT);
      String current = new File(".").getAbsolutePath();
      System.out.println(current);
      FileWriter writer = new FileWriter(filePath);
      writer.write(usersJSON.toJSONString());
      writer.flush();
    } catch (Exception e) {
        System.out.println(e);
    }
  }

  private static void saveFriends(User user, JSONObject userJSON) {
    JSONArray friendsJSON = new JSONArray();
    ArrayList<User> friendList = user.getFriendList();
    for(User friend : friendList) {
      JSONObject friendJSON = new JSONObject();
      friendJSON.put(USER_ID, friend.getUserID().toString());
      friendJSON.put(USER_USERNAME, friend.getUserName());
      friendsJSON.add(friendJSON);
    }
    userJSON.put(USER_FRIENDS, friendsJSON);
  }

  private static void saveProgress(User user, JSONObject userJSON) {
    if(user.getLanguage()!= null) {
      ArrayList<Object> addedQuestions = new ArrayList<>();
      JSONArray languagesArray = new JSONArray();
      JSONObject spanishObject = new JSONObject();
      spanishObject.put(FOREIGN_LANGUAGE, String.valueOf(user.getLanguage()));
      spanishObject.put(LANGUAGE_PROGRESS, user.getLanguageProgress());
      switch(user.getDifficulty()){
        case EASY: spanishObject.put(DIFFICULTY, EASY); break;
        case MEDIUM: spanishObject.put(DIFFICULTY, MEDIUM); break;
        case HARD: spanishObject.put(DIFFICULTY, HARD); break;
      }
      spanishObject.put(MODULE, user.getModule());
      JSONArray troubleArray = new JSONArray();
      for(LessonTopic topic : LessonTopic.values()) {
        HashMap<Question,Integer> map = user.getTrouble().get(topic);
        for(Question question : map.keySet()) {
          JSONObject troubleObject = new JSONObject();
          troubleObject.put(TOPIC, topic);
          troubleObject.put(QUESTIONTYPE, getQuestionType(question));
          if(getQuestionType(question) instanceof FillBlank) {
            FillBlank toSave = (FillBlank)question;
            troubleObject.put(QUESTION_ID, toSave.getId());
          }
          else if(getQuestionType(question) instanceof MultipleChoice) {
            MultipleChoice toSave = (MultipleChoice) question;
            troubleObject.put(QUESTION_ID, toSave.getId());
          }
          troubleObject.put(INCORRECT, map.get(question));
          troubleArray.add(troubleObject);
        }
      }
      spanishObject.put(TROUBLE,troubleArray);
       JSONArray incompleteArray = new JSONArray();
       for(LessonTopic topic : LessonTopic.values()) {
        JSONObject topicObject = new JSONObject();
        switch(topic) {
          case SCHOOL: topicObject.put(TOPIC, SCHOOL); break;
          case FAMILY: topicObject.put(TOPIC, FAMILY); break;
          case WEATHER: topicObject.put(TOPIC, WEATHER); break;
          case FOOD: topicObject.put(TOPIC, FOOD); break;
          case PETS: topicObject.put(TOPIC, PETS); break;
         }
         if((user.getIncomplete(topic) == null)) {
          System.out.println(topic);
          incompleteArray.add(topicObject);
          continue;
         }
        else if(!(user.getIncomplete(topic) == null)) {
         JSONArray questionTypes = new JSONArray();
         for(Object object : user.getIncomplete(topic)) {
          if(addedQuestions.contains(object)) {
            continue;
          }
          JSONObject questionObj = new JSONObject();
            if(object instanceof Matching) {
              questionObj.put(QUESTIONTYPE, MATCHING);
            }
            else if(object instanceof FillBlank) {
              FillBlank toAdd = (FillBlank)object;
              questionObj.put(QUESTIONTYPE,FILLBLANK);
              questionObj.put(QUESTION_ID, toAdd.getId());
            }
            else if(object instanceof MultipleChoice) {
              MultipleChoice toAdd = (MultipleChoice)object;
              questionObj.put(QUESTIONTYPE, MULTIPLECHOICE);
              questionObj.put(QUESTION_ID, toAdd.getId());
            }
            else if(object instanceof Flashcard) {
              questionObj.put(QUESTIONTYPE, FLASHCARD);
            }
            questionTypes.add(questionObj);
            addedQuestions.add(object);
         }
         topicObject.put(QUESTIONTYPE,questionTypes);
         incompleteArray.add(topicObject);
        }
       }
       spanishObject.put(TROUBLE, troubleArray);
       spanishObject.put(INCOMPLETE, incompleteArray);
       languagesArray.add(spanishObject);
       userJSON.put(USER_LANGUAGES,languagesArray);
      }
  }

  private static void savePersonalData (User user, JSONObject userJSON) {
    userJSON.put(USER_ID, user.getUserID().toString());
    userJSON.put(USER_FIRST_NAME, user.getFirstName());
    userJSON.put(USER_LAST_NAME, user.getLastName());
    userJSON.put(USER_USERNAME, user.getUserName());
    userJSON.put(USER_PASSWORD, user.getPassword());
    userJSON.put(USER_EMAIL, user.getEmail());
    JSONObject userSettings = new JSONObject();
    userSettings.put(USER_NOTIFICATIONS, Integer.valueOf(user.getSettings().getNotifications()));
    userSettings.put(USER_LIGHT_MODE, Integer.valueOf(user.getSettings().getLightMode()));
    userSettings.put(USER_TEXT_TO_SPEECH, Integer.valueOf(user.getSettings().getTextToSpeech()));
    userSettings.put(USER_FONT_SIZE, Integer.valueOf(user.getSettings().getFontSize()));
    userJSON.put(USER_SETTINGS, userSettings);
  }

  /**
   * Returns a casted object of one of the lesson objects depending on 
   * what is passed in to differentiate the different lesson objects
   * @param object to be differentiated
   * @return casted version of the object 
   */
  private static Object getQuestionType(Object object) {
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

  private static String getFileWritingPath(String filePath, String junitFilePath) {
    try {
      if(isJunitTest()) {
        URI url = DataWriter.class.getResource(junitFilePath).toURI();
        return url.getPath();
      } else {
        return filePath;
      }
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }
}
