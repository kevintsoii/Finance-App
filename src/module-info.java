module BudgetBuddy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;

    opens application to javafx.graphics, javafx.fxml;
    opens application.controllers to javafx.fxml;
    opens application.models to javafx.base;
}