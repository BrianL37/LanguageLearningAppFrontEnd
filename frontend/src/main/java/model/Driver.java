package library;


import java.util.Scanner;
import java.util.UUID;

import narrator.Narrator;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * This driver is testing 3 scenarios for the LanguageLearningApp
 * Scenario 1: Jim Smith signs up as a new user
 * Scenario 2: Jim Smith completes the first module with 4/5 success
 * Scenario 3: Jim Smith fails a module with  3/5 success
 * Scenario 4: Jim Smith logs out and logs back in to retry 
 * the second module
 * @author Tony Lin
 */
public class Driver {
  private static LanguageLearningSystemFacade facade;
  private static Scanner keyboard;
  
  /**
   * Initializes the facade, scanner, and dictionary manager
   */
  public static void startDemo() {
    facade = LanguageLearningSystemFacade.getInstance();
    keyboard = new Scanner(System.in);
  }

  /**
   * Allows the user to login from the terminal by entering username
   * and password
   */
  public static void login() {
    try {
    System.out.println("Enter your username");
    String userName = keyboard.next();
    keyboard.nextLine();
    System.out.println("Enter your password");
    String password = keyboard.next();
    keyboard.nextLine();
    facade.login(userName, password);
    System.out.println("Welcome back: " + facade.getUser().getFirstName());
    System.out.println("Friends:");
    for(User user : facade.getUser().getFriendList()) {
      System.out.println(user.getUserName());
    }
    } catch(Exception e) {
      System.out.println("Incorrect username/password");
    }
  }

