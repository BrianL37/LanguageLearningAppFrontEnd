package library;

    /** 
Each topic provides a thematic focus for vocabulary and phrases,
helping learners to build relevant language skills.
    */
public enum LessonTopic {

    /** 
Represents the school topic, covering vocabulary related to educational
settings, subjects, school supplies, and common school-related interactions.
    */
SCHOOL,

    /** 
Represents the family topic, focusing on vocabulary and phrases
related to family members, relationships, and family activities.
    */
FAMILY,

    /**
Represents the weather topic, covering vocabulary and expressions
used to describe different weather conditions and seasonal changes.
    */
WEATHER,

    /** 
Represents the pets topic, focusing on vocabulary related to animals,
pet care, and common phrases associated with pets.
    */
PETS,

    /** 
Represents the food topic, covering vocabulary and phrases
associated with food items, meals, cooking, and dining.
    */
FOOD;
public static LessonTopic fromString(String text) {
    if(text.equalsIgnoreCase("school"))
      return SCHOOL;
    else if(text.equalsIgnoreCase("family"))
      return FAMILY;
    else if(text.equalsIgnoreCase("weather"))
      return WEATHER;
    else if(text.equalsIgnoreCase("PETS"))
      return PETS;
    else 
      return FOOD;
}
}
