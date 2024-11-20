package library;

/**
 * this enumeration holds the different language types the app offers 
 * @author Michael Carson 
 */
public enum ForeignLanguage {
SPANISH,
GERMAN,
FRENCH,
CHINESE;

public static ForeignLanguage fromString(String text) {
    if(text.equalsIgnoreCase("SPANISH"))
      return SPANISH;
    else if(text.equalsIgnoreCase("GERMAN"))
      return GERMAN;
      else if(text.equalsIgnoreCase("FRENCH"))
      return FRENCH;
    else 
      return CHINESE;
}
}
