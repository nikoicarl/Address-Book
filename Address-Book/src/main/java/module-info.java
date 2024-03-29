module org.example.addressbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.addressbook to javafx.fxml;
    exports org.example.addressbook;
}