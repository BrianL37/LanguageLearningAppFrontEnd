package library;

import java.util.UUID;

import javax.naming.ldap.LdapName;

import java.util.ArrayList;


/**
 * The class LanguageLearningSystemFacade represents a facade for a 
 * language learning program that has a static BoardGame, Facade,
 * LanguageList, UserList, a user, currentLanguage, and
 * currentLesson. This class will be the  only class the UI interacts
 * with
 * @author Tony Lin
 */
public class LanguageLearningSystemFacade {
  private User user;
  private Language currentLanguage;
  private Lesson currentLesson;
  private static BoardGame boardGame;
  private static LanguageLearningSystemFacade facade;
  private static LanguageList languageList;
  private static UserList userList;


  /**
   * Private constructor for the facade that also creates an instance
   * of userList, languageList, and boardGame.
   */
  private LanguageLearningSystemFacade() {
    userList = UserList.getInstance();
    languageList = LanguageList.getInstance();
    //boardGame = BoardGame.getInstance();
  }

  
  /** Checks if there is already an instance of the facade. If one 
   * exists, returns that instance. If one does not exist, creates
   * a new instance and returns that.
   * @return LanguageLearningSystemFacade
   */
  public static LanguageLearningSystemFacade getInstance() {
    if(facade == null)
      facade = new LanguageLearningSystemFacade();
    return facade;
  }

  public static void resetInstance() {
    facade = null;
  }
  
  /** Logs in to the users preexisting accout and sets this user
   * to user that was just logged into
   * @param userName of the account to login to 
   * @param password of the account to login to
   * @return User that was logged into
   */
  public User login(String userName, String password) {
    this.user = userList.login(userName, password);
    return userList.login(userName,password);
  }

  
  /** Saves all data including all users, all boardgame progress, and
   * all languages
   * @return boolean 1 if successful and 0 if unsuccessful
   */
  public boolean logout() {
    try {
      userList.saveUsers();
      //boardGame.saveBoard();
      //languageList.saveLanguages();  
      return true;
    } catch (Exception e) {
      return false;
    }

  }
/**
 * Returns the current logged in user in the facade
 * @return User that is logged in
 */
  public User getUser() {
    return this.user;
  }
  
  /**
   * Returns ArrayList containing all users loaded from json
   * @return ArrayList<User> containing all users in system
   */
  public ArrayList<User> getAllUsers() {
    return userList.getAllUsers();
  }

  /** Creates a new user and adds them into the userList while also
   * setting this user to the new user created
   * @param firstName of the new user
   * @param lastName of the new user
   * @param userName of the new user
   * @param password of the new user
   * @param email of the new user
   * @param userID UUID of the new user
   * @return User that was just created
   */
  public User signUp(String firstName, String lastName,
                     String userName, String password, String email, UUID userID) {
    userList.addUser(firstName, lastName, userName, password, email, userID);
    this.user = userList.getUser(userList.getUserID(userName));
    return user;
  }

  
  /** Changes this user's personal data
   * @param change What data to change for this user
   * @param updated Updated data of the user
   * @return User with the updated data
   */
  public User editUser(int change, String updated) {
    userList.editUser(this.user.getUserID(),change,updated);
    return this.user;
  }

  
  /** Adds a user onto this users friendlist
   * @param userName of the friend to add
   * @return User the friend that was just added
   */
  public User addFriend(String userName) {
    UUID friendID = userList.getUserID(userName);
    User friend = userList.getUser(friendID);
    userList.getUser(this.user.getUserID()).addFriend(friend);
    return friend;
  }


/** Returns an ArrayList of all of this user's friends
 * @return ArrayList<User> containing all of the user's friends
 */
public ArrayList<User> getFriendList() {
  return userList.getUser(this.user.getUserID()).getFriendList();
}

  
  /** Removes a user from this users friendlist
   * @param userName of the friend to remove
   * @return boolean 1 if successful or 0 if unsuccessful
   */
  public boolean removeFriend(String userName) {
    try {
      UUID friendID = userList.getUserID(userName);
      User friend = userList.getUser(friendID);
      userList.getUser(this.user.getUserID()).removeFriend(friend);
      return true;
    } catch (Exception e) {
      return false;
    }

  }
  
  
  /** Changes the user's personal settings
   * @param setting option the user wants to change
   * @param updated the new status of the setting option
   */
  public void changeSettingOptions(int setting, int updated) {
    userList.getUser(this.user.getUserID()).changeSetting(setting, updated);
  }

  
  /** Returns an ArrayList containing all languages the user has 
   * started
   * @return ArrayList<Language> of all of the user's languages
   */
  public ArrayList<Language> getLanguages() {
    return languageList.getAllLanguages(this.user.getUserID());
  }

  public Language getLanguage() {
    return this.currentLanguage;
  }
  
  /** Adds the new language to languageList and sets the
   * currentLanguage to the language. Returns the language for 
   * the user to complete
   * @param language that the user wants to start
   * @param difficulty level that the user wants the language at
   * @return Language for the user to complete
   */
  public Language startLanguage(ForeignLanguage language,
                                LanguageDifficulty difficulty) {
    languageList.addLanguage(this.user.getUserID(),language,difficulty);
    this.currentLanguage = languageList.getLanguage(this.user.getUserID(), language);
    for(LessonTopic topic : LessonTopic.values())
      this.user.setIncomplete(topic, this.currentLanguage.getLesson(topic).getQuestions());
    user.setDifficulty(difficulty);
    user.setLanguage(language);
    return languageList.getLanguage(this.user.getUserID(), language);
  }

  public Language continueLanguage(ForeignLanguage language) {
    this.currentLanguage = languageList.addLanguage(this.user.getUserID(), language, this.user.getDifficulty());
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    for(LessonTopic topic : LessonTopic.values()) {
      Lesson toAdd = new Lesson(this.user.getDifficulty(), topic);
      toAdd.setQuestions(this.user.getIncomplete(topic));
      lessons.add(toAdd);
    }
     user.setLanguage(language);
     return this.currentLanguage;
  }


  
  /** Begins the board game
   * @return BoardGame that has been started
   */
  public BoardGame startGame() {
    boardGame.startGame();
    return boardGame;
  }

  
  /** Returns a lesson of the topic from the users's choice
   * @param language of the lesson the user wants
   * @param topic of the lesson the user wants
   * @return Lesson to return to the user
   */
  public Lesson chooseTopic(ForeignLanguage language, LessonTopic topic) {
    this.currentLesson = languageList.getLanguage(this.user.getUserID(),language).getLesson(topic);
    return languageList.getLanguage(this.user.getUserID(),language).getLesson(topic);
}

  public Lesson getLesson() {
    return this.currentLesson; 
  }

  public void setLesson(Lesson lesson) {
    this.currentLesson = lesson;
  }

  /** Begins a lesson for the user and returns that lesson
   * @param language of the lesson the user wants to start
   * @param topic of the lesson the user wants to start
   * @return Lesson for the user to complete
   */
  public Lesson startLesson(LessonTopic topic) {
    this.currentLesson = this.currentLanguage.getLesson(topic);
    return this.currentLesson;
  }

  
  /** Completes the lesson and returns 1 if successful or returns 0
   * if unsuccessful
   * @param language That the lesson belongs to 
   * @param lesson The lesson that was completed
   * @return boolean 1 if successful and 0 if unsuccessful
   */
  public boolean endLesson(ForeignLanguage language, Lesson lesson) {
    try {
      this.currentLesson = null;
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
