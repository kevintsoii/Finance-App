package application.controllers;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.stream.Collectors;

import application.models.Account;
import application.models.AccountManager;
import application.models.TypeManager;
import application.models.AccountType;
import application.models.ScheduledTransaction;
import application.models.ScheduledTransactionManager;
import application.models.Transaction;
import application.models.TransactionManager;
import application.util.FXUtil;

public class AddScheduledController {
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> account;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<String> frequency;
    @FXML
    private TextField date;
    @FXML
    private TextField amount;
    @FXML
    private Text message;

    @FXML
    public void initialize() {        
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
        
        ObservableList<String> frequencyChoices = FXCollections.observableArrayList("Monthly");
        frequency.setItems(frequencyChoices);
        if (!frequencyChoices.isEmpty()) {
            frequency.getSelectionModel().selectFirst();
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
            message.setText("You must select a name.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        if (name.getText().contains(",")) {
            message.setText("Invalid character in name: ,");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        if (account.getValue() == null) {
            message.setText("You must select an account.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        if (type.getValue() == null) {
            message.setText("You must select a transaction type.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        if (frequency.getValue() == null) {
            message.setText("You must select a frequency.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        int dateValue = 0;
        double amountValue = 0.0;
        
        try {
            dateValue = Integer.parseInt(date.getText());
            if (dateValue < 0) {
                message.setText("Date cannot be negative.");
                message.setStyle("-fx-fill: red;");
                return;
            }
        } catch (NumberFormatException e) {
            message.setText("Invalid date.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        try {
            amountValue = Double.parseDouble(amount.getText());
            if (amountValue < 0) {
                message.setText("Amount cannot be negative.");
                message.setStyle("-fx-fill: red;");
                return;
            }
        } catch (NumberFormatException e) {
            message.setText("Invalid amount.");
            message.setStyle("-fx-fill: red;");
            return;
        }

        // Create the Transaction
        ScheduledTransactionManager transactionManager = ScheduledTransactionManager.getInstance();
        boolean created = transactionManager.addTransaction(
            new ScheduledTransaction(name.getText(), account.getValue(), type.getValue(), frequency.getValue(), dateValue, amountValue)
        );
        
        if (!created) {
            message.setText("Duplicate transaction.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        // Reset form
        if (!account.getItems().isEmpty()) {
            account.getSelectionModel().selectFirst();
        }
        if (!type.getItems().isEmpty()) {
            type.getSelectionModel().selectFirst();
        }
        if (!frequency.getItems().isEmpty()) {
            frequency.getSelectionModel().selectFirst();
        }
        name.setText("");
        date.setText("0");
        amount.setText("0.0");
        message.setText("Transaction Created!");
        message.setStyle("-fx-fill: green;");
    }
}