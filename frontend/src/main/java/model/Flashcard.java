package library;

import java.util.ArrayList;

/**
 * This class implements the flashcard lesson type
 * @author Michael Carson 
 */
public class Flashcard {
    private Word word;
    private String front;
    private String back;
    private boolean isFlipped;

    /**
     * initializes flashcard 
     * @param front
     * @param back
     */
    public Flashcard(Word word) {
        front = word.getForeign();
        back = word.getEnglish();
        this.word = word;
        this.isFlipped = false;
    }
    /**
     * sets the front of the flashcard 
     * @param word
     * @return
     */

    /**
     * flips the card 
     */
    public String flipCard() {
        this.isFlipped = !this.isFlipped;
        return this.isFlipped ? this.back : this.front;
    }
    /**
     * returns the current word based on weather or not the card is flipped
     * @return
     */
    public Word getCurrentWord() {
        return this.word;
    }
    public ArrayList<Word> getContent() {
      ArrayList<Word> words = new ArrayList<>();
      words.add(this.word);
      return words;
    }
}

