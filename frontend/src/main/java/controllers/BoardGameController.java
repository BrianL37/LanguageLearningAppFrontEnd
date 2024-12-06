package controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class BoardGameController {
    private Stage primaryStage;
    @FXML
    private VBox root;
    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView playerIcon;
    @FXML
    private ImageView arrow1;
    @FXML
    private ImageView arrow2;
    @FXML
    private ImageView arrow3;
    @FXML
    private ImageView arrow4;
    @FXML
    private ImageView arrow5;
    @FXML
    private ImageView arrow6;
    @FXML
    private ImageView arrow7;
    @FXML
    private ImageView arrow8;

    @FXML private Button homeButton;
    @FXML private ImageView arrow9;
    private List<ImageView> arrows;
    private int currentRow = 0;
    private int currentColumn = 0;
    private boolean moveRight = true;
    private LanguageLearningSystemFacade facade;

    @FXML
    private void initialize() {
        arrows = new ArrayList<>();
        arrows.add(arrow1);
        arrows.add(arrow2);
        arrows.add(arrow3);
        arrows.add(arrow4);
        arrows.add(arrow5);
        arrows.add(arrow6);
        arrows.add(arrow7);
        arrows.add(arrow8);
        arrows.add(arrow9);
        hideAllArrows();
        primaryStage = new Stage();
        facade = LanguageLearningSystemFacade.getInstance();
        // Load the image directly in the controller
        Image playerImage = new Image(getClass().getResource("/images/player.png").toExternalForm());
        playerIcon.setImage(playerImage);
        if(facade.getUser().getSettings().getLightMode() == 0) {
          updateBoardColors(true);
        }
        // Initialize the player position
        movePlayer(currentRow, currentColumn);
        for(int i = 0; i < facade.getUser().getLanguageProgress() - 1; i++) {
            moveToNextSquare();
        }
        updateArrows();
        if (currentRow == 4 && currentColumn == 9) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1)); // 1-second delay
            delay.setOnFinished(event -> showInfoAlert("Congratulations! You have completed the game!"));
            delay.play();
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
    public void switchToHome() {
        try {
            // Load the login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/library/Homepage.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) gridPane.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBoardColors(boolean darkModeBoard) { 
        root.setStyle("-fx-background-color: #36454F;");
        homeButton.setStyle(homeButton.getStyle() + "-fx-text-fill: #36454F;");
        for (int row = 0; row < gridPane.getRowConstraints().size(); row++) { 
            final int currentRow = row;
            for (int col = 0; col < gridPane.getColumnConstraints().size(); col++) { 
                final int currentCol = col;
                StackPane pane = (StackPane) gridPane.getChildren().stream().filter(node -> GridPane.getRowIndex(node) == currentRow && GridPane.getColumnIndex(node) == currentCol) .findFirst() .orElse(null); 
                if (pane != null) {
                    if ((row + col) % 2 == 0) { 
                        //change even spaces 
                        if (darkModeBoard) { 
                            pane.setStyle("-fx-background-color: #8CE1F5;");
                        }
                        //change odd spaces 
                     } else { 
                        if (darkModeBoard) { 
                            pane.setStyle("-fx-background-color: #5AA9E6;"); 
                        } 
                } 
            } 
        } 
    }
}

public void hideAllArrows() {
    for (ImageView arrow : arrows) {
        arrow.setVisible(false);
    }
}
    // Method to show a specific arrow
    public void showArrow(int index) {
        if (index >= 0 && index < arrows.size()) {
            arrows.get(index).setVisible(true);
        }
    }

    // Method to hide a specific arrow
    public void hideArrow(int index) {
        if (index >= 0 && index < arrows.size()) {
            arrows.get(index).setVisible(false);
        }
    }
    public void updateArrows() {
        for (ImageView arrow : arrows) {
            StackPane parent = (StackPane) arrow.getParent();
            Integer arrowRow = GridPane.getRowIndex(parent);
            Integer arrowColumn = GridPane.getColumnIndex(parent);
    
    
            // Use default values if row or column index is null
            int row = (arrowRow != null) ? arrowRow : -1;
            int column = (arrowColumn != null) ? arrowColumn : -1;
    
            if (row == currentRow && column == currentColumn) {
                arrow.setVisible(false);
            } else {
                arrow.setVisible(true);
            }
        }
    }

        private void showInfoAlert(String message) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Completed");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.show();
    }
}
