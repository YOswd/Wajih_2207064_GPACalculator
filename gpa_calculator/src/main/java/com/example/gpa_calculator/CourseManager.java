package com.example.gpa_calculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseManager {
    public static ObservableList<Course> courses = FXCollections.observableArrayList();
    public static ObservableList<Student> students = FXCollections.observableArrayList();

    public static void addCourse(Course c) { courses.add(c); }
    public static void clearCourses() { courses.clear(); }
    public static void addStudent(Student s) { students.add(s); }
    public static void clearStudents() { students.clear(); }
}
