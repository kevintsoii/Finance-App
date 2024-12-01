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
import application.models.Transaction;
import application.models.TransactionManager;
import application.util.FXUtil;

public class ViewTransactionController {
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
    
    private Transaction transaction;

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
        
        // Initialize payment/deposit fields to 0
        payment.setText("0.0");
        deposit.setText("0.0");
    }

    @FXML
    public void showHome() {
        FXUtil.setPage("/views/Transactions.fxml");
    }
    
    public void setTransaction(Transaction transaction) {
        account.setValue(transaction.getAccount());
        type.setValue(transaction.getTransactionType());
        date.setValue(transaction.getDate());
        description.setText(transaction.getDescription());
        payment.setText(String.valueOf(transaction.getPayment()));
        deposit.setText(String.valueOf(transaction.getDeposit()));
        this.transaction = transaction;
    }

    @FXML
    public void submit() {
        message.setText("");
        message.setStyle("-fx-fill: black;");

        // Validate Fields
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
        
        if (date.getValue() == null) {
            date.setValue(LocalDate.now());
        }
        
        if (description.getText() == null || description.getText().trim().isEmpty()) {
            message.setText("You must enter a description.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        if (description.getText().contains(",")) {
            message.setText("Invalid character in description: ,");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        double paymentAmount = 0.0;
        double depositAmount = 0.0;
        try {
            paymentAmount = Double.parseDouble(payment.getText());
            if (paymentAmount < 0) {
                message.setText("Payment amount cannot be negative.");
                message.setStyle("-fx-fill: red;");
                return;
            }
        } catch (NumberFormatException e) {
            message.setText("Invalid payment amount.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        try {
            depositAmount = Double.parseDouble(deposit.getText());
            if (depositAmount < 0) {
                message.setText("Deposit amount cannot be negative.");
                message.setStyle("-fx-fill: red;");
                return;
            }
        } catch (NumberFormatException e) {
            message.setText("Invalid deposit amount.");
            message.setStyle("-fx-fill: red;");
            return;
        }
        
        if (paymentAmount == 0.0 && depositAmount == 0.0) {
            message.setText("Payment or deposit amount must be entered.");
            message.setStyle("-fx-fill: red;");
            return;
        }

        // Update the Transaction
        TransactionManager transactionManager = TransactionManager.getInstance();
        transaction.setAccount(account.getValue());
        transaction.setTransactionType(type.getValue());
        transaction.setDate(date.getValue());
        transaction.setDescription(description.getText().strip());
        transaction.setPayment(paymentAmount);
        transaction.setDeposit(depositAmount);
        transactionManager.save();
        
        message.setText("Transaction Updated!");
        message.setStyle("-fx-fill: green;");
    }
}