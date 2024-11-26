package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.HashMap;

/**
 * The Lesson class contains a topic, questions, flashcards,
 *  and a picture story.
 * @author Tony Lin and Ishaan Cheema
 */
public class Lesson {
  private LessonTopic topic;
  private ArrayList<Object> questions;
  private LanguageDifficulty difficulty;
  private static DictionaryManager manager;
  private ArrayList<Word> words;

  /**
   * Parameterized Constructor for Lesson that creates a 
   * ArrayList<Object> that has a story,matching, 3 flashcards,
   * 3 MultipleChoice, and 3 FillBlank questions
   * @param difficulty Difficulty of the questions
   * @param topic Topic of the questions
   */
  public Lesson(LanguageDifficulty difficulty, LessonTopic topic) {
    manager = DictionaryManager.getInstance();
    this.difficulty = difficulty;
    this.topic = topic;
    this.questions = new ArrayList<>();
    this.words = manager.getWordsByTopic(difficulty, topic);
    createFlashCard();
    createMatching();
    createMultipleChoice();
    createFillBlank();
    //this.story = new PictureStory();
  }

  /**
   * Creates 3 flashcards of the 3 vocab words per lesson topic 
   * and adds them to this.questions
   */
  private void createFlashCard() {
    ArrayList<Word> words = manager.getWordsByTopic(this.difficulty, this.topic);
    for(Word word : words) {
      Flashcard card = new Flashcard(word);
      this.questions.add(card);
    }
  }

  /**
   * Returns true or false to if the user selected the correct option
   * from the MultipleChoice
   * @param multipleChoice The MultipleChoice the user answered
   * @param answer The users selected answer
   * @return Boolean true or false
   */
  public boolean checkMultipleChoice(MultipleChoice multipleChoice, int answer) {
    if(this.questions.contains(multipleChoice))
    for(Object question : this.questions)
      if(question.equals(multipleChoice)){
        MultipleChoice choice = (MultipleChoice)question;
        ArrayList<Word> correctanswer = choice.getAnswer();
        return multipleChoice.getAnswers().get(answer).equals(correctanswer.get(0));
      }
    return false;
  }

  /**
   * Returns formatted string containing the 4 answer choices for
   * the MultipleChoice question
   * @param multipleChoice The MultipleChoice to be formatted
   * @return String formatted with the answer choices
   */
  public String multipleChoiceAnswers(MultipleChoice multipleChoice) {
    StringBuilder toReturn = new StringBuilder();
    for(Object question : this.questions)
      if(question.equals(multipleChoice)){
        MultipleChoice choice = (MultipleChoice)question;
        ArrayList<Word> answers = choice.getAnswers();
        for(int i = 0; i < 4; ++i){
          toReturn.append(answers.get(i).getForeign());
          toReturn.append("\n");
        }
        return toReturn.toString();
    }
    return null;
  }

  /**
   * Returns formatted string of the MultipleChoice prompt 
   * @param multipleChoice The MultipleChoice to be formatted
   * @return String formatted and containing prompt of MultipleChoice
   */
  public String multipleChoicePrompt(MultipleChoice multipleChoice) {
    StringBuilder prompt = new StringBuilder();
    for(Object object : this.questions)
      if(object.equals(multipleChoice)){
        MultipleChoice choice = (MultipleChoice)object;
        for(Word word : choice.getContent()) {
        prompt.append(word.getEnglish());
        prompt.append(" ");
    }
    return prompt.toString();
      }
    return null;
  }

