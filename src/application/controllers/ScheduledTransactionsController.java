package application.controllers;

import application.models.ScheduledTransaction;
import application.models.ScheduledTransactionManager;
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
    private TextField searchTerm;
    
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

        // Add transactions to an array list
        ObservableList<ScheduledTransaction> transactions = FXCollections.observableArrayList(
            ScheduledTransactionManager.getInstance().getTransactions()
        );
        FilteredList<ScheduledTransaction> filteredTransactions = new FilteredList<>(transactions, t -> true);
        
        // Listen for search term changes
        searchTerm.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredTransactions.setPredicate(transaction -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String query = newValue.toLowerCase();
                return (transaction.getName() != null && transaction.getName().toLowerCase().contains(query));
            });
        });
        
        // Add transactions to table
        SortedList<ScheduledTransaction> sortedTransactions = new SortedList<>(filteredTransactions);
        sortedTransactions.comparatorProperty().bind(scheduledTransactionsTable.comparatorProperty());
        scheduledTransactionsTable.setItems(sortedTransactions);
        scheduledTransactionsTable.getSortOrder().add(date);
        
        // Handle double click to edit
        scheduledTransactionsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                ScheduledTransaction selectedTransaction = scheduledTransactionsTable.getSelectionModel().getSelectedItem();
                if (selectedTransaction != null) {
                    EditScheduledController controller = FXUtil.setCustomPage("/views/EditScheduled.fxml");
                    if (controller != null) {
                        controller.setTransaction(selectedTransaction);
                    }
                }
            }
        });
    }
}
