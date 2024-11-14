package library;

import java.util.HashMap;
import java.util.function.BooleanSupplier;
import java.util.ArrayList;
/**
 * A class to manage a language learning dictionary of words and their
 * translations.
 */
public class DictionaryManager {
    private HashMap<LanguageDifficulty, HashMap<LessonTopic, HashMap<Word, String>>> data;
    private static DictionaryManager manager;
    
    public static DictionaryManager getInstance() {
        if(manager == null){
          manager = new DictionaryManager();
          manager = DataLoader.loadDictionary(manager);
        }
        return manager;
      }

    DictionaryManager() {
        this.data = new HashMap<>();
        for(LanguageDifficulty difficulty : LanguageDifficulty.values()) {
          data.put(difficulty, new HashMap());
          for(LessonTopic topic : LessonTopic.values()) {
            data.get(difficulty).put(topic, new HashMap<>());
          }
        }
    }


    /**
     * Adds a word to the dictionary.
     *
     * @param difficulty The difficulty level of the word.
     * @param category   The category of the word.
     * @param foreign    The foreign word.
     * @param english    The English translation of the word.
     */
    public void addWord(LanguageDifficulty difficulty, LessonTopic category, String foreign, String english,
            String meaning) {
        Word word = new Word(foreign, english);
        data.get(difficulty).get(category).put(word, meaning);
    }

    /**
     * Retrieves the translation of a word.
     *
     * @param difficulty The difficulty level of the word.
     * @param category   The category of the word.
     * @param foreign    The foreign word to translate.
     * @return The translation of the word, or "Translation not found." if the
     *         translation is not in the dictionary.
     */
    public String getMeaning(LanguageDifficulty difficulty, LessonTopic category, String foreign) {
        for (Word word : data.get(difficulty).get(category).keySet()) {
            if (word.getForeign().equalsIgnoreCase(foreign)) {
                return data.get(difficulty).get(category).get(word);
            }
        }
        return "Translation not found.";
    }
    
    public ArrayList<Word> getWordsByTopic(LanguageDifficulty difficulty, LessonTopic topic) {
      ArrayList<Word> wordList = new ArrayList();
      for(Word word : data.get(difficulty).get(topic).keySet()) {
        wordList.add(word);
      }
      return wordList;
    }
    /**
     * Retrieves the meaning of a word.
     *
     * @param difficulty The difficulty level of the word.
     * @param category   The category of the word.
     * @param foreign    The foreign word to get the meaning for.
     * @return A string containing the meaning of the word, or "Word not found." if
     *         the word is not in the dictionary.
     */
    public Word getWord(LanguageDifficulty difficulty, LessonTopic topic, String foreign) {
        for (Word word : data.get(difficulty).get(topic).keySet()) {
            if (word.getForeign().equalsIgnoreCase(foreign)) {
                return word;
            }
        }
        return null;
    }

    public BooleanSupplier hasWord(String string) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'hasWord'");
    }
}