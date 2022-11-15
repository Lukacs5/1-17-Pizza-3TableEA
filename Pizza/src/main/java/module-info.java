module com.example.pizza {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pizza to javafx.fxml;
    exports com.example.pizza;
}