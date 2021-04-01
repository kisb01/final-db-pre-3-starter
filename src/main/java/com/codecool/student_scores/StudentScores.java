package com.codecool.student_scores;

import java.sql.*;

public class StudentScores {

    private final String DB_URL;
    private final String USERNAME;
    private final String PASSWORD;
    private final Connection conn;

    public StudentScores(String DB_URL, String USERNAME, String PASSWORD) {
        this.DB_URL = DB_URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.conn = getConnection();
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Database not reachable.");
        }
        return connection;
    }

    public String getCityWithHighestScore(){
        String query = "SELECT city, SUM(score) as score FROM student_scores GROUP BY city ORDER BY score DESC LIMIT 1";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public String getMostActiveStudent(){
        String query = "SELECT student_name, COUNT(score) as score FROM student_scores GROUP BY student_name ORDER BY score DESC LIMIT 1";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
