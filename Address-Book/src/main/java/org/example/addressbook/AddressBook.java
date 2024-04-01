/**
 * Student Name: Carl Nikoi
 * Student Number: 100439006
 */
package org.example.addressbook;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

public class AddressBook extends Application {

    private final InsertData insertData = new InsertData();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Address Book Application");

        // Create the registration form pane
        GridPane gridPane = createAddressBookForm();
        // Create a scene with the registration form gridPane as the root node.
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private GridPane createAddressBookForm() {
        // New Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstraints = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstraints.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstraints);

        // Add UI Controls
        addUIControls(gridPane);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Add Contact");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add First Name Label
        Label firstNameLabel = new Label("First Name: ");
        gridPane.add(firstNameLabel, 0, 1);

        // Add First Name Text Field
        TextField firstNameField = new TextField();
        firstNameField.setPrefHeight(40);
        gridPane.add(firstNameField, 1, 1);

        // Add Last Name Label
        Label lastNameLabel = new Label("Last Name: ");
        gridPane.add(lastNameLabel, 0, 2);

        // Add Last Name Text Field
        TextField lastNameField = new TextField();
        lastNameField.setPrefHeight(40);
        gridPane.add(lastNameField, 1, 2);

        // Add Email Label
        Label emailLabel = new Label("Email : ");
        gridPane.add(emailLabel, 0, 3);

        // Add Email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 3);

        // Add Phone Label
        Label phoneLabel = new Label("Phone : ");
        gridPane.add(phoneLabel, 0, 4);

        // Add Phone Field
        TextField phoneField = new TextField();
        phoneField.setPrefHeight(40);
        gridPane.add(phoneField, 1, 4);

        // Add Address Label
        Label addressLabel = new Label("Address: ");
        gridPane.add(addressLabel, 0, 5);

        // Add Address TextArea
        TextArea addressArea = new TextArea();
        addressArea.setPrefRowCount(4); // Set the number of rows
        addressArea.setWrapText(true);
        gridPane.add(addressArea, 1, 5);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 6, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        // Add View All Contacts Button
        Button viewAllButton = new Button("View All Contacts");
        viewAllButton.setPrefHeight(40);
        viewAllButton.setDefaultButton(false);
        viewAllButton.setPrefWidth(150);
        gridPane.add(viewAllButton, 0, 7, 2, 1);
        GridPane.setHalignment(viewAllButton, HPos.CENTER);
        GridPane.setMargin(viewAllButton, new Insets(20, 0, 20, 0));

        // Submit Button Event
        submitButton.setOnAction(event -> {
            if (validateFields(firstNameField, lastNameField, emailField, phoneField, addressArea)) {
                // Call the insertContact method
                insertData.insertContact(firstNameField.getText(), lastNameField.getText(),
                        emailField.getText(), phoneField.getText(), addressArea.getText());

                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(),
                        "Contact Added!", "Contact added successfully!");
                // Clear input fields after submission
                firstNameField.clear();
                lastNameField.clear();
                emailField.clear();
                phoneField.clear();
                addressArea.clear();
            }
        });

        // View All Contacts Button Event
        viewAllButton.setOnAction(event -> {
            // Call method to show all contacts
            showAllContacts();
        });

    }

    private boolean validateFields(TextField firstNameField, TextField lastNameField, TextField emailField,
                                   TextField phoneField, TextArea addressArea) {
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                addressArea.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, firstNameField.getScene().getWindow(),
                    "Form Error!", "Please fill in all fields");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    private void showAllContacts() {
        List<Contact> contacts = Contact.getAllContactsFromDatabase();

        // Displaying contacts in a dialog or console (replace this with your UI implementation)
        StringBuilder contactDetails = new StringBuilder();
        for (Contact contact : contacts) {
            contactDetails.append("Name: ").append(contact.getFirstName()).append(" ").append(contact.getLastName()).append("\n");
            contactDetails.append("Email: ").append(contact.getEmail()).append("\n");
            contactDetails.append("Phone: ").append(contact.getPhone()).append("\n");
            contactDetails.append("Address: ").append(contact.getAddress()).append("\n\n");
        }

        // Show contact details in a dialog or console (replace this with your UI implementation)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Contacts");
        alert.setHeaderText(null);
        alert.setContentText(contactDetails.toString());
        alert.showAndWait();
    }
}



