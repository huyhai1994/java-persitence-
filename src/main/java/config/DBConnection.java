package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=jdbc_demo;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String password = "Password123!";
        return DriverManager.getConnection(url, user, password);
    }
}
