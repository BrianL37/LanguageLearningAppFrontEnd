package library;
/**
 * @author Michael Carson 
 * some of these methods are placeholders for future tests 
 * due to the methods not being implemented/not being functional 
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;

public class BoardGameTests {
    private BoardGame boardGame;

    @Before
    public void setUp() {
        boardGame = BoardGame.getInstance();
    }

    @Test
    public void testSingletonInstance() {
        BoardGame instance1 = BoardGame.getInstance();
        BoardGame instance2 = BoardGame.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testStartGame() {
        boardGame.startGame();
        assertNotNull(boardGame.getInstance());
    }

    @Test
    public void testNextSpace() {
        boardGame.nextSpace(5);
        assertEquals(expectedPlayerPosition, boardGame.getPlayer().getPosition());
    }

    @Test
    public void testLoadGame() {
        int progress = BoardGame.loadGame(0);
        assertEquals(0, progress);
    }

    @Test
    public void testSaveBoard() {
        int result = BoardGame.saveBoard();
        assertEquals(0, result);
    }
}
