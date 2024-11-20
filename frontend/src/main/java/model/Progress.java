package library;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Progress class tracks a the user's progress through a language, 
 * progress through each lesson in the langauge, all of their 
 * incomplete questions, as well as their trouble questions.
 * @author Tony Lin and Brian Lee
 */
public class Progress{
  private HashMap<LessonTopic, Integer> lessonProgress;
  private HashMap<LessonTopic, ArrayList<Object>> incomplete;
  private HashMap<LessonTopic, HashMap<Question,Integer>> trouble;
  private int languageProgress;
  private int module;
  private LanguageDifficulty difficulty;
  private ForeignLanguage language;
/** 
 * Default constructor that initializes the HashMaps tracking the 
 * user's progress and initializes langaugeProgress to 0
 */
  public Progress(){
    this.lessonProgress = new HashMap<>();
    this.incomplete = new HashMap<>();
    this.trouble = new HashMap<>();
    for(LessonTopic topic : LessonTopic.values()) {
      this.lessonProgress.put(topic, 0);
      this.trouble.put(topic, new HashMap<>());
    }
    this.module = 1;
    this.languageProgress = 0;
   }

   /**
    * Adds a ArrayList<Question> to the stored collection of incomplete
    * questions
    * @param topic of the lesson
    * @param questions ArrayList<Question> of the incomplete questions
    */
  public void setIncomplete(LessonTopic topic, ArrayList<Object> questions) {
    this.incomplete.put(topic, questions);
    this.lessonProgress.put(topic, 8 - questions.size());
  }

  /**
   * Returns a ArrayList<Question> containing all questions of a 
   * lesson that the user has not completed
   * @param topic of the lesson
   * @return ArrayList<Question> of the incompleted questions
   */
  public ArrayList<Object> getIncomplete(LessonTopic topic){
    return this.incomplete.get(topic);
    }

    /**
     * Updates the user's language progress and lesson progress 
     * while also removing the completed question from the incomplete
     * questions collection
     * Mainly used for FillBlank, MultipleChoice, Matching
     */
  public void updateCorrect(LessonTopic topic, Object question) {
      if(this.lessonProgress.get(topic) < 8) {
    try {
      if(this.incomplete.get(topic).contains(question)) {
        this.languageProgress++;
        this.lessonProgress.put(topic, this.lessonProgress.get(topic) + 1);
        this.incomplete.get(topic).remove(question);
        this.trouble.get(topic).remove(question);
      }
    } catch (Exception e) {
    }
    }
  }

  /**
   * Adds a question to the incomplete questions HashMap
   * @param topic LessonTopic of the incomplete question
   * @param question that is incomplete
   */
  public void updateIncomplete(LessonTopic topic, Object toAdd) {
    for(Question question : this.trouble.get(topic).keySet()) {
      System.out.println("hi");
        if(!this.trouble.get(topic).containsKey(question)) {
          this.trouble.get(topic).put(question, 1);
        }
        else
          this.trouble.get(topic).put(question, this.trouble.get(topic).get(question) + 1);
    }
    this.incomplete.get(topic).add(toAdd);
  }

  /**
   * Returns the overall progress the user has made through an entire
   * language
   * @return int representing how many total questions the user has 
   * completed in a language
   */
  public int getLanguageProgress() {
    return this.languageProgress;
}

/**
 * Returns the lesson progress for a given lessontopic
 * @param topic of the lesson
 * @return int representing how many questions the user has completed
 * in that lesson
 */
  public int getLessonProgress(LessonTopic topic) {
    return this.lessonProgress.get(topic);
  }

  /**
   * Removes the trouble question from the collection
   * @param question to be removed
   */
  public void removeTrouble(LessonTopic topic, Question question) {
    this.trouble.get(topic).remove(question);
  }

  /**
   * Sets the users troubled problems to the argument passed in
   * @param map HashMap of problems to be set as users trouble problems
   */
  public void setTrouble(HashMap<LessonTopic, HashMap<Question, Integer>>map) {
    this.trouble = map;
  }

  /**
   * Returns a HashMap containing all of the user's troubled problems
   * @return HashMap of user's trouble problems
   */
  public HashMap<LessonTopic,HashMap<Question,Integer>> getTrouble() {
    return this.trouble;
  }

  public void setModule(int num) {
    this.module = num;
  }

  public int getModule() {
    return this.module;
  }

  public void completeLesson(LessonTopic topic) {
    this.lessonProgress.put(topic, 8);
    this.incomplete.remove(topic);
    ++this.module;
  }

  public void setLanguageProgress(int progress) {
    this.languageProgress = progress;
  }
  public LanguageDifficulty getDifficulty() {
    return this.difficulty;
  }

  public void setDifficulty(LanguageDifficulty difficulty) {
    this.difficulty = difficulty;
  }
  public void setLanguage(ForeignLanguage language) {
    this.language = language;
  }

  public ForeignLanguage getLanguage() {
    return this.language; 
  }
}