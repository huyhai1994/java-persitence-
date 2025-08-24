package org.example;

import config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertExample {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (id,name, email) Values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 9);
            pstmt.setString(2, "Alice");
            pstmt.setString(3, "Alice@example.com");
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " row(s) inserted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
