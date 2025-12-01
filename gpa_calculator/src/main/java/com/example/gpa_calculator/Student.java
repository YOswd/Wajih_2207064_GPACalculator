package com.example.gpa_calculator;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String roll;
    private final List<Course> courses = new ArrayList<>();

    public Student(String name, String roll) {
        this.name = name;
        this.roll = roll;
    }

    public String getName() { return name; }
    public String getRoll() { return roll; }
    public List<Course> getCourses() { return courses; }

    public double calculateGPA() {
        double totalCredits = 0.0;
        double totalWeightedPoints = 0.0;

        for (Course c : courses) {
            double cr = c.getCredit();
            double gp = c.getGradePoint();
            totalCredits += cr;
            totalWeightedPoints += cr * gp;
        }

        if (totalCredits == 0) return 0.0;
        double gpa = totalWeightedPoints / totalCredits;
        return Math.round(gpa * 100.0) / 100.0;
    }
}
