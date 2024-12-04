package application.controllers;

import java.time.LocalDate;
import java.util.stream.Collectors;

import application.models.Account;
import application.models.AccountManager;
import application.models.AccountType;
import application.models.Transaction;
import application.models.TransactionManager;
import application.models.TypeManager;
import application.util.FXUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ReportController {
    @FXML
    private TableView<Transaction> transactionsTable;
    @FXML
    private TableColumn<Transaction, String> tAccount;
    @FXML
    private TableColumn<Transaction, String> tType;
    @FXML
    private TableColumn<Transaction, LocalDate> tDate;
    @FXML
    private TableColumn<Transaction, String> tDescription;
    @FXML
    private TableColumn<Transaction, Double> tPayment;
    @FXML
    private TableColumn<Transaction, Double> tDeposit;
    
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
    
    private Transaction transaction;
    
    @FXML
    private AnchorPane transactionPane;
    
    @FXML
    private AnchorPane reportPane;

    @FXML
    private ComboBox<String> accountSelect;
    @FXML
    private ComboBox<String> typeSelect;
    
    private FilteredList<Transaction> filteredTransactions = null;
    
    @FXML public void onClose() {
        transactionPane.setVisible(false);
        reportPane.setVisible(true);
    }
    
    @FXML
    public void showHome() {
        FXUtil.setPage("/views/Home.fxml");
    }
    
    @FXML
    public void onAccountChosen() {
        if (accountSelect.getValue() == null) {
            return;
        }
        
        filteredTransactions.setPredicate(transaction -> {
            return (transaction.getDescription() != null && transaction.getAccount().equals(accountSelect.getValue()));
        });

        typeSelect.setValue(null);
        tType.setVisible(true);
        tAccount.setVisible(false);
        tDate.setSortType(TableColumn.SortType.DESCENDING);
        transactionsTable.getSortOrder().clear();
        transactionsTable.getSortOrder().add(tDate);
    }
    
    @FXML
    public void onTypeChosen() {
        if (typeSelect.getValue() == null) {
            return;
        }

        filteredTransactions.setPredicate(transaction -> {
            return (transaction.getDescription() != null && transaction.getTransactionType().equals(typeSelect.getValue()));
        });

        accountSelect.setValue(null);
        tAccount.setVisible(true);
        tType.setVisible(false);
        tDate.setSortType(TableColumn.SortType.DESCENDING);
        transactionsTable.getSortOrder().clear();
        transactionsTable.getSortOrder().add(tDate);
    }

    @FXML
    public void initialize() {
        transactionPane.setVisible(false);
        reportPane.setVisible(true);
        
        // make transaction viewing fields uneditable
        account.setMouseTransparent(true);
        type.setMouseTransparent(true);
        date.setMouseTransparent(true);
        description.setMouseTransparent(true);
        payment.setMouseTransparent(true);
        deposit.setMouseTransparent(true);
        
        // set up select dropdowns
        ObservableList<String> accountChoices = FXCollections.observableArrayList(
            AccountManager.getInstance().getAccounts().stream().map(Account::getName).collect(Collectors.toList())
        );
        accountSelect.setItems(accountChoices);
        
        ObservableList<String> typeChoices = FXCollections.observableArrayList(
            TypeManager.getInstance().getTypes().stream().map(AccountType::getName).collect(Collectors.toList())
        );
        typeSelect.setItems(typeChoices);
            
        // Set up columns of transactionsTable
        tAccount.setCellValueFactory(new PropertyValueFactory<>("account"));
        tType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        tDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        tDeposit.setCellValueFactory(new PropertyValueFactory<>("deposit"));

        // Configure account opening date sort order
        tDate.setSortType(TableColumn.SortType.DESCENDING);

        // Add transactions to an array list
        ObservableList<Transaction> transactions = FXCollections.observableArrayList(
            TransactionManager.getInstance().getTransactions()
        );
        filteredTransactions = new FilteredList<>(transactions, t -> false);
        
        // Add transactions to table
        SortedList<Transaction> sortedTransactions = new SortedList<>(filteredTransactions);
        sortedTransactions.comparatorProperty().bind(transactionsTable.comparatorProperty());
        transactionsTable.setItems(sortedTransactions);
        transactionsTable.getSortOrder().add(tDate);
        
        // Handle click to view
        transactionsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Transaction selectedTransaction = transactionsTable.getSelectionModel().getSelectedItem();
                if (selectedTransaction != null) {
                    transaction = selectedTransaction;
                    
                    account.setValue(transaction.getAccount());
                    type.setValue(transaction.getTransactionType());
                    date.setValue(transaction.getDate());
                    description.setText(transaction.getDescription());
                    payment.setText(String.valueOf(transaction.getPayment()));
                    deposit.setText(String.valueOf(transaction.getDeposit()));
                    
                    transactionPane.setVisible(true);
                    reportPane.setVisible(false);
                }
            }
        });
    }
}
