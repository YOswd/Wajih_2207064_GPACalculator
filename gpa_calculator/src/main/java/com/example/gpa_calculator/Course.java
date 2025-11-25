package com.example.gpa_calculator;

public class Course {
    private String name;
    private String code;
    private double credit;
    private double gradePoint;

    public Course(String name, String code, double credit, double gradePoint) {
        this.name = name;
        this.code = code;
        this.credit = credit;
        this.gradePoint = gradePoint;
    }

    public String getName() { return name; }
    public String getCode() { return code; }
    public double getCredit() { return credit; }
    public double getGradePoint() { return gradePoint; }
}
