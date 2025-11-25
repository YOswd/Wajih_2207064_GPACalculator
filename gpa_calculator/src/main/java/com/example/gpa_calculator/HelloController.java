package com.example.gpa_calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private void onHelloButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gpa_calculator.fxml"));
            Scene scene = new Scene(loader.load(),800,800);

            Stage stage = (Stage)welcomeText.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
