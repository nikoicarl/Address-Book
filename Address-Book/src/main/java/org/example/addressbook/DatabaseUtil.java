/**
 * Student Name: Carl Nikoi
 * Student Number: 100439006
 */
package org.example.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/address_book_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Load the MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("MySQL JDBC Driver not found", e);
        }
    }

    // Establish database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Close database connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Instead of just printing the stack trace, you might want to log the error
                e.printStackTrace();
            }
        }
    }
}