  /**
   * Allows the user to sign up from the terminal by entering 
   * first name, last name, username, password, and email.
   */
  public static void signUp() {
    try {
      System.out.println("Enter your first name");
      String firstName = keyboard.nextLine();
      System.out.println("Enter your last name");
      String lastName = keyboard.nextLine();
      System.out.println("Enter your username");
      String userName = keyboard.nextLine();
      System.out.println("Enter your password (At least 8 characters)");
      String password = keyboard.nextLine();
      System.out.println("Enter your email");
      String email = keyboard.nextLine();
      checkSignUp(firstName, lastName, userName, password, email);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Checks arguments to ensure when the user signs up 
   * all values they entered are available/valid.
   * @param firstName First name of user
   * @param lastName Last name of user
   * @param userName Username of user
   * @param password Password of user
   * @param email Email of user
   */
  private static void checkSignUp(String firstName, String lastName,
  String userName, String password, String email)  {
    try {
      if(password.length() < 8) {
        System.out.println("Please enter a password at least 8 characters");
        return;
      }
      ArrayList<User> allUsers = facade.getAllUsers();
      for(User user : allUsers) {
        if(user.getUserName().equals(userName)) {
          System.out.println("This username is taken, please choose a new one");
          return;
        }
        else if (user.getEmail().equals(email)) {
          System.out.println("This email is already registered, please choose a new one");
          return;
        }
      }
      facade.signUp(firstName, lastName, userName, password, email, UUID.randomUUID());
      System.out.println("Welcome new user: " + facade.getUser().getFirstName());
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  /**
   * Saves all data to json files
   */
  public static void logout() {
    facade.logout();
    System.out.println("Logged Out");
  }

  /**
   * Presents options for user to use the program through terminal
   *  such as login, signup, or logout.
   */
  public static void menuOptions() {
    try {
      System.out.println("Enter 1 to login\nEnter 2 to signup"
      + "\nEnter 9 to logout");
      int menuOptions = keyboard.nextInt();
      keyboard.nextLine();
      switch(menuOptions) {
      case 1: login(); break;
      case 2: signUp(); break;
      case 9: logout(); System.exit(0); break;
      default: System.out.println("Please enter 1, 2, or 9");
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Will play the string passed in as argument as a text-to-speech
   * feature
   * @param text to be played out loud
   */
  public static void playSound(String text){
    Narrator.playSound(text);
  }

  /**
   * Loads the matching game and prints all words to the terminal.
   * Then allows the user to choose a spanish word and english word 
   * to pair together. Only marked correct if the user pairs all 3
   * vocab pairs successfully
   */
  public static boolean playMatching() {
   // Narrator.playSound("Matching Game");
    System.out.println("\nMatching Game");
    String prompt = facade.getLesson().matchPrompt();
    System.out.println(prompt);
    ArrayList<Integer> userChoices = new ArrayList<>();
    for(int i = 0; i < 3; ++i) {
      System.out.println("Pick a Spanish word");
      userChoices.add(keyboard.nextInt()-1);
      keyboard.nextLine();
      System.out.println("Pick a English word");
      userChoices.add(keyboard.nextInt()-1);
      keyboard.nextLine();
    }
    System.out.println("Your score was: "+ facade.getLesson().checkMatching(prompt, userChoices) + "/3");
    if(facade.getLesson().checkMatching(prompt, userChoices) == 3) {
      facade.getUser().correct(facade.getLesson().getTopic(), facade.getLesson().getMatching());
      return true;
    }else {
      facade.getUser().incorrect(facade.getLesson().getTopic(), facade.getLesson().getMatching());
      return false;
    }
  }

  /**
   * Loads a FillBlank based on the users choice and prints 
   * the prompt and takes the users input. Then it checks
   * the users input and updates their progress if they 
   * were correct
   * @param num Representing which FillBlank to load
   */
  public static boolean playFillBlank(int num) {
    // Narrator.playSound("Fill in the Blank");
    System.out.println(facade.getLesson().getFillBlank(num).getContent().get(0).getEnglish());
    String input = keyboard.nextLine();
    if(facade.getLesson().checkFillBlank(num, input)) {
      facade.getUser().correct(facade.getLesson().getTopic(), facade.getLesson().getFillBlank(num));
      System.out.println("Correct");
      return true;
    } else {
      facade.getUser().incorrect(facade.getLesson().getTopic(), facade.getLesson().getFillBlank(num));
      System.out.println("Incorrect, the correct answer was");
      System.out.println(facade.getLesson().getFillBlank(num).getAnswer().get(0).getForeign());
      return false;
    }
  }

  /**
   * Loads the 3 flashcards per lesson and uses the Narrator to 
   * say the words out loud
   */
  public static void playFlashcards() {
    Narrator.playSound("Flash Cards");
    System.out.println("\nCard 1");
    Narrator.playSound(facade.getLesson().getFlashcards().get(0).getCurrentWord().getForeign());
    System.out.println(facade.getLesson().getFlashcards().get(0).getCurrentWord().getForeign());
    Narrator.playSound(facade.getLesson().getFlashcards().get(0).getCurrentWord().getEnglish());
    System.out.println(facade.getLesson().getFlashcards().get(0).getCurrentWord().getEnglish());
    System.out.println("\nCard 2");
    Narrator.playSound(facade.getLesson().getFlashcards().get(1).getCurrentWord().getForeign());
    System.out.println(facade.getLesson().getFlashcards().get(1).getCurrentWord().getForeign());
    Narrator.playSound(facade.getLesson().getFlashcards().get(1).getCurrentWord().getEnglish());
    System.out.println(facade.getLesson().getFlashcards().get(1).getCurrentWord().getEnglish());
    System.out.println("\nCard 3");
    Narrator.playSound(facade.getLesson().getFlashcards().get(2).getCurrentWord().getForeign());
    System.out.println(facade.getLesson().getFlashcards().get(2).getCurrentWord().getForeign());
    Narrator.playSound(facade.getLesson().getFlashcards().get(2).getCurrentWord().getEnglish());
    System.out.println(facade.getLesson().getFlashcards().get(2).getCurrentWord().getEnglish());
    for(Flashcard card : facade.getLesson().getFlashcards()) {
      facade.getUser().correct(facade.getLesson().getTopic(),card);
    }

  }

  /**
   * Loads a multiple choice question based on users choice and
   * prints the prompt and answer choices. Then it takes users choice
   * checks if the user is correct and updates the user information
   * @param num Representing which MultipleChoice question to load
   */
  public static boolean playMultipleChoice(int num) {
    Narrator.playSound("Multiple Choice Question");
    System.out.println("Multiple Choice Question:");
    System.out.println(facade.getLesson().multipleChoicePrompt(facade.getLesson().getMultipleChoice(num)));
    System.out.println("Answer Choices");
    System.out.println(facade.getLesson().multipleChoiceAnswers(facade.getLesson().getMultipleChoice(num)));
    System.out.println("Enter a number 1-4");
    int choice = keyboard.nextInt() - 1;
    keyboard.nextLine();
    if(facade.getLesson().checkMultipleChoice(facade.getLesson().getMultipleChoice(num),choice)) {
      facade.getUser().correct(facade.getLesson().getTopic(), facade.getLesson().getMultipleChoice(num));
      System.out.println("Correct");
      return true;
    } else {
    facade.getUser().incorrect(facade.getLesson().getTopic(), facade.getLesson().getMultipleChoice(num));
    System.out.println("Incorrect");
    return false;
    }
  }

  public static void startLanguage(ForeignLanguage language,LanguageDifficulty difficulty) {
    facade.startLanguage(language, difficulty);
  }

  public static void startLesson(LessonTopic topic) {
    if(facade.getUser().getLessonProgress(topic) == 8) {
      System.out.println("You have already completed this module");
      return;
    }
    int score = 0;
    facade.startLesson(topic);
    playFlashcards();
    ++score;
    if(playMultipleChoice(2))
      ++score;
    if(playMultipleChoice(1))
    ++score;
    if(playMultipleChoice(0));
    ++score;
    if(playMatching())
      ++score;
    if(playFillBlank(2))
      ++score;
    if(playFillBlank(1))
    ++score;
    if(playFillBlank(0))
    ++score;
    Narrator.playSound("You scored " + String.valueOf(score) + "Out of eight");
    System.out.println("You scored " + score + "/8" + " (" + (score/8)*100 + "%)");
    if(score >= 7) {
      facade.getUser().complete(topic);
    } else {
      System.out.println("Try this module again");
      return;
    }
  }

  /**
   * Scenario 1
   */
  public static void scenario1() {
    String firstName = "Jim";
    String lastName = "Smith";
    String userName = "JimSmith01";
    String password = "SmithRocks";
    String email = "JimSmith2000@email.com";
    checkSignUp(firstName, lastName, userName, password, email);
    facade.login(userName,password);
    System.out.println("Jim logs out");
    facade.logout();
    System.out.println("Jim logs back in");
    facade.login(userName, password);
  }

  /**
   * Scenario 2
   */
  public static void scenario2() {
    startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.EASY);
    startLesson(LessonTopic.FOOD);
    Narrator.playSound("You scored eighty percent, You can advance to the next module");
    System.out.println("You scored 80%, You can advance to the next module");
    facade.getUser().setModule(2);
  }

  /**
   * Scenario 3
   */
  public static void scenario3() {
    facade.startLesson(LessonTopic.SCHOOL);
    Narrator.playSound("You scored sixty percent, Time to try again");
    System.out.println("You scored a 60%");
  }

  /**
   * Scenario 4
   */
  public static void scenario4() {
    System.out.println("Jim logs out");
    facade.logout();
    facade.login("JimSmith01", "SmithRocks");
    System.out.println("Jim logs back in");
    facade.startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.EASY);
    facade.startLesson(LessonTopic.SCHOOL);
    System.out.println("Jim continues on 2nd Module");
    startLesson(LessonTopic.SCHOOL);
    }

  public static void main(String[] args) {
    startDemo();
    // facade.login("JimSmith01", "SmithRocks");
    // facade.continueLanguage(ForeignLanguage.SPANISH);
    // facade.startLesson(LessonTopic.FOOD);
    // playMatching();
    // logout();
    // scenario1();
    // scenario2();
    // scenario3();
    // scenario4();
    BoardGame game = BoardGame.getInstance();
}
}