module com.example.databaseproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.databaseproject to javafx.fxml;
    exports com.example.databaseproject;
}