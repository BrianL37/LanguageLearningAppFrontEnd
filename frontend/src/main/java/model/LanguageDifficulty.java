package model;

/**
 * This helps categorize content based on learner proficiency.
 */
public enum LanguageDifficulty {

    /**
     * Represents an easy difficulty level, suitable for beginners
     * or those with minimal prior knowledge of the language.
     */
    EASY,

    /**
     * Represents a medium difficulty level, designed for learners
     * with some foundational knowledge seeking to build skills.
     */
    MEDIUM,

    /**
     * Represents a hard difficulty level, intended for advanced learners
     * looking to master complex concepts and vocabulary.
     */
    HARD;

    public static LanguageDifficulty fromString(String text) {
      if(text.equalsIgnoreCase("EASY"))
        return EASY;
      else if(text.equalsIgnoreCase("MEDIUM"))
        return MEDIUM;
      else 
        return HARD;
}
}