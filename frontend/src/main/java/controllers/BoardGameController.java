package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BoardGameController {

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView playerIcon;

    private int currentRow = 0;
    private int currentColumn = 0;
    private boolean moveRight = true;

    @FXML
    private void initialize() {
        // Load the image directly in the controller
        Image playerImage = new Image(getClass().getResource("/images/player.png").toExternalForm());
        playerIcon.setImage(playerImage);

        // Initialize the player position
        movePlayer(currentRow, currentColumn);
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

}