  /**
   * Creates 3 multiple choice questions to add to this.questions
   */
  private void createMultipleChoice() {
    Random random = new Random();
    ArrayList<Word> allWords = new ArrayList<Word>();
    ArrayList<Word> topicWords = manager.getWordsByTopic(this.difficulty, this.topic);
    for(LessonTopic testtopic : LessonTopic.values()) {
      if(testtopic.equals(this.topic))
        continue;
      allWords.addAll(manager.getWordsByTopic(this.difficulty, testtopic));
    }
    for(int i = 0; i < 3; ++i) {
      Word answerWord = topicWords.get(i);
      String multipleChoiceQuestion = manager.getMeaning(difficulty, topic, answerWord.getForeign());
      String[] multipleChoiceSplit = multipleChoiceQuestion.split(" ");
      ArrayList<Word> multipleChoicePrompt = new ArrayList<Word>();
      for(String word : multipleChoiceSplit)
        multipleChoicePrompt.add(new Word("", word));
        ArrayList<Word> answers = new ArrayList<Word>();
        int correct = random.nextInt(4);
      for(int j = 0; j < correct; ++j) {
        Word word = allWords.get(random.nextInt(allWords.size()));
        answers.add(word);
    }
      answers.add(answerWord);
      for(int j = 0; j < 3 - correct; ++j) {
      Word word = allWords.get(random.nextInt(allWords.size()));
      answers.add(word);
    }
      this.questions.add(new MultipleChoice(multipleChoicePrompt, answers, correct,i));
  }
  }

  /**
   * Creates 3 FillBlank questions depending on what this.topic is
   */
  private void createFillBlank() {
    switch(this.topic) {
      case SCHOOL:
      this.questions.add(new FillBlank("Every night before bed I like to read a ___.", manager.getWord(this.difficulty,this.topic,"libro"),0));
      this.questions.add(new FillBlank("I hate sitting in the ___, it's so uncomfortable in school.", manager.getWord(this.difficulty,this.topic,"mesa"),1));
      this.questions.add(new FillBlank("All the students in the ___ were talking so loudly.", manager.getWord(this.difficulty,this.topic,"clase"),2));
      break;
      case WEATHER:
      this.questions.add(new FillBlank("During the day, the ___ shines brightly", manager.getWord(this.difficulty,this.topic,"sol"),0));
      this.questions.add(new FillBlank("The sky was filled with fluffy ___", manager.getWord(this.difficulty,this.topic,"nube"),1));
      this.questions.add(new FillBlank("The thirsty plants were nourished when it started to ___", manager.getWord(this.difficulty,this.topic,"lluvia"),2));
      break;
      case FAMILY:
      this.questions.add(new FillBlank("The woman who gave birth to me is my ___", manager.getWord(this.difficulty,this.topic,"madre"),0));
      this.questions.add(new FillBlank("The man who gave birth to me is my ___", manager.getWord(this.difficulty,this.topic,"padre"),1));
      this.questions.add(new FillBlank("My parents had two sons, their relationship is ___", manager.getWord(this.difficulty,this.topic,"hermano"),2));
      break;
      case FOOD:
      this.questions.add(new FillBlank("____ is from cows", manager.getWord(this.difficulty,this.topic,"leche"),0));
      this.questions.add(new FillBlank("____ is a red fruit that grows in trees", manager.getWord(this.difficulty,this.topic,"manzana"),1));
      this.questions.add(new FillBlank("The main ingredient in a sandwich is ___", manager.getWord(this.difficulty,this.topic,"pan"),2));
      break;
      case PETS:
      this.questions.add(new FillBlank("A ___ enjoys climbing high places and napping", manager.getWord(this.difficulty,this.topic,"gato"),0));
      this.questions.add(new FillBlank("The ___ loves to play fetch", manager.getWord(this.difficulty,this.topic,"perro"),1));
      this.questions.add(new FillBlank("My pet ____ always flies to greet me when I come home", manager.getWord(this.difficulty,this.topic,"pájaro"),2));
      break;
    }
  }

  /**
   * Takes the Matching object in this.questions and formats 
   * all word pairs separated by \n into one stingle string
   * @return String formatted with all words for matching 
   */
  public String matchPrompt() {
    StringBuilder prompt = new StringBuilder();
    for(Object question : this.questions) {
      if(question instanceof Matching) {
        Matching match = (Matching) question;
        ArrayList<Word> words = match.getContent();
        for(Word word : words) {
          prompt.append(word.getForeign());
          prompt.append("\n");
        }
        Collections.shuffle(words);
        for(Word word : words) {
          prompt.append(word.getEnglish());
          prompt.append("\n");
        }
      }
    }
    return prompt.toString();
  }

