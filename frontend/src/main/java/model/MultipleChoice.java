package library;

import java.util.ArrayList;

/**
 * The class `MultipleChoice` represents a multiple choice question
 * with a list of words for the question, an array of lists of words
 * for the answer choices, and an integer to track the index of
 * the correct answer.
 * @author Tony Lin and Ishaan Cheema
 */
public class MultipleChoice implements Question{
  private ArrayList<Word> question;
  private ArrayList<Word> answers;
  private int correct;
  private int id;

    /**
     * Creates a {@code MultipleChoice} question.
     * 
     * @param question the question words
     * @param answers the answer choices
     * @param correct index of the correct answer
     */

  public MultipleChoice(ArrayList<Word> question,
                        ArrayList<Word> answers, int correct, int id) {
    this.question = question;
    this.answers = answers;
    this.correct = correct;
    this.id = id;
  }

  public int getCorrectPosition() {
    return this.correct;
  }

  /*
  * @return the question content 
  */

  public ArrayList<Word> getContent() {
    return this.question;
  }

  /*
  * @return the answer choices 
  */

  public ArrayList<Word> getAnswers() {
    return this.answers;
  }

  /*
  * Checks if the user-selected answer is correct.
  */

  public boolean isCorrect(ArrayList<Word> answer) {
    return answer.get(0) == this.answers.get(correct);
  }

  /*
  * @return the correct answer choice 
  */

  public ArrayList<Word> getAnswer() {
    ArrayList<Word> word = new ArrayList<Word>();
    word.add(answers.get(correct));
    return word;
  }


  public void setPrompt(ArrayList<Word> prompt) {
    this.question = prompt;
  }


  public void setAnswer(Word answer) {
  }

  public int getId() {
    return this.id; 
  }

}
