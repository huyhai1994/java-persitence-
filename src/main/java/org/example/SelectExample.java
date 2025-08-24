package org.example;

import config.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectExample {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT id, name, email from users";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(id + name + email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
