package com.example.gpa_calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GPACalculate {

    @FXML private TextField courseNameField;
    @FXML private TextField courseCodeField;
    @FXML private TextField courseCreditField;
    @FXML private TextField teacher1Field;
    @FXML private TextField teacher2Field;
    @FXML private TextField gradeField;

    @FXML private Button addCourseButton;
    @FXML private Button calculateButton;

    @FXML
    public void initialize() {
        calculateButton.setDisable(true);
    }

    private double gradeToPoint(String grade) {
        return switch (grade.toUpperCase()) {
            case "A+" -> 4.00;
            case "A"  -> 3.75;
            case "A-" -> 3.50;
            case "B+" -> 3.25;
            case "B"  -> 3.00;
            case "B-" -> 2.75;
            case "C+" -> 2.50;
            case "C"  -> 2.25;
            case "D"  -> 2.00;
            case "F"  -> 0.00;
            default   -> -1;
        };
    }

    @FXML
    private void addCourse() {
        String nameText = courseNameField.getText().trim();
        String codeText = courseCodeField.getText().trim();
        String creditText = courseCreditField.getText().trim();
        String teacher1Text = teacher1Field.getText().trim();
        String teacher2Text = teacher2Field.getText().trim();
        String gradeText = gradeField.getText().trim();

        System.out.println("Name = '" + courseNameField.getText() + "'");
        System.out.println("Code = '" + courseCodeField.getText() + "'");
        System.out.println("Credit = '" + courseCreditField.getText() + "'");
        System.out.println("Teacher 1 : " + teacher1Field.getText() + "'");
        System.out.println("Teacher 2 : " + teacher2Field.getText() + "'");
        System.out.println("Grade = '" + gradeField.getText() + "'");


        if (nameText.isEmpty() || codeText.isEmpty() || creditText.isEmpty() || gradeText.isEmpty()) {
            showError("Please fill all fields.");
            return;
        }

        double credit;
        try {
            credit = Double.parseDouble(creditText);
            if (credit <= 0) {
                showError("Credit must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Credit must be a valid number.");
            return;
        }

        double gradePoint = gradeToPoint(gradeText);
        if (gradePoint == -1) {
            showError("Invalid grade! Example: A+, A-, B, C+");
            return;
        }

        CourseManager.addCourse(new Course(nameText, codeText, credit, gradePoint));

        courseNameField.clear();
        courseCodeField.clear();
        courseCreditField.clear();
        teacher1Field.clear();
        teacher2Field.clear();
        gradeField.clear();

        calculateButton.setDisable(false);
        showInfo("Course added successfully!");
    }

    @FXML
    private void calculateGPA() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) courseCreditField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            showError("Failed to load result page.");
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.show();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(msg);
        alert.show();
    }
}
