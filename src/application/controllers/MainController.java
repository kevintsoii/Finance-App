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
    }

    @FXML
    public void showEnterTransaction() {
    }

    @FXML
    public void showScheduleTransaction() {
    }

    @FXML
    public void showTransactions() {
    }

    @FXML
    public void showScheduledTransactions() {
    }

    @FXML
    public void showReports() {
    }
}
