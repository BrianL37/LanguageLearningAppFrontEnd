package library;

import java.util.ArrayList;

public interface Question {

    /** 
Returns the content of the question as an ArrayList of Word objects.
@return an ArrayList containing the words in the question prompt
    */
ArrayList<Word> getContent();

    /** 
Checks if the provided answer index matches the correct answer.
@param userAnswer an integer representing the user's answer choice
@return true if the answer is correct; false otherwise
    */
boolean isCorrect(ArrayList<Word> answer);

    /** 
Sets the content of the question with a prompt of words.
@param prompt an ArrayList of Word objects representing the question prompt
    */
void setPrompt(ArrayList<Word> prompt);

    /** 
Returns the correct answer for the question.
@return the correct Word answer
    */
ArrayList<Word> getAnswer();

    /**
Sets the correct answer for the question.
@param answer the Word object representing the correct answer
    */
void setAnswer(Word answer);
}