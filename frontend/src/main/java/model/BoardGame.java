package library;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;



/**
 * this class has all the functionality and implementation of the board game 
 * @author Michael Carson 
 */
public class BoardGame {
    private static BoardGame instance;
    private Board boardgame;
    private Player player;
    
    /**
     * creates a board and uses "X" as player symbol 
     */
    private BoardGame(){
       this.boardgame = new Board();
        this.player = new Player("X"); 
      

        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(boardgame);
        frame.pack();
        frame.setLocationRelativeTo(null); // sets frame to middle of screen 
        frame.setVisible(true);
    }
    /**
     * creates instance of the BoardGame
     * @return
     */
    public static BoardGame getInstance(){
        if (instance == null){
            instance = new BoardGame();
            }
            return instance;
    }

    public static void loadGame(int languageProgress){
        return 0;
        
    }
    /**
     * prints the board and initial user location on the board 
     */
    public void startGame() {
        initializeBoard();
    }
    /**
     * moves the player symbol forward
     * @param steps
     */
    public void nextSpace(int steps){
        player.move(steps, 15, 15);
        boardgame.updateBoard(player);
    }
    public static void saveBoard(){
        return 0;
    }
    }
    
