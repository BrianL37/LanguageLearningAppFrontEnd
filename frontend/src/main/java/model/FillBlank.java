package library;

import java.util.ArrayList;

/**
 * The FillBlank class represents a fill-in-the-blank question.
 * It contains a question and an answer, which is the correct Word.
 * This class allows checking if a user-provided answer is correct.
 * @author Tony Lin and Ishaan Cheema
*/

public class FillBlank implements Question {
  private Word answer;
  private String prompt;
  private int id;

    /**
     * Creates a {@code FillBlank} question.
     * 
     * @param question the question content as a list of words
     * @param answer the correct answer
     */

  public FillBlank(String prompt, Word answer, int id) {
    this.prompt = prompt;
    this.answer = answer;
    this.id = id;
  }

  /*
  * @return the correct answer 
  */

  public ArrayList<Word> getAnswer() {
    ArrayList<Word> word = new ArrayList<>();
    word.add(this.answer);
    return word;
  }

  /*
  * @return the question content 
  */
  
  public ArrayList<Word> getContent() {
    ArrayList<Word> words = new ArrayList();
    Word word = new Word("", this.prompt);
    words.add(word);
    return words;
  }

    /**
     * Checks if the given {@code Word} is correct.
     * 
     * @param userAnswer the user's answer
     * @return {@code true} if correct, {@code false} otherwise
     */
  
  public boolean isCorrect(Word userAnswer) {
    return this.answer.equals(userAnswer);
  }

    /**
     * Checks if the given word (in foreign language) is correct.
     * 
     * @param foreignWord the user's answer in foreign language
     * @return {@code true} if correct, {@code false} otherwise
     */
  
  public boolean isCorrect(String foreignWord) {
    return this.answer.getForeign().equals(foreignWord);
  }

    /**
     * Sets a new correct answer.
     * 
     * @param newAnswer the new answer
     */

  public void setAnswer(Word newAnswer) {
    this.answer = newAnswer;
  }

    public boolean isCorrect(ArrayList<Word> answer) {
     return answer.get(0)==this.answer;
    }

    @Override
    public void setPrompt(ArrayList<Word> prompt) {
    }

  public int getId() {
    return this.id; 
  }


}
