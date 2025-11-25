package com.example.gpa_calculator;

public class StudentRecord {
    private int id;
    private String name;
    private String roll;
    private double gpa;

    public StudentRecord(int id, String name, String roll, double gpa) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.gpa = gpa;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRoll() { return roll; }
    public double getGpa() { return gpa; }
}
