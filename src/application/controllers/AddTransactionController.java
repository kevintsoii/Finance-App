package application.controllers;

import java.time.LocalDate;
import java.util.stream.Collectors;

import application.models.Account;
import application.models.AccountManager;
import application.models.AccountType;
import application.models.TypeManager;
import application.util.FXUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddTransactionController {
    @FXML
    private ComboBox<String> account;
    @FXML
    private ComboBox<String> type;
    @FXML
    private DatePicker date;
    @FXML
    private TextField description;
    @FXML
    private TextField payment;
    @FXML
    private TextField deposit;
    @FXML
    private Text message;

    @FXML
    public void initialize() {
        date.setValue(LocalDate.now());
        
        ObservableList<String> accountChoices = FXCollections.observableArrayList(
            AccountManager.getInstance().getAccounts().stream().map(Account::getName).collect(Collectors.toList())
        );
        account.setItems(accountChoices);
        if (!accountChoices.isEmpty()) {
            account.getSelectionModel().selectFirst();
        }
        
        ObservableList<String> typeChoices = FXCollections.observableArrayList(
            TypeManager.getInstance().getTypes().stream().map(AccountType::getName).collect(Collectors.toList())
        );
        type.setItems(typeChoices);
        if (!typeChoices.isEmpty()) {
            type.getSelectionModel().selectFirst();
        }
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
