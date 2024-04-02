/**
 * Student Name: Carl Nikoi
 * Student Number: 100439006
 */
package org.example.addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public Contact(String firstName, String lastName, String email, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;


    }

    // Getter methods for accessing the fields
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Getters and setters (not provided in your code)

    public static List<Contact> getAllContactsFromDatabase() {
        List<Contact> contacts = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection (replace with your database details)
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book_db", "root", "");

            // Create SQL statement
            statement = connection.createStatement();

            // Execute SQL query to retrieve contacts
            resultSet = statement.executeQuery("SELECT * FROM contact");

            // Iterate through the result set and create Contact objects
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");

                Contact contact = new Contact(firstName, lastName, email, phone, address);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contacts;
    }
}
