package library;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The LanguageList class manages a list of languages associated with
 * each user using a HashMap.
 * @author Tony Lin
 */
public class LanguageList {
  private static LanguageList languageList;
  private HashMap<UUID, ArrayList<Language>> userLanguages;

  /**
   * Private constructor that initializes the HashMap userLanguages
   */
  private LanguageList() {
    userLanguages = new HashMap<UUID,ArrayList<Language>>();
  }
  
  /** Checks if there exists an instance of LanguageList. If one exists
   * returns that LanguageList. If one does not exist will create new
   * LanguageList to return
   * @return LanguageList
   */
  public static LanguageList getInstance() {
    if(languageList == null)
      languageList = new LanguageList();
    return languageList;
  }

  /** Checks if the user has other languages in the HashMap. If this
   * is the user's first language, will add them to the HashMap with
   * a new ArrayList to hold their languages. If the user has a
   * previous language, it just adds the language onto their existing
   * ArrayList
   * @param userID Of the user to add the language
   * @param language To be added to the user
   * @return Language back to the user for them to complete
   */
  public Language addLanguage(UUID userID, ForeignLanguage language,
                              LanguageDifficulty difficulty) {
    Language toReturn = new Language(language,difficulty);
    if(!this.userLanguages.keySet().contains(userID)) {
      userLanguages.put(userID, new ArrayList<Language>());
      userLanguages.get(userID).add(toReturn);
    }
    else {
      this.userLanguages.get(userID).add(toReturn);
    }
    return toReturn;
    }

  /** Goes through the list of a user's languages and if the user
   * has added the foreign language requested, it will return that 
   * language. If the user requested a language they have not 
   * added, then it returns null
   * @param userID of the user to get their languages
   * @param language the foreign language requested by user
   * @return Language requested by user
   */
  public Language getLanguage(UUID userID, ForeignLanguage language) {

    for(int i = 0; i < userLanguages.get(userID).size(); ++i) {
      if(userLanguages.get(userID).get(i).getForeignLanguage().equals(language))
        return userLanguages.get(userID).get(i);
    }
    return null;
  }

  /** Returns an ArrayList of all the languages added by a user
   * @param userID Of the user
   * @return ArrayList<Language> All of the languages added by user
   */
  public ArrayList<Language> getAllLanguages(UUID userID) {
    return userLanguages.get(userID);
  }


}
