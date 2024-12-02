package controllers;
/**
 * @author Michael Carson 
 * This Singleton manages and keeps track of the state of the game
 * this is used to keep track of player token location 
 * 
 * Communicates between BoardGameController and QuestionController
 * 
 * Method to advance player token and update UI to reflect this change
 */
public class GameStateController {
        private static GameStateController instance;
        private int currentPosition;
        private BoardGameController boardGameController;
    
        private GameStateController() {
            currentPosition = 0;
        }
    
        public static GameStateController getInstance() {
            if (instance == null) {
                instance = new GameStateController();
            }
            return instance;
        }
    
        public int getCurrentPosition() {
            return currentPosition;
        }
    
        public void advanceToken() {
            currentPosition++;
            if (boardGameController != null) {
                boardGameController.updateTokenPosition();
            }
        }
    
        public void setBoardGameController(BoardGameController controller) {
            this.boardGameController = controller;
        }
    }
    
