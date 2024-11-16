package application.controllers;

import application.models.ScheduledTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class ScheduledTransactionsController {

    @FXML
    private TableView<ScheduledTransaction> scheduledTransactionsTable;
    @FXML
    private TableColumn<ScheduledTransaction, String> accountColumn;
    @FXML
    private TableColumn<ScheduledTransaction, String> typeColumn;
    @FXML
    private TableColumn<ScheduledTransaction, LocalDate> dueDateColumn;
    @FXML
    private TableColumn<ScheduledTransaction, String> descriptionColumn;
    @FXML
    private TableColumn<ScheduledTransaction, Double> paymentColumn;
    @FXML
    private TableColumn<ScheduledTransaction, Double> depositColumn;

    private ObservableList<ScheduledTransaction> scheduledTransactions = FXCollections.observableArrayList();

    public void initialize() {
        accountColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));
        depositColumn.setCellValueFactory(new PropertyValueFactory<>("deposit"));

        loadScheduledTransactions();
        scheduledTransactionsTable.setItems(scheduledTransactions);
    }

    private void loadScheduledTransactions() {
        // Load transactions (mock data or from file/DB)
        scheduledTransactions.add(new ScheduledTransaction("Chase", "Credit Card", LocalDate.of(2024, 12, 5), "Monthly Payment", 200.0, 0.0));
        scheduledTransactions.add(new ScheduledTransaction("Wells Fargo", "Mortgage", LocalDate.of(2024, 12, 1), "House Payment", 0.0, 1500.0));
        // Sort by due date
        scheduledTransactions.sort((a, b) -> a.getDueDate().compareTo(b.getDueDate()));
    }
}
