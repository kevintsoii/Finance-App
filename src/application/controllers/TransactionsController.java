package application.controllers;

import java.time.LocalDate;

import application.models.Account;
import application.models.AccountManager;
import application.models.Transaction;
import application.models.TransactionManager;
import application.util.FXUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionsController {
    @FXML
    private TableView<Transaction> transactionsTable;

    @FXML
    private TableColumn<Transaction, String> account;

    @FXML
    private TableColumn<Transaction, String> type;

    @FXML
    private TableColumn<Transaction, LocalDate> date;

    @FXML
    private TableColumn<Transaction, String> description;

    @FXML
    private TableColumn<Transaction, Double> payment;

    @FXML
    private TableColumn<Transaction, Double> deposit;
    
    @FXML
    private TextField searchTerm;

    @FXML
    public void showHome() {
        FXUtil.setPage("/views/Home.fxml");
    }

    @FXML
    public void initialize() {
        // Set up columns of transactionsTable
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        type.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        payment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        deposit.setCellValueFactory(new PropertyValueFactory<>("deposit"));

        // Configure account opening date sort order
        date.setSortType(TableColumn.SortType.DESCENDING);

        // Add transactions to an array list
        ObservableList<Transaction> transactions = FXCollections.observableArrayList(
            TransactionManager.getInstance().getTransactions()
        );
        FilteredList<Transaction> filteredTransactions = new FilteredList<>(transactions, t -> true);
        
        // Listen for search term changes
        searchTerm.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredTransactions.setPredicate(transaction -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String query = newValue.toLowerCase();
                return (transaction.getDescription() != null && transaction.getDescription().toLowerCase().contains(query));
            });
        });
        
        // Add transactions to table
        SortedList<Transaction> sortedTransactions = new SortedList<>(filteredTransactions);
        sortedTransactions.comparatorProperty().bind(transactionsTable.comparatorProperty());
        transactionsTable.setItems(sortedTransactions);
        transactionsTable.getSortOrder().add(date);
    }
}
