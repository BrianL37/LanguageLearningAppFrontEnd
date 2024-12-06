package model;

import java.util.ArrayList;
import java.util.HashMap;

public class test {
  public static void main(String[] args) {
    LanguageLearningSystemFacade facade = LanguageLearningSystemFacade.getInstance();
    facade.login("JimSmith01", "SmithRocks");
    System.out.println(facade.getUser().getLessonProgress(LessonTopic.SCHOOL));
  }
}
