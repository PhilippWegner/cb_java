module de.hsbo {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.hsbo.gui to javafx.fxml;
    exports de.hsbo.gui;
}
