package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.time.LocalDate;
import java.util.ArrayList;

import application.models.AccountManager;
import application.models.ScheduledTransaction;
import application.models.ScheduledTransactionManager;
import application.models.Account;

public class HomeController {
    @FXML
    private TableView<Account> accountTable;

    @FXML
    private TableColumn<Account, String> name;
    
    @FXML
    private TableColumn<Account, LocalDate> date;
    
    @FXML
    private TableColumn<Account, Double> balance;
    
    @FXML
    private AnchorPane notification;

    @FXML
    private Text notificationItems;
    
    @FXML
    public void closeNotification() {
        notification.setVisible(false);
    }

    @FXML
    public void initialize() {
        // Set up columns of accountTable
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));

        // Configure account opening date sort order
        date.setSortType(TableColumn.SortType.DESCENDING);

        // Add accounts to accountTable
        ObservableList<Account> accounts = FXCollections.observableArrayList(
            AccountManager.getInstance().getAccounts()
        );
        accountTable.setItems(accounts);
        accountTable.getSortOrder().add(date);

        // Notify if scheduled transaction due
        ArrayList<ScheduledTransaction> transactions = ScheduledTransactionManager.getInstance().getTransactions();
        ArrayList<ScheduledTransaction> transactionsToday = new ArrayList<>();

        int today = LocalDate.now().getDayOfMonth();
        for (ScheduledTransaction transaction : transactions) {
            if (transaction.getDate() == today) {
                transactionsToday.add(transaction);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (ScheduledTransaction transaction : transactionsToday) {
            sb.append(" - ").append(transaction.getName()).append("\n");
        }
        if (sb.length() > 0) {
            notificationItems.setText(sb.toString());
            notification.setVisible(true);
        }
    }
}