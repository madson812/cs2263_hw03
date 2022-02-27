module cs2263.hw03.app.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.google.gson;

    opens cs2263_hw03 to javafx.fxml;
    exports cs2263_hw03;
}