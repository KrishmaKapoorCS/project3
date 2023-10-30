module com.example.bankgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires junit;

    opens com.example.bankgui to javafx.fxml;
    exports com.example.bankgui;
}