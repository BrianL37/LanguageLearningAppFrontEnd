package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * The class UserList maintains a collection of Users using a 
 * HashMap with UUID as the key and the User as the value;
 * @author Tony Lin and Ishaan Cheema
 */

public class UserList {
  private HashMap<UUID, User> users;
  private static UserList userList;
  private int size;

   /**
   * Private constructor to prevent direct instantiation.
   */

  UserList() {
    users = new HashMap<>();
    size = 0;
  }

    /**
     * Gets the single instance of {@code UserList}. 
     * Creates it if it doesn’t exist.
     * 
     * @return the singleton instance of {@code UserList}
     */

  public static synchronized UserList getInstance() {
    if (userList == null) {
      userList = new UserList();
      DataLoader.loadUsers(userList);
    }
    return userList;
  }

    /*
     * Adds a new user. 
     * Adds all of the information as well.
    */

  public User addUser(String firstName, String lastName,
                      String userName, String password, String email,
                      UUID userID) {
    User toAdd = new User(firstName, lastName, userName, password, email, userID);
    users.put(userID, toAdd);
    ++size;
    return toAdd;
  }

  /*
  * Gets a user by {@code UUID}. 
  */

  public User getUser(UUID userID) {
    return users.get(userID);
  }

  /*
  * Edits a user’s info by field index. 
  */

  public User editUser(UUID userID, int change, String updated) {
    switch(change) {
      case 0: users.get(userID).setFirstName(updated); break;
      case 1: users.get(userID).setLastName(updated); break;
      case 2: users.get(userID).setUserName(updated); break;
      case 3: users.get(userID).setPassword(updated); break;
      case 4: users.get(userID).setEmail(updated); break;
    }
    return users.get(userID);
  }

  /*
  * Removes a user by {@code UUID}. 
  */

  public boolean removeUser(UUID userID) {
    users.remove(userID);
    --size;
    return true;
  }

  /*
  * Authenticates a user by username and password. 
  */

  public User login(String userName, String password) {
    for (User user : users.values()) {
      if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null;
  }

  /*
  * Finds a user’s {@code UUID} by username. 
  */

  public UUID getUserID(String userName) {
    for (User user : users.values()) {
      if (user.getUserName().equals(userName)) {
        return user.getUserID();
      }
    }
    return null;
  }

  /*
  * Saves the user list. 
  */

  public void saveUsers() {
    DataWriter.saveUsers(getAllUsers());
  }

  /*
  * Gets the number of users. 
  */

  public Integer getSize() {
    return this.size;
  }

  /*
  * Returns a list of all users. 
  */

  public ArrayList<User> getAllUsers() {
    ArrayList<User> toReturn = new ArrayList<>();
    for(User user : users.values())
      toReturn.add(user);
    return toReturn;
  }
}
