package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class BoardGameController {
    private ImageView token;
    private GameStateController gameStateController;

    @FXML
    private VBox boardGameRoot;

    @FXML
    private void initialize() {
        gameStateController = GameStateController.getInstance();
        gameStateController.setBoardGameController(this);

        try {
            GridPane grid = new GridPane();
            boardGameRoot.getChildren().add(grid);

            createBoard(grid);
            token = new ImageView(new Image("path/to/token/image.png"));
            token.setFitWidth(50);  // Adjust token size
            token.setFitHeight(50);
            updateTokenPosition();

            grid.getChildren().add(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createBoard(GridPane grid) {
        Button chooseLessonButton = new Button("Choose Lesson");
        chooseLessonButton.setOnAction(e -> goToChooseLesson());
        grid.add(chooseLessonButton, 0, 0);

        // Display spaces on the board (not buttons, just visual representation)
        for (int i = 1; i < 50; i++) {
            // Create labels for other spaces on the board
            // Placeholders for other spaces
            Button button = new Button("Space " + (i + 1));
            int column = i % 10;  // 10 columns
            int row = i / 10;    // 5 rows
            grid.add(button, column, row);
        }
    }

    public void updateTokenPosition() {
        int column = gameStateController.getCurrentPosition() % 10;
        int row = gameStateController.getCurrentPosition() / 10;
        GridPane.setColumnIndex(token, column);
        GridPane.setRowIndex(token, row);
    }

    private void goToChooseLesson() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/ChooseLesson.fxml"));
            GridPane grid = loader.load();

            Stage stage = (Stage) boardGameRoot.getScene().getWindow();
            stage.setScene(new Scene(grid));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void advanceToken() {
        gameStateController.advanceToken();
    }
}
