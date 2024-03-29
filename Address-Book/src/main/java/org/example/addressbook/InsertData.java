package org.example.addressbook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertData {

    private static final String INSERT_CONTACT_SQL = "INSERT INTO `contact`(`first_name`, `last_name`, `email`, `phone`, `address`) VALUES (?, ?, ?, ?, ?)";

    public void insertContact(String firstName, String lastName, String email, String phone, String address) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT_SQL)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
