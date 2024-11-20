package library;

public class DataConstants {
  protected static final String USER_JUNIT = "../data/user.json";
  protected static final String DICTIONARY_JUNIT = "../data/dictionary.json";
  protected static final String USER_JSON = "src/main/java/data/user.json";
  protected static final String DICTIONARY_JSON = "src/main/java/data/dictionary.json";
  protected static final String USER_ID = "uuid";
  protected static final String USER_FIRST_NAME = "first-name";
  protected static final String USER_LAST_NAME = "last-name";
  protected static final String USER_USERNAME = "username";
  protected static final String USER_EMAIL = "email";
  protected static final String USER_PASSWORD = "password";
  protected static final String USER_LANGUAGES = "languages";
  protected static final String DICTIONARY = "dictionary";
  protected static final String MEANING = "meaning";
  protected static final String WORDSBYTOPIC = "wordsbytopic";
  protected static final String ENGLISH = "english";
  protected static final String DIFFICULTY = "difficulty";
  protected static final String EASY = "EASY";
  protected static final String MEDIUM = "MEDIUM";
  protected static final String HARD = "HARD";
  protected static final String MODULE = "module";
  protected static final String LANGUAGES = "languages";
  protected static final String FOREIGN_LANGUAGE = "foreign-language";
  protected static final String USER_SETTINGS = "user-settings";
  protected static final String TROUBLE = "trouble";
  protected static final String INCORRECT = "incorrect";
  protected static final String INCOMPLETE = "incomplete";
  protected static final String USER_NOTIFICATIONS = "notifications";
  protected static final String USER_LIGHT_MODE = "light-mode";
  protected static final String USER_TEXT_TO_SPEECH = "text-to-speech";
  protected static final String USER_FONT_SIZE = "font-size";
  protected static final String USER_FRIENDS = "friends";
  protected static final String LESSONS_COMPLETED = "lessons-completed";
  protected static final String LANGUAGE_PROGRESS = "language-progress";
  protected static final String QUESTION_ID = "question-id";
  protected static final String SPANISH = "spanish";
  protected static final String LESSONS = "lessons";
  protected static final String QUESTIONS = "questions";
  protected static final String QUESTIONTYPE = "question-type";
  protected static final String FLASHCARD= "flashcard";
  protected static final String MATCHING = "matching";
  protected static final String STORY = "story";
  protected static final String MULTIPLECHOICE = "multiplechoice";
  protected static final String FILLBLANK = "fillblank";
  protected static final String TOPIC = "topic";
  protected static final String WORDS = "words";
  protected static final String SCHOOL = "school";
  protected static final String FAMILY = "family";
  protected static final String WEATHER = "weather";
  protected static final String PETS = "pets";
  protected static final String FOOD = "food";
  public static boolean isJunitTest() {
    for(StackTraceElement element : Thread.currentThread().getStackTrace()) {
      if(element.getClassName().startsWith("org.junit.")) {
        return true;
      }
    }
    return false;
  }
}
