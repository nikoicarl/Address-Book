# Address Book Application

This is a simple address book application that allows users to add, view, edit, and delete contacts. It provides a user-friendly interface for managing contact information including first name, last name, email, phone number, and address.

## Features

- **Add Contact**: Users can add a new contact by providing their first name, last name, email, phone number, and address.
- **View Contact**: Contacts are displayed in a list format, allowing users to easily view all saved contacts.
- **Edit Contact**: Users can edit existing contact details including first name, last name, email, phone number, and address.
- **Delete Contact**: Contacts can be deleted individually, removing them from the address book.
- **Validation**: Input fields are validated to ensure all required fields are filled and correct formats are used (e.g., valid email address).
- **Confirmation**: Confirmation dialogs are provided for critical actions such as deleting a contact to prevent accidental data loss.

## Technologies Used

- **JavaFX**: The graphical user interface (GUI) of the application is built using JavaFX, providing a modern and responsive UI.
- **MySQL Database**: Contact information is stored in a MySQL database to allow for persistent storage and easy retrieval.
- **JDBC**: Java Database Connectivity (JDBC) is used to establish a connection between the application and the MySQL database, enabling data manipulation.
- **Maven**: Maven is used as the build automation tool and dependency management to streamline the project setup and management process.

## Getting Started

To run the Address Book application locally, follow these steps:

1. **Clone the Repository**: Clone this repository to your local machine using `git clone`.

2. **Set Up MySQL Database**: Ensure you have MySQL installed on your machine. Create a new database named `address_book_db` and execute the SQL script provided in the `database.sql` file to set up the necessary table structure.

3. **Configure Database Connection**: Update the database connection parameters (URL, username, password) in the `DatabaseUtil.java` file to match your MySQL database configuration.

4. **Build and Run**: Build the project using Maven and run the `AddressBook.java` file to launch the application.

## Usage

- Upon launching the application, you will be presented with the main window of the address book.
- Use the buttons to add, edit, or delete contacts as needed.
- Fill in the required fields and click "Submit" to add a new contact.
- Double-click on a contact in the list to edit its details.
- To delete a contact, select it from the list and click the "Delete" button.

## Contributing

Contributions are welcome! If you have any ideas, improvements, or feature requests, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
