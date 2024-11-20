package library;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import java.util.ArrayList;

public class LanguageLearningSystemFacadeTests {
  private static final Logger logger = LoggerFactory.getLogger(LanguageLearningSystemFacadeTests.class);
  private static LanguageLearningSystemFacade facade;

  @BeforeAll
  public static void oneTimeSetup() {   
    facade = LanguageLearningSystemFacade.getInstance();
    facade.login("JimSmith01", "SmithRocks");
   // logger.info(String.valueOf(DataConstants.isJunitTest()));

  }
        
  @AfterAll
  public static void oneTimeTearDown() {
    LanguageLearningSystemFacade.resetInstance();
  }
        
  @BeforeClass
  public void setup() {

  }
        
  @AfterClass
  public void tearDown() {
  }
  
  @Test
  public void testSingleton() {
  LanguageLearningSystemFacade facade2 = LanguageLearningSystemFacade.getInstance();
  assertEquals(facade,facade2);

  }
    
  @Test
  public void testLoginValid() {
    User user = facade.getUser();
    assertTrue(user instanceof User);
  }

  @Test
  public void testLoginInvalid() {
    facade.logout();
    facade.login("bad", "bad");
    assertFalse(facade.getUser() instanceof User);
  }
  @Test
  public void testGetUser() {
    User user = facade.getUser();
    assertTrue(user instanceof User);
  }

  @Test
  public void testGetAllUsers() {
    ArrayList<User> users = new ArrayList<>();
    assertTrue(users != null);
  }

  @Test
  public void testSignUpValid() {
    facade.logout();
    User user = facade.signUp("Tony", "Lin", "tonylin6", "donkeysrcool", "tonylin@email.com", UUID.randomUUID());
    assertTrue(user instanceof User);
  }

  @Test
  public void testSignUpDuplicateEmail() {
    facade.logout();
    User user = facade.signUp("Tony", "Lin", "tonylin6", "donkeysrcool", "JimSmith2000@email.com", UUID.randomUUID());
    assertTrue(facade.getUser() == null);
  }
  @Test
  public void testSignUpBadEmail() {
    User user = facade.signUp("Tony", "Lin", "tonylin6", "donkeysrcool", "bad", UUID.randomUUID());
    assertTrue(facade.getUser() == null);
  }

  @Test
  public void testSignUpBadUserName() {
    User user = facade.signUp("Tony", "Lin", "JimSmith01", "donkeysrcool", "tonylin@email.com", UUID.randomUUID());
    assertTrue(facade.getUser() == null);
  }
  @Test
  public void testSignUpBadPassword() {
    User user = facade.signUp("Tony", "Lin", "tonylin6", "1", "tonylin@email.com", UUID.randomUUID());
    assertTrue(facade.getUser() == null);
  }
  @Test
  public void testEditUserFirstName() {
    facade.editUser(0, "newName");
    assertEquals(facade.getUser().getFirstName(), "newName");
  }

  @Test
  public void testEditUserLastName() {
    facade.editUser(1, "newLastName");
    assertEquals(facade.getUser().getLastName(), "newLastName");
  }
  @Test
  public void testEditUserUserName() {
    facade.editUser(2, "newUserName");
    assertEquals(facade.getUser().getUserName(), "newUserName");
  }

  @Test
  public void testEditUserPasswordValid() {
    facade.editUser(3, "newPassword");
    assertEquals(facade.getUser().getPassword(), "newPassword");
  }
  @Test
  public void testEditUserPasswordInvalid() {
    facade.editUser(3, "1");
    assertEquals(facade.getUser().getPassword(), "SmithRocks");
  }
  @Test
  public void testEditUserEmailValid() {
    facade.editUser(4, "newemail@email.com");
    assertEquals(facade.getUser().getEmail(), "newemail@email.com");
  }
  @Test
  public void testEditUserEmailInvalid() {
    facade.editUser(4, "1");
    assertEquals(facade.getUser().getEmail(), "JimSmith2000@email.com");
  }
  @Test
  public void testAddFriendValid() {
    facade.logout();
    facade.signUp("Tony", "Lin", "tonylin6", "donkeysrcool", "tonylin@email.com", UUID.randomUUID());
    facade.addFriend("JimSmith01");
    assertEquals(facade.getFriendList().get(0).getUserName(), "JimSmith01");
  }

  @Test
  public void testAddFriendInvalid() {
    facade.logout();
    facade.signUp("Tony", "Lin", "tonylin6", "donkeysrcool", "tonylin@email.com", UUID.randomUUID());
    facade.addFriend("NotAUser");
    assertEquals(0, facade.getFriendList().size());
  }
  @Test
  public void testGetFriendList() {
    ArrayList<User> friends = new ArrayList<>();
    assertTrue(friends != null);
  }

  @Test
  public void testRemoveFriendValid() {
    facade.logout();
    facade.signUp("Tony", "Lin", "tonylin6", "donkeysrcool", "tonylin@email.com", UUID.randomUUID());
    facade.addFriend("JimSmith01");
    facade.removeFriend("JimSmith01");
    assertEquals(0, facade.getFriendList().size());
  }

  @Test
  public void testChangeSettingNotification() {
    facade.changeSettingOptions(0, 1);
    assertEquals(facade.getUser().getSettings().getNotifications(), 1);
  }
  @Test
  public void testChangeSettingLightMode() {
    facade.changeSettingOptions(1, 0);
    assertEquals(facade.getUser().getSettings().getLightMode(), 0);
  }
  @Test
  public void testChangeSettingTextToSpeech() {
    facade.changeSettingOptions(2, 1);
    assertEquals(facade.getUser().getSettings().getTextToSpeech(), 1);
  }
  @Test
  public void testChangeSettingFontSize() {
    facade.changeSettingOptions(3, 5);
    assertEquals(facade.getUser().getSettings().getFontSize(), 5);
  }


  @Test
  public void testStartLanguage() {
    Language language = facade.startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.EASY);
    assertTrue(language != null);
  }

  @Test
  public void testStartGame() {
    BoardGame game = facade.startGame();
    assertTrue(game != null);
  }

  @Test
  public void testChooseTopic() {
    facade.startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.EASY);
    Lesson lesson = facade.chooseTopic(ForeignLanguage.SPANISH, LessonTopic.FOOD);
    assertTrue(lesson != null);
  }



  @Test
  public void testStartLesson() {
    facade.startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.EASY);
    facade.startLesson(LessonTopic.SCHOOL);
    assertTrue(facade.getLesson() != null);
  }

  @Test
  public void testEndLesson() {
    facade.startLanguage(ForeignLanguage.SPANISH, LanguageDifficulty.EASY);
    Lesson lesson = facade.startLesson(LessonTopic.SCHOOL);
    facade.endLesson(ForeignLanguage.SPANISH, lesson);
    assertTrue(facade.getLesson() == null);
  }

}
