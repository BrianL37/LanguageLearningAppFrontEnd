package library;
import java.util.ArrayList;


/**
 * @author Brian Lee
 * Mock implementation of the Question interface for testing purposes.
 */
public class MockQuestion implements Question {
    private ArrayList<Word> content;
    private ArrayList<Word> answer;


    public MockQuestion() {
        this.content = new ArrayList<>();
        this.answer = new ArrayList<>();
    }


    @Override
    public ArrayList<Word> getContent() {
        return content;
    }


    @Override
    public boolean isCorrect(ArrayList<Word> userAnswer) {
        return answer.equals(userAnswer);
    }


    @Override
    public void setPrompt(ArrayList<Word> prompt) {
        this.content = prompt;
    }


    @Override
    public ArrayList<Word> getAnswer() {
        return answer;
    }


    @Override
    public void setAnswer(Word answerWord) {
        this.answer.clear();
        this.answer.add(answerWord);
    }
}


