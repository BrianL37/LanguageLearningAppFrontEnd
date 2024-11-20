package library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the Login.fxml file
            Parent root = FXMLLoader.load(getClass().getResource("/library/Login.fxml"));
            primaryStage.setTitle("Language Learning App");
            primaryStage.setScene(new Scene(root, 400, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load Login.fxml. Ensure the file is in src/main/resources/library.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
