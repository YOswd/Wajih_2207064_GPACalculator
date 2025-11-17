package com.example.gpa_calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Result {

    @FXML
    private TextField gpaField;

    @FXML
    public void initialize() {
        double cgpa = CourseManager.calculateCGPA();
        gpaField.setText(String.format("%.2f", cgpa));
    }

    @FXML
    private void restart() {
        try {
            CourseManager.clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gpa_calculator.fxml"));
            Scene scene = new Scene(loader.load(), 600, 600);

            Stage stage = (Stage) gpaField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @FXML
    private void exitApp() {
        System.exit(0);
    }
}
