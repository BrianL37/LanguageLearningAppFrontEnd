package library;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.UUID;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;


/**
 * This class tests the functions of the User class using JUnit tests
 * @author Tony Lin
 */
public class UserTests {
  private static Lesson lesson;
  private static User bob;
  private static User amy;
  private static User jeff;
  
  @BeforeAll
  public static void oneTimeSetup() {
    bob = new User("Bob", "Smith", "bobbysmith", "bobbysmith", "bobbysmith@email.com", UUID.randomUUID());
    amy = new User("Amy", "Horton", "amyhorton5", "ilovelearning", "amyhorton2000@email.com",UUID.randomUUID());
    bob.addFriend(amy);
    amy.addFriend(bob);
  }
	
  @AfterAll
  public static void oneTimeTearDown() {
  }
	
  @BeforeClass
  public void setup() {

  }
	
  @AfterClass
  public void tearDown() {
  }

  @Test
  public void testConstructorBadPassword() {
    User tony = new User("Tony", "Lin", "tonylin6", "1", "tonylin@email.com", UUID.randomUUID());
    assertFalse(tony instanceof User);
  }

  @Test 
  public void testConstructorBadEmail() {
    User tony = new User("Tony", "Lin", "tonylin6", "donkeysrcool", "bad", UUID.randomUUID());
    assertFalse(tony instanceof User);
  }

  @Test
  public void testSetEmailBadArgument() {
    bob.setEmail("bad");
    assertFalse(bob.getEmail().equals("bad"));
  }

  @Test
  public void testSetPasswordBadArgument() {
    bob.setPassword("bad");
    assertFalse(bob.getPassword().equals("bad"));
  }

  @Test
  public void testValidUser() {
    User tony = new User("Tony", "Lin", "tonylin6", "donkeysrcool", "tonylin@email.com", UUID.randomUUID());
    assertTrue(tony instanceof User);
    }

  @Test
  public void testAddFriendValid() {
    assertEquals(bob, amy.getFriendList().get(0));
  }

  @Test
  public void testRemoveFriend() {
    bob.removeFriend(amy);
    assertEquals(bob.getFriendList().size(), 0);
  }

  @Test
  public void testCorrectValid() {
    lesson = new Lesson(LanguageDifficulty.EASY, LessonTopic.FOOD);
    bob.setIncomplete(LessonTopic.FOOD, lesson.getQuestions());
    bob.correct(LessonTopic.FOOD, lesson.getMatching());
    boolean success = true;
    for(Object obj : bob.getIncomplete(LessonTopic.FOOD))
      if(obj instanceof Matching)
        success = false;
    assertTrue(success);
  }

  @Test
  public void testIncorrectValid() {
    lesson = new Lesson(LanguageDifficulty.EASY, LessonTopic.FOOD);
    bob.setIncomplete(LessonTopic.FOOD, lesson.getQuestions());
    bob.incorrect(LessonTopic.FOOD, lesson.getMatching());
    boolean success = false;
    for(Object obj : bob.getIncomplete(LessonTopic.FOOD))
      if(obj instanceof Matching)
        success = true;
    assertTrue(success);
  }
    @Test
  public void testChangeSettingNotificationValid() {
    bob.changeSetting(0, 1);
    assertEquals(bob.getSettings().getNotifications(), 1);
  }
  @Test
  public void testChangeSettingLightModeValid() {
    bob.changeSetting(1, 0);
    assertEquals(bob.getSettings().getLightMode(), 0);
  }
  @Test
  public void testChangeSettingTextToSpeechValid() {
    bob.changeSetting(2, 1);
    assertEquals(bob.getSettings().getTextToSpeech(), 1);
  }
  @Test
  public void testChangeSettingFontSizeValid() {
    bob.changeSetting(3, 5);
    assertEquals(bob.getSettings().getFontSize(), 5);
  }
  @Test
  public void testChangeSettingNotificationInvalid() {
    bob.changeSetting(0, 5);
    assertEquals(bob.getSettings().getNotifications(), 0);
  }
  @Test
  public void testChangeSettingLightModeInvalid() {
    bob.changeSetting(1, 5);
    assertEquals(bob.getSettings().getLightMode(), 1);
  }
  @Test
  public void testChangeSettingTextToSpeechInvalid() {
    bob.changeSetting(2, 5);
    assertEquals(bob.getSettings().getTextToSpeech(), 0);
  }
  @Test
  public void testChangeSettingFontSizeInvalid() {
    bob.changeSetting(3, -5);
    assertEquals(bob.getSettings().getFontSize(), 10);
  }
}
