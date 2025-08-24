import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        openDatabaseConnection();
        closeDatabaseConnection();
    }

    private static void openDatabaseConnection() throws SQLException {
        System.out.println("Opening database connection...");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jdbc_demo",
                "root",
                "password"
        );
        System.out.println("connection valid: " + connection.isValid(0));
    }

    private static void closeDatabaseConnection() throws SQLException {
        connection.close();
    }
}
