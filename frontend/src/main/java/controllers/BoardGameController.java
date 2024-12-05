package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.LanguageLearningSystemFacade;

public class BoardGameController {

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView playerIcon;

    private int currentRow = 0;
    private int currentColumn = 0;
    private boolean moveRight = true;
    private LanguageLearningSystemFacade facade;

    @FXML
    private void initialize() {
        facade = LanguageLearningSystemFacade.getInstance();
        // Load the image directly in the controller
        Image playerImage = new Image(getClass().getResource("/images/player.png").toExternalForm());
        playerIcon.setImage(playerImage);
        // Initialize the player position
        movePlayer(currentRow, currentColumn);
        for(int i = 0; i < facade.getUser().getLanguageProgress() - 1; i++) {
            moveToNextSquare();
        }
    }

    public void movePlayer(int newRow, int newColumn) {
        if (newRow >= 0 && newRow < gridPane.getRowConstraints().size() &&
            newColumn >= 0 && newColumn < gridPane.getColumnConstraints().size()) {
            
            // Remove the player icon from its current position
            StackPane currentPane = (StackPane) gridPane.getChildren().stream()
                    .filter(node -> GridPane.getRowIndex(node) == currentRow &&
                                    GridPane.getColumnIndex(node) == currentColumn)
                    .findFirst()
                    .orElse(null);
            
            if (currentPane != null) {
                currentPane.getChildren().remove(playerIcon);
            }

            // Add the player icon to the new position
            StackPane newPane = (StackPane) gridPane.getChildren().stream()
                    .filter(node -> GridPane.getRowIndex(node) == newRow &&
                                    GridPane.getColumnIndex(node) == newColumn)
                    .findFirst()
                    .orElse(null);

            if (newPane != null) {
                newPane.getChildren().add(playerIcon);
                currentRow = newRow;
                currentColumn = newColumn;
            }
        }
    }

    public void moveToNextSquare() {
        if (moveRight) {
            if (currentColumn < gridPane.getColumnConstraints().size() - 1) {
                movePlayer(currentRow, currentColumn + 1);
            } else if (currentRow < gridPane.getRowConstraints().size() - 1) {
                movePlayer(currentRow + 1, currentColumn);
                moveRight = false;
            }
        } else {
            if (currentColumn > 0) {
                movePlayer(currentRow, currentColumn - 1);
            } else if (currentRow < gridPane.getRowConstraints().size() - 1) {
                movePlayer(currentRow + 1, currentColumn);
                moveRight = true;
            }
        }
    }
      @FXML
    public void switchToChooseLesson() {
        try {
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/ChooseLesson.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) gridPane.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
