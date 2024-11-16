package application.controllers;

import application.models.ScheduledTransaction;
import application.models.ScheduledTransactionManager;
import application.util.FXUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScheduledTransactionsController {

    @FXML
    private TableView<ScheduledTransaction> scheduledTransactionsTable;
    @FXML
    private TableColumn<ScheduledTransaction, String> name;
    @FXML
    private TableColumn<ScheduledTransaction, String> account;
    @FXML
    private TableColumn<ScheduledTransaction, String> type;
    @FXML
    private TableColumn<ScheduledTransaction, String> frequency;
    @FXML
    private TableColumn<ScheduledTransaction, Integer> date;
    @FXML
    private TableColumn<ScheduledTransaction, Double> amount;

    @FXML
    public void showHome() {
        FXUtil.setPage("/views/Home.fxml");
    }
    
    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        frequency.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        // Configure account opening date sort order
        date.setSortType(TableColumn.SortType.ASCENDING);

        // Add accounts to transactionsTable
        ObservableList<ScheduledTransaction> transactions = FXCollections.observableArrayList(
            ScheduledTransactionManager.getInstance().getTransactions()
        );
        scheduledTransactionsTable.setItems(transactions);
        scheduledTransactionsTable.getSortOrder().add(date);
    }
}
