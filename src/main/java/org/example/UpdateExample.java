package org.example;

import config.DBConnection;

import java.sql.*;

public class UpdateExample {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "update users set email = ? where id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "newalice@example.com");
            pstmt.setInt(2, 1); // update user with id = 1
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " row(s) updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
