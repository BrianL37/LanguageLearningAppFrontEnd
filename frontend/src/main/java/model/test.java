package model;

import java.util.ArrayList;
import java.util.HashMap;

public class test {
  public static void main(String[] args) {
    LanguageLearningSystemFacade facade = LanguageLearningSystemFacade.getInstance();
    facade.login("JimSmith01", "SmithRocks");
    //facade.continueLanguage(ForeignLanguage.SPANISH);
    facade.startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.EASY);
    facade.startLesson(LessonTopic.FAMILY);
    facade.getUser().correct(facade.getLesson().getTopic(), facade.getLesson().getQuestions().get(0), 0);
    facade.getUser().correct(facade.getLesson().getTopic(), facade.getLesson().getQuestions().get(1), 1);
    facade.getUser().correct(facade.getLesson().getTopic(), facade.getLesson().getQuestions().get(2), 2);
    //facade.getUser().correct(facade.getLesson().getTopic(), facade.getLesson().getQuestions().get(3), 0);
    // facade.getLesson().getQuestions().get(0);
    // facade.getLesson().getQuestions().get(1);
    // facade.getLesson().getQuestions().get(2);
    System.out.println(facade.getUser().getLessonProgress(LessonTopic.FAMILY));


  }
}
