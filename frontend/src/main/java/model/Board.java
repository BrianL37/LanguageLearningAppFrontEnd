package library;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

/**
 * This class sets up the board for the board game the user plays 
 * @author Michael Carson 
 */
public class Board extends JPanel{
    private int rows;
    private int columns;
    private String[][] grid;
    private Color [][] colors;
    private static final int CELL_SIZE = 50; // pixel size 

    // creates a board of the size 15 x 15 
    public Board(){
        this(10, 10);
    }

    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.grid = new String[rows][columns];
        this.colors =new Color [rows][columns];
        initilizeBoard();
        setPreferredSize(new Dimension(columns * CELL_SIZE, rows * CELL_SIZE));
    }
    /**
     * creates the board with Grey and Blue checkerboard format
     */
    private void initilizeBoard(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                grid[i][j] = "_";
                // Alternate colors between grey and blue in a checkerboard pattern
            if ((i + j) % 2 == 0) {
                colors[i][j] = Color.GRAY;  // Grey color for even sum of indices
            } else {
                colors[i][j] = Color.BLUE;  // Blue color for odd sum of indices
            }
        }
            }
        }
    
    //draws the board 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Cast Graphics to Graphics2D for better control over drawing
                Graphics2D g2d = (Graphics2D) g;

                // Set the color of the current cell
                g2d.setColor(colors[i][j]);

                // Draw the filled rectangle for the cell background
                g2d.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                // Draw the grid line
                g2d.setColor(Color.BLACK); // Set color back to black for grid lines
                g2d.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                // Draw the player's symbol (or underscore for empty)
                g2d.setColor(Color.BLACK); // Text in black
                g2d.drawString(grid[i][j], j * CELL_SIZE + CELL_SIZE / 2 - 5, i * CELL_SIZE + CELL_SIZE / 2 + 5);
            }
        }
    }


    public void updateBoard(Player player) {
        //initializeBoard(); // Reset the board
        grid[player.getRow(columns)][player.getCol(columns)] = player.getSymbol();
        repaint(); // Repaint the board to reflect changes
    }


    public boolean setup(){
        initilizeBoard();
        return true;
    }
}
   