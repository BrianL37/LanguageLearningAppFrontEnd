package library;

/**
 * This class controls player movement on the board
 * @author Michael Carson 
 */
public class Player {
    private int row;
    private int col;
    private String symbol;

    public Player(String symbol) {
        this.row = 0; // Start at the top-left corner (row 0, col 0)
        this.col = 0;
        this.symbol = symbol;
    }

    /**
     * Moves the player horizontally across the grid. 
     * When the player reaches the end of a row, they move to the next row, starting at column 0.
     * @param steps the number of steps to move forward.
     * @param maxRows the total number of rows in the grid (15).
     * @param maxCols the total number of columns in the grid (15).
     */
    public void move(int steps, int maxRows, int maxCols) {
        // Move the player step by step
        for (int i = 0; i < steps; i++) {
            col++; // Move to the next column

            // If the player reaches the end of the row (col exceeds maxCols), move to the next row
            if (col >= maxCols) {
                col = 0; // Reset to the first column
                row++;   // Move to the next row

                // If the player reaches the bottom row, they can't move further
                if (row >= maxRows) {
                    row = maxRows - 1; // Stay in the last row
                    col = maxCols - 1; // Stay in the last column
                    break; // Stop moving, since we are at the last cell
                }
            }
        }
    }

    /**
     * getter for row 
     * @return
     */
    public int getRow() {
        return row;
    }
    /**
     * getter for column 
     * @return
     */
    public int getCol() {
        return col;
    }
    /**
     * getter for symbol 
     * @return
     */
    public String getSymbol() {
        return symbol;
    }
}

  