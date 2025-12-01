package com.example.gpa_calculator;

public class Course {
    private String name;
    private String code;
    private double credit;
    private double gradePoint;
    private String teacher1;
    private String teacher2;

    public Course(String name, String code, double credit, double gradePoint, String teacher1, String teacher2) {
        this.name = name;
        this.code = code;
        this.credit = credit;
        this.gradePoint = gradePoint;
        this.teacher1 = teacher1;
        this.teacher2 = teacher2;
    }

    public String getName() { return name; }
    public String getCode() { return code; }
    public double getCredit() { return credit; }
    public double getGradePoint() { return gradePoint; }
    public String getTeacher1() { return teacher1; }
    public String getTeacher2() { return teacher2; }
}
