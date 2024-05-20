module org.example.pms {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.pms to javafx.fxml;
    exports org.example.pms;
}