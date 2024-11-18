package controllers;

import narrator.Narrator;
import library.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void playNarrator() {
        Narrator.playSound("Hola Mundo");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
