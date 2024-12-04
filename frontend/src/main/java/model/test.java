package model;

public class test {
    public static void main (String[] args) {
        LanguageLearningSystemFacade facade = LanguageLearningSystemFacade.getInstance();
        facade.login("JimSmith01", "SmithRocks");
        facade.continueLanguage(ForeignLanguage.SPANISH);
        facade.startLesson(LessonTopic.PETS);
        Lesson lesson = facade.getLesson();
        facade.getUser().correct(lesson.getTopic(), lesson.getMultipleChoice(0), 0);
        facade.getUser().correct(lesson.getTopic(), lesson.getMultipleChoice(0), 1);
        System.out.println(facade.getUser().getLessonProgress(lesson.getTopic()));
    }
}
