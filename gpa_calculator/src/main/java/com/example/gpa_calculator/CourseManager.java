package com.example.gpa_calculator;

import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private static List<Course> courses = new ArrayList<>();

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static double calculateCGPA() {
        double totalCredits = 0;
        double totalPoints = 0;

        for(Course course:courses) {
            totalCredits += course.getCredit();
            totalPoints += course.getCredit()*course.getGradePoint();
        }

        if(totalCredits == 0) return 0.0;
        return totalPoints/totalCredits;
    }

    public static void clear() {
        courses.clear();
    }
}
