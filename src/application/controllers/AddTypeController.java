package application.controllers;

import java.time.LocalDate;

import application.models.AccountType;
import application.models.TypeManager;
import application.util.FXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddTypeController {
    @FXML
    private TextField name;
    @FXML
    private Text message;

    @FXML
    public void showHome() {
        FXUtil.setPage("/views/Home.fxml");
    }

    @FXML
    public void submit() {
        message.setText("");
        message.setStyle("-fx-fill: black;");

        // Validate Fields
        if (name.getText() == null || name.getText().trim().isEmpty()) {
            message.setText("You must enter a name.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        if (name.getText().contains(",")) {
            message.setText("Invalid character in name: ,");
            message.setStyle("-fx-fill: red;");
            return;
        }

        // Create the Type
        TypeManager typeManager = TypeManager.getInstance();
        boolean created = typeManager.addType(
            new AccountType(name.getText().strip())
        );
        
        if (!created) {
            message.setText("Name already exists.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        name.setText(null);
        message.setText("Transaction Type Created!");
        message.setStyle("-fx-fill: green;");
    }
}
