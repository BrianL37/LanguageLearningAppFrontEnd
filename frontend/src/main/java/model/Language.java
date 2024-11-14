package library;

import java.util.ArrayList;

/**
 * The class Language contains lessons, the difficulty,
 * and progress.
 * @author Tony Lin
 */
public class Language {
  private ArrayList<Lesson> lessons;
  private LanguageDifficulty difficulty;
  private ForeignLanguage foreignLanguage;

  /**
   * Parameterized constructor that takes a foreign language and 
   * difficulty and creates instance of language containing all of the 
   * lessons at that difficulty
   * @param difficulty of the Language
   * @param language What language should the instance be of (Spanish)
   */
  public Language(ForeignLanguage language, LanguageDifficulty difficulty) {
    this.lessons = new ArrayList<Lesson>();
    this.difficulty = difficulty;
    this.foreignLanguage = language;
    for(LessonTopic topic : LessonTopic.values()) {
      this.lessons.add(new Lesson(this.difficulty, topic));
    }
  }
  
  /** Sets this languages difficulty
   * @param difficulty to be set for this language
   */
  public void setDifficulty(LanguageDifficulty difficulty) {
    this.difficulty = difficulty;
  }
  
  /**
   * Returns the current selected difficulty of the language
   * @return difficulty of the language
   */
  public LanguageDifficulty getDifficulty() {
    return this.difficulty;
  }

  /**
   * Sets the languages lessons to the ArrayList<Lesson> that is
   * passed in (Used to load users previously started language)
   * @param lessons ArrayList of lessons to be set as this 
   * languages lessons
   */
  public void setLessons(ArrayList<Lesson> lessons) {
    this.lessons = lessons;
  }

  /**
   * Returns a collection of all lessons the user has not completed
   * @return ArrayList<Lesson> containing the users lessons
   */
  public ArrayList<Lesson> getAllLessons() {
    return this.lessons;
  }

  /** Returns what language this language class currently is
   * ie (Spanish)
   * @return ForeignLanguage the type of language fo this class
   */
  public ForeignLanguage getForeignLanguage() {
    return this.foreignLanguage;
  }
  
  /** Will return a specific lesson based on which topic 
   * the user wants to complete
   * @param topic of the lesson requested
   * @return Lesson for the user to complete
   */
  public Lesson getLesson(LessonTopic topic) {
    for(Lesson lesson : lessons) {
      if(lesson.getTopic().equals(topic))
        return lesson;
    }
    return null;
  }
  

}