package org.example.addressbook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertData {

    private static final String INSERT_CONTACT_SQL = "INSERT INTO `contact`(`first_name`, `last_name`, `email`, `phone`, `address`) " +
            "SELECT ?, ?, ?, ?, ? " +
            "WHERE NOT EXISTS (SELECT 1 FROM `contact` WHERE `first_name` = ? AND `last_name` = ?)";

    public void insertContact(String firstName, String lastName, String email, String phone, String address) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT_SQL)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, firstName);
            preparedStatement.setString(7, lastName);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Contact inserted successfully.");
            } else {
                System.out.println("Contact already exists with the same first name and last name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

