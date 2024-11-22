package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Matching implements Question{
private ArrayList<Word> words;


public Matching(ArrayList<Word> words){
    this.words = words;
}
public void addWordPair(Word word1){
    words.add(word1);
}
public boolean isCorrect(String foreign, String english){
    for(Word word : words) {
      if(word.getForeign().equalsIgnoreCase(foreign) && word.getEnglish().equalsIgnoreCase(english))
        return true;   
} 
    return false;
}
public ArrayList<Word> getContent() {
  return this.words;
}


public void setPrompt(ArrayList<Word> prompt) {
  this.words = prompt;
}


public ArrayList<Word> getAnswer() {
  return this.words;
}

public void setAnswer(Word answer) {

}

public boolean isCorrect(ArrayList<Word> answer) {
    return answer == this.words;
}
}
