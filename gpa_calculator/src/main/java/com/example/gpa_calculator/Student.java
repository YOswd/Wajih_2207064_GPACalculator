package com.example.gpa_calculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Student {
    private String name;
    private String roll;
    private ObservableList<Course> courses = FXCollections.observableArrayList();

    public Student(String name, String roll) {
        this.name = name;
        this.roll = roll;
    }

    public String getName() { return name; }
    public String getRoll() { return roll; }
    public ObservableList<Course> getCourses() { return courses; }

    public double calculateGPA() {
        double totalCredits = 0;
        double totalPoints = 0;
        for (Course c : courses) {
            totalCredits += c.getCredit();
            totalPoints += c.getCredit() * c.getGradePoint();
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }
}
