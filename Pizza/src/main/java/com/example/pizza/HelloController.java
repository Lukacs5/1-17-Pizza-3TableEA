package com.example.pizza;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Ez a feladat több java gyakorlati példával rendelkezik a feladat leirásnak megfelelően \n a fenti menüsor használatával lehetséges ezeken a végig naigálás");
    }
}