package com.example.gpa_calculator;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GPACalculate {

    @FXML private TextField studentNameField;
    @FXML private TextField studentRollField;
    @FXML private TextField courseNameField;
    @FXML private TextField courseCodeField;
    @FXML private TextField courseCreditField;
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
        String name = courseNameField.getText().trim();
        String code = courseCodeField.getText().trim();
        String creditText = courseCreditField.getText().trim();
        String gradeText = gradeField.getText().trim();

        if(name.isEmpty() || code.isEmpty() || creditText.isEmpty() || gradeText.isEmpty()){
            showError("Fill all course fields!");
            return;
        }

        double credit;
        try { credit = Double.parseDouble(creditText); }
        catch(NumberFormatException e) { showError("Credit must be number!"); return; }

        double gradePoint = gradeToPoint(gradeText);
        if(gradePoint == -1) { showError("Invalid grade!"); return; }

        CourseManager.addCourse(new Course(name, code, credit, gradePoint));
        clearCourseFields();
        calculateButton.setDisable(false);
    }

    @FXML
    private void calculateGPA() {
        String name = studentNameField.getText().trim();
        String roll = studentRollField.getText().trim();
        if(name.isEmpty() || roll.isEmpty()) { showError("Enter student info!"); return; }

        Student student = new Student(name, roll);
        student.getCourses().addAll(CourseManager.courses);
        CourseManager.clearCourses();

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                DatabaseManager.insertGPA(student);
                return null;
            }
        };

        task.setOnSucceeded(e -> loadResultPage());
        task.setOnFailed(e -> showError("Failed to save GPA!"));

        new Thread(task).start();
    }

    private void loadResultPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result.fxml"));
            Scene scene = new Scene(loader.load(), 700, 700);
            Stage stage = (Stage) studentNameField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){ e.printStackTrace(); }
    }

    private void showError(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.show();
    }

    private void clearCourseFields() {
        courseNameField.clear();
        courseCodeField.clear();
        courseCreditField.clear();
        gradeField.clear();
    }
}
