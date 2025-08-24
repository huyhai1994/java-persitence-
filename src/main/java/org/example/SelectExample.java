package org.example;

import config.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class SelectExample {
    public static void main(String[] args) {
        try(Connection connection = DBConnection.getConnection()){

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
