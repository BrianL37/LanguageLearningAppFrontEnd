package library;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The User class represents a user with data members such as
 * userID, firstName, lastName, userName, password, friendList,
 * and settings.
 * @author Tony Lin
 */
public class User {
  private UUID userID;
  private String firstName;
  private String lastName;
  private String userName;
  private String password;
  private String email;
  private ArrayList<User> friendList;
  private Settings settings;
  private Progress progress;

/**
 * Parameterized constructor of user that generates random userID,
 * creates new empty friendList, and sets all of the data members,
 * to the arguments
 * @param firstName First name of the user
 * @param lastName Last name of the user
 * @param userName User name of the user
 * @param password Password of the user
 */
  public User(String firstName, String lastName, String userName,
              String password, String email, UUID userID) {
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.friendList = new ArrayList<User>();
    this.settings = new Settings();
    this.progress = new Progress();
    for(LessonTopic topic : LessonTopic.values())
    this.progress.setIncomplete(topic, new ArrayList<Object>());
  }
  
  /** Returns this user's userid
   * @return UUID of this user
   */
  public UUID getUserID() {
    return this.userID;
  }
  
  /** Sets this user's first name to the argument first name if
   * it is non-null
   * @param firstName first name to be set as user's first name
   */
  public void setFirstName(String firstName) {
    if(firstName != null)
      this.firstName = firstName;
  }

  /** Returns this users first name
   * @return String first name of this user
   */
  public String getFirstName() {
    return this.firstName;
  }

  /** Sets this user's last name to the argument last name if
   * it is non-null
   * @param lastName to be set as user's last name
   */
  public void setLastName(String lastName) {
    if(lastName != null)
      this.lastName = lastName;
  }
  
  /** Returns this user's last name
   * @return String last name of this user
   */
  public String getLastName() {
    return this.lastName;
  }

  /** Sets this user's username to the argument userName if 
   * it is non-null
   * @param userName to be set as the new userName
   */
  public void setUserName(String userName) {
    if(userName != null)
      this.userName = userName;
  }
  
  /** Returns this user's username
   * @return String username of this user
   */
  public String getUserName() {
    return this.userName;
  }

  /** Sets new password if the argument password is greater than 8
   * characters
   * @param String password to be set as the new password
   * @return boolean true if successful and false if unsuccessful
   */
  public boolean setPassword(String password) {
    if(password.length() >= 8) {
      this.password = password;
      return true;
    }
    return false;
  }
  
  /** Returns this user's password
   * @return String password of this user
   */
  public String getPassword() {
    return this.password;
  }

  /** Returns the friend list of this user
   * @return ArrayList<User> friendlist of this user
   */
  public ArrayList<User> getFriendList() {
    return this.friendList;
  }
  
  /** Adds the argument user onto this user's friendlist if the
   * argument user is non-null and not this user.
   * @param user to be added onto friend list
   * @return bool true if successfully added and false if unable to
   * add.
   */
  public boolean addFriend(User user) {
    if(user != null && user != this) {
      this.friendList.add(user);
      return true;
    }
    return false;
  }

  /** Checks if the argument user is a friend in the friend list and
   * removes it from the list if on there
   * @param user to be removed from the friend list
   * @return boolean true if the user is removed, false if friend is
   * not in the list.
   */
  public boolean removeFriend(User user) {
    if(user != null && user != this) {
        if(this.friendList.contains(user)) {
        this.friendList.remove(user);
        return true;
        }
      }
      return false;
  }

  /**
   * Sets the user's email to new one passed in
   * @param email new email for user
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Returns the user's email
   * @return the user's email
   */
  public String getEmail() {
    return this.email;
  }

  /** Returns this user's current settings
   * @return Settings of this user
   */
  public Settings getSettings() {
    return this.settings;
  }

  /** Changes this users settings if the arguments are valid
   * @param setting The setting to change
   * @param updated 0 for false and anythin else for true;
   */
  public void changeSetting(int setting, int updated) {
    if((setting >= 0 && setting <= 3) && (updated >= 0)) {
      switch(setting) {
        case 0:this.settings.setNotifications(updated); break;
        case 1:this.settings.setLightMode(updated); break;
        case 2:this.settings.setTextToSpeech(updated); break;
        case 3:this.settings.setFontSize(updated);
      }
    }
  }

  /**
   * Sets the collection of this user's incomplete questions for a 
   * specific lesson 
   * @param topic of the lesson
   * @param questions ArrayList<Question> of the incomplete questions
   */
  public void setIncomplete(LessonTopic topic, ArrayList<Object> questions) {
    this.progress.setIncomplete(topic, questions);
  }

  /**
   * Returns the collection of this user's incomplete questions for
   * a specific lesson
   * @param topic of the lesson
   * @return ArrayList<Question> of the incomplete question of that
   * lesson
   */
  public ArrayList<Object> getIncomplete(LessonTopic topic) {
    return this.progress.getIncomplete(topic);
  }

  /**
   * Returns this user's overall progress through a langauge
   * @return int representing total number of completed questions
   * in a language
   */
  public int getLanguageProgress() {
    return this.progress.getLanguageProgress();
  }

  /**
   * Returns this user's progress through a specific lesson
   * @param topic of the lesson
   * @return int representing complete questions in that lesson
   */
  public int getLessonProgress(LessonTopic topic) {
    return this.progress.getLessonProgress(topic);
  }

  public void complete(LessonTopic topic) {
    this.progress.completeLesson(topic);
  }

  public ArrayList<Object> getAllIncomplete() {
    ArrayList<Object> toReturn = new ArrayList<>();
    for(LessonTopic topic : LessonTopic.values()) {
      toReturn.addAll(this.progress.getIncomplete(topic));
    }
    return toReturn;
  }

  public void setLessonProgress(LessonTopic topic, int progress) {
    
  }

  public void setLanguageProgress(int progress) {
    this.progress.setLanguageProgress(progress);
  }

  public void correct(LessonTopic topic, Object question) {
    this.progress.updateCorrect(topic,question);
   }

  public void incorrect(LessonTopic topic, Object question) {
    this.progress.updateIncomplete(topic,question);
}

  public HashMap<LessonTopic,HashMap<Question,Integer>> getTrouble() {
    return this.progress.getTrouble();
  }

  public int getModule() {
    return this.progress.getModule();
  }

  public void setModule(int num) {
    this.progress.setModule(num);
}

  public LanguageDifficulty getDifficulty() {
    return this.progress.getDifficulty();
  }

  public void setDifficulty(LanguageDifficulty difficulty) {
    this.progress.setDifficulty(difficulty);
  }
  public void setLanguage(ForeignLanguage language) {
    this.progress.setLanguage(language);
  }

  public ForeignLanguage getLanguage() {
    return this.progress.getLanguage();
  }
}



