package model;

public class DataConstants {
  public static final String USER_JUNIT = "../data/user.json";
  public static final String DICTIONARY_JUNIT = "../data/dictionary.json";
  public static final String USER_JSON = "frontend/src/main/java/data/user.json";
  public static final String DICTIONARY_JSON = "frontend/src/main/java/data/dictionary.json";
  public static final String USER_ID = "uuid";
  public static final String USER_FIRST_NAME = "first-name";
  public static final String USER_LAST_NAME = "last-name";
  public static final String USER_USERNAME = "username";
  public static final String USER_EMAIL = "email";
  public static final String USER_PASSWORD = "password";
  public static final String USER_LANGUAGES = "languages";
  public static final String DICTIONARY = "dictionary";
  public static final String MEANING = "meaning";
  public static final String WORDSBYTOPIC = "wordsbytopic";
  public static final String ENGLISH = "english";
  public static final String DIFFICULTY = "difficulty";
  public static final String EASY = "EASY";
  public static final String MEDIUM = "MEDIUM";
  public static final String HARD = "HARD";
  public static final String MODULE = "module";
  public static final String LANGUAGES = "languages";
  public static final String FOREIGN_LANGUAGE = "foreign-language";
  public static final String USER_SETTINGS = "user-settings";
  public static final String TROUBLE = "trouble";
  public static final String INCORRECT = "incorrect";
  public static final String INCOMPLETE = "incomplete";
  public static final String USER_NOTIFICATIONS = "notifications";
  public static final String USER_LIGHT_MODE = "light-mode";
  public static final String USER_TEXT_TO_SPEECH = "text-to-speech";
  public static final String USER_FONT_SIZE = "font-size";
  public static final String USER_FRIENDS = "friends";
  public static final String LESSONS_COMPLETED = "lessons-completed";
  public static final String LANGUAGE_PROGRESS = "language-progress";
  public static final String QUESTION_ID = "question-id";
  public static final String SPANISH = "spanish";
  public static final String LESSONS = "lessons";
  public static final String QUESTIONS = "questions";
  public static final String QUESTIONTYPE = "question-type";
  public static final String FLASHCARD= "flashcard";
  public static final String MATCHING = "matching";
  public static final String STORY = "story";
  public static final String MULTIPLECHOICE = "multiplechoice";
  public static final String FILLBLANK = "fillblank";
  public static final String TOPIC = "topic";
  public static final String WORDS = "words";
  public static final String SCHOOL = "school";
  public static final String FAMILY = "family";
  public static final String WEATHER = "weather";
  public static final String PETS = "pets";
  public static final String FOOD = "food";
  public static boolean isJunitTest() {
    for(StackTraceElement element : Thread.currentThread().getStackTrace()) {
      if(element.getClassName().startsWith("org.junit.")) {
        return true;
      }
    }
    return false;
  }
}
