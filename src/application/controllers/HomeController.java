package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.time.LocalDate;

import application.models.AccountManager;
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

    }
}