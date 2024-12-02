package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * @author Michael Carson 
 * Creates a button in the first space to access the choose lesson page 
 * 
 * Creates the board which tracks user progress 
 */
public class BoardGameController extends Application {
    private ImageView token;
    private GameStateController gameStateController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Board Game");

        gameStateController = GameStateController.getInstance();
        gameStateController.setBoardGameController(this);

        try {
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid, 800, 800);
            primaryStage.setScene(scene);
            primaryStage.show();

            createBoard(grid);
            token = new ImageView(new Image("path/to/your/token/image.png"));
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
        for (int i = 0; i < 50; i++) {
            // You can add Labels or other nodes to visualize the spaces
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

            Stage stage = (Stage) token.getScene().getWindow();
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

