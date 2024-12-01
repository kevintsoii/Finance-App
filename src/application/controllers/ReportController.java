package application.controllers;

import java.time.LocalDate;
import java.util.stream.Collectors;

import application.models.Account;
import application.models.AccountManager;
import application.models.AccountType;
import application.models.ScheduledTransaction;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<ScheduledTransaction> scheduledTransactionsTable;
    @FXML
    private TableColumn<ScheduledTransaction, String> sName;
    @FXML
    private TableColumn<ScheduledTransaction, String> sAccount;
    @FXML
    private TableColumn<ScheduledTransaction, String> sType;
    @FXML
    private TableColumn<ScheduledTransaction, String> sFrequency;
    @FXML
    private TableColumn<ScheduledTransaction, Integer> sDate;
    @FXML
    private TableColumn<ScheduledTransaction, Double> sAmount;

    @FXML
    private ComboBox<String> accountSelect;
    @FXML
    private ComboBox<String> typeSelect;
    
    FilteredList<Transaction> filteredTransactions = null;
    
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
        
        // Handle double click to edit
        transactionsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Transaction selectedTransaction = transactionsTable.getSelectionModel().getSelectedItem();
                if (selectedTransaction != null) {
                    ViewTransactionController controller = FXUtil.setCustomPage("/views/ViewTransaction.fxml");
                    if (controller != null) {
                        controller.setTransaction(selectedTransaction);
                    }
                }
            }
        });
    }
}
