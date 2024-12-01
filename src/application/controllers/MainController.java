package application.controllers;

import application.CommonObjs;
import application.util.FXUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class MainController {
    @FXML
    private HBox mainBox;

    @FXML
    public void initialize() {
        // Initialize global objects
        CommonObjs commonObjs = CommonObjs.getInstance();
        commonObjs.setMainBox(mainBox);

        FXUtil.setPage("/views/Home.fxml");
    }

    @FXML
    public void showAddAccount() {
        FXUtil.setPage("/views/AddAccount.fxml");
    }

    @FXML
    public void showCreateTransactionType() {
        FXUtil.setPage("/views/AddType.fxml");
    }

    @FXML
    public void showEnterTransaction() {
        FXUtil.setPage("/views/AddTransaction.fxml");
    }

    @FXML
    public void showScheduleTransaction() {
        FXUtil.setPage("/views/AddScheduled.fxml");
    }

    @FXML
    public void showTransactions() {
        FXUtil.setPage("/views/Transactions.fxml");
    }

    @FXML
    public void showScheduledTransactions() {
        FXUtil.setPage("/views/ScheduledTransactions.fxml");
    }

    @FXML
    public void showReports() {
        FXUtil.setPage("/views/Report.fxml");
    }
}
