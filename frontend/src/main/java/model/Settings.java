package model;

/**
 * The Settings class defines properties for notifications, 
 * light mode, text-to-speech, and font size for a user.
 * @author Tony Lin
 */
public class Settings {
  private int notifications;
  private int lightMode;
  private int textToSpeech;
  private int fontSize;

  /**
   * Default constructor where a users default settings are
   * set to no notifications, light mode on, no text to speech,
   * and font size of 10.
   */
  public Settings() {
    this.notifications = 0;
    this.lightMode = 1;
    this.textToSpeech = 0;
    this.fontSize = 10;
  }
  
  /** Changes the notification setting to the boolean argument passed
   * in
   * @param on the new setting for notifications
   */
  public void setNotifications(int on) {
    this.notifications = on;
  }

  /**
   * Returns the value of notifications
   * @return 1 if on or 0 if off
   */
  public int getNotifications() {
    return this.notifications;
  }

  /** Changes the light mode setting to the boolean argument passed
   * in
   * @param on the new setting for light mode
   */
  public void setLightMode(int on) {
    this.lightMode = on;
  }
  
  /**
   * Returns the value of light mode
   * @return 1 if on or 0 if off
   */
  public int getLightMode() {
    return this.lightMode;
  }

  /** Changes the text-to-speech setting to the boolean argument 
   * passed in
   * @param on the new setting for text-to-speech
   */
  public void setTextToSpeech(int on) {
    this.textToSpeech = on;
  }
  
/**
 * Returns the value of text-to-speech
 * @return 1 if on or 0 if off
 */
  public int getTextToSpeech() {
    return this.textToSpeech;
  }

  /** Changes the font size setting to the boolean argument passed in
   * @param size the new setting for font size
   */
  public void setFontSize(int size) {
    if(size > 0)
      this.fontSize = size;
  }

  /**
   * Returns the value of font size
   * @return Integer of font size
   */
  public int getFontSize() {
    return this.fontSize;
  }

  /** Returns a string of all of a user's current settings
   * in formatted manner
   * @return String of all datamembers of settings class
   */
  public String toString() {
    return "Settings:\nNotifications: " + String.valueOf(this.notifications)
            + "\nLight Mode: " + String.valueOf(this.lightMode) + 
            "\nText-To-Speech: " + String.valueOf(this.textToSpeech) +
            "\nFont Size: " + String.valueOf(this.fontSize);
  }
}
