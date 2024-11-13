package application.controllers;

import java.time.LocalDate;

import application.models.Account;
import application.models.AccountManager;
import application.models.Transaction;
import application.models.TransactionManager;
import application.util.FXUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

        // Add accounts to transactionsTable
        ObservableList<Transaction> transactions = FXCollections.observableArrayList(
            TransactionManager.getInstance().getTransactions()
        );
        transactionsTable.setItems(transactions);
        transactionsTable.getSortOrder().add(date);

    }
}
