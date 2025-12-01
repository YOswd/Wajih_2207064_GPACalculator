package com.example.gpa_calculator;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:C:/2207064_GPA_Calculator/gpa_calculator/gpa.db";

    static {
        try {
            System.out.println("Database path: " + new File("C:/2207064_GPA_Calculator/gpa_calculator/gpa.db").getAbsolutePath());
        } catch (Exception ignored) {}

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "roll TEXT NOT NULL," +
                    "gpa REAL NOT NULL)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertGPA(Student student) {
        String sql = "INSERT INTO students(name, roll, gpa) VALUES(?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            double gpa = student.calculateGPA();
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getRoll());
            pstmt.setDouble(3, gpa);
            pstmt.executeUpdate();
            System.out.println("Inserted student: " + student.getName() + " GPA=" + gpa);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static List<StudentRecord> fetchAllStudents() {
        List<StudentRecord> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id ASC";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new StudentRecord(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll"),
                        rs.getDouble("gpa")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Deleted student id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
