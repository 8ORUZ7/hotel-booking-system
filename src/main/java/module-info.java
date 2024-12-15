module osiristape.hotelbooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;
    requires java.base;

    // Add the JasperReports module
    requires jasperreports;  // This is the dependency that includes JasperReport classes

    opens osiristape.hotelbooking to javafx.fxml;
    exports osiristape.hotelbooking;
}
