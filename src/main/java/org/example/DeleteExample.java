package org.example;

import config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteExample {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE from users where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 2);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " row(s) deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
