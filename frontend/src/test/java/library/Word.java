package library;

import java.util.ArrayList;

/**
 * The Word class in Java represents a word with its foreign and
 * English translations, along with a list of similar words.
 * This class allows for storing translations and checking word correctness.
 * It also supports managing a list of similar words.
 * 
 * @author Tony Lin and Ishaan Cheema
 */

public class Word {
  private String foreign;
  private String english;
  private WordType type;
  private ArrayList<Word> similar;

  public Word(String foreign, String english) {
    this.foreign = foreign;
    this.english = english;
    this.similar = new ArrayList<>();
  }

  public boolean isCorrect(String foreign, String english) {
    if (foreign == null || english == null) {
      return false;
    }
    return this.foreign.equals(foreign) && this.english.equals(english);
  }

  public String getForeign() {
    return this.foreign;
  }

  public String getEnglish() {
    return this.english;
  }

  // Add a similar word
  public void addSimilarWord(Word word) {
    if (word != null) {
      similar.add(word);
    }
  }

  // Remove a similar word
  public boolean removeSimilarWord(Word word) {
    return similar.remove(word);
  }

  // Get the list of similar words
  public ArrayList<Word> getSimilarWords() {
    return this.similar;
  }

  // Optional: Override toString for better string representation
  @Override
  public String toString() {
    return "Word [foreign=" + foreign + ", english=" + english + "]";
  }

  // Optional: Override equals for equality check based on foreign and english words
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Word other = (Word) obj;
    return foreign.equals(other.foreign) && english.equals(other.english);
  }
}
