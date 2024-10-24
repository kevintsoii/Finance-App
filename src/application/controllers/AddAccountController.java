package application.controllers;

import java.time.LocalDate;

import application.models.Account;
import application.models.AccountManager;
import application.util.FXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddAccountController {
    @FXML
    private TextField name;
    @FXML
    private DatePicker date;
    @FXML
    private TextField balance;
    @FXML
    private Text message;

    @FXML
    public void initialize() {
        date.setValue(LocalDate.now());
    }

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
        if (date.getValue() == null) {
            date.setValue(LocalDate.now());
        }
        try {
            double accountBalance = Double.parseDouble(balance.getText());
            if (accountBalance < 0) {
                message.setText("You must have an account balance.");
                message.setStyle("-fx-fill: red;");
                return;
            }
        } catch (NumberFormatException e) {
            message.setText("You must set a numeric account balance.");
            message.setStyle("-fx-fill: red;");
            return;
        }

        // Create the Account
        AccountManager accountManager = AccountManager.getInstance();
        boolean created = accountManager.addAccount(
            new Account(name.getText().strip(), date.getValue(), Double.parseDouble(balance.getText()))
        );
        
        if (!created) {
            message.setText("Name already exists.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        name.setText(null);
        date.setValue(LocalDate.now());
        balance.setText(null);
        message.setText("Account Created!");
        message.setStyle("-fx-fill: green;");
    }
}