  /**
   * Checks the users answers for the matching problem and returns
   * an int from 0-3 of how many pairs they correctly identified
   * @param prompt The String prompt of matching (All 6 words separated by \n)
   * @param answers ArrayList<Integer> containing all of the users pairings
   * @return Integer representing how many correct pairs the user
   * identified
   */
  public int checkMatching(String prompt, ArrayList<Integer> answers) {
    String[] promptsplit = prompt.split("\n");
    int correct = 0;
    for(Object question : this.questions) {
      if(question instanceof Matching) {
        Matching match = (Matching) question;
        for(int i = 0; i < 5; i = i + 2) {
          boolean isCorrect = match.isCorrect(promptsplit[answers.get(i)],promptsplit[answers.get(i+1)]);
          if (isCorrect)
            ++correct;
        }
      }
    }
    return correct;
  }

  /**
   * Creates a Matching object that contains all 3 vocab words of 
   * this lessons topic. There will be 3 pairs of words with one
   * pair consisting of the foreign word and the english word
   */
  private void createMatching() {
    ArrayList<Word> matching = manager.getWordsByTopic(this.difficulty, this.topic);
    this.questions.add(new Matching(matching));
  }

  // public void setStory(PictureStory story) {
  //   this.story = story;
  // }

  /**
   * Returns the lessontopic
   * @return LessonTopic
   */
  public LessonTopic getTopic() {
    return this.topic;
  }

  /**
   * Returns a MultipleChoice object based on which identifier is
   * provided because there are 3 MultipleChoice questions per 
   * lesson
   * @param num identifier for which multiple choice to return
   * @return MultipleChoice object
   */
  public MultipleChoice getMultipleChoice(int num) {
    int i = 0;
    for(Object question : this.questions)
      if(question instanceof MultipleChoice) {
        MultipleChoice toReturn = (MultipleChoice) question;
        if(num == toReturn.getId())
          return (MultipleChoice) question;
        ++i;
      }
    return null;
  }

  /**
   * Returns an ArrayList<Flashcard> containing 
   * 3 flashcards for the 3 vocab words for the lesson
   * @return ArrayList<Flashcard> containing 3 flashcards
   */
  public ArrayList<Flashcard> getFlashcards() {
    ArrayList<Flashcard> cards = new ArrayList<>();
    for(Object object : this.questions)
      if(object instanceof Flashcard) {
        Flashcard card = (Flashcard)object;
        cards.add(card);
      }
    return cards;
  }

  /**
   * Returns a Matching Object
   * @return Matching Object
   */
  public Matching getMatching() {
    for(Object question : this.questions)
      if(question instanceof Matching) 
        return (Matching)question;
    return null;
  }
  
  // public PictureStory getStory() {
  //   return this.story;
  // }

  /**
   * Return a FillBlank object based on which one is selected
   * @param num identifier for which FillBlank out of the lesson is 
   * returned
   * @return FillBlank Object
   */
  public FillBlank getFillBlank(int num) {
    int i = 0;
    for(Object question : this.questions)
      if(question instanceof FillBlank) {
        FillBlank toReturn = (FillBlank)question;
        if(num == toReturn.getId() ) {
          return (FillBlank) question;
        }
        ++i;
      }
    return null;
  }

  /**
   * Checks the user response to the FillBlank question and return 
   * true or false 
   * @param num number identifier of FillBlank since there are 3 per
   * lesson
   * @param input The users input as a string
   * @return Boolean true or false
   */
  public boolean checkFillBlank(int num, String input) {
    int i = 0;
    for(Object question : this.questions)
      if(question instanceof FillBlank) {
        if(i == num ) {
          FillBlank toCheck = (FillBlank)question;
          return input.equalsIgnoreCase(toCheck.getAnswer().get(0).getForeign());
        }
        ++i;
      }
    return false;
  }

  /**
   * Returns all questions/flashcards/story inside one ArrayList<Object>
   * @return ArrayList<Object> of all things to complete in lesson
   */
  public ArrayList<Object> getQuestions() {
    return this.questions;
  }

  public void setQuestions(ArrayList<Object> questions) {
    this.questions = questions;
  }

  public ArrayList<Word> getWords() {
    return this.words;
  }
}