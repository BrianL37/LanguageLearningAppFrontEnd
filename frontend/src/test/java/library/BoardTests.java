package library;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;

public class BoardTests {
    private Board board;

    @Before
    public void setUp() {
        board = new Board(15, 15);
    }

    @Test
    public void testBoardInitialization() {
        board.setup();
        assertNotNull(board.getGrid());
    }

    @Test
    public void testGridSize() {
        assertEquals(15, board.getRows());
        assertEquals(15, board.getColumns());
    }

    @Test
    public void testCellColorInitialization() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if ((i + j) % 2 == 0) {
                    assertEquals(Color.GRAY, board.getColorAt(i, j));
                } else {
                    assertEquals(Color.BLUE, board.getColorAt(i, j));
                }
            }
        }
    }

    @Test
    public void testUpdateBoard() {
        Player player = new Player("X");
        player.setPosition(1, 1);
        board.updateBoard(player);
        assertEquals("X", board.getGrid()[1][1]);
    }
}

