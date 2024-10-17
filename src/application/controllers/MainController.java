package application.controllers;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainController {
	@FXML HBox mainBox;
	
	@FXML
	public void initialize() {
		URL url = getClass().getResource("/views/Home.fxml");
		try {
			if (mainBox.getChildren().size() > 1)
				mainBox.getChildren().remove(1);
				
			AnchorPane panel = (AnchorPane) FXMLLoader.load(url);
			mainBox.getChildren().add(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void showAddAccount() {
		URL url = getClass().getResource("/views/AddAccount.fxml");
		try {
			if (mainBox.getChildren().size() > 1)
				mainBox.getChildren().remove(1);
				
			AnchorPane panel = (AnchorPane) FXMLLoader.load(url);
			mainBox.getChildren().add(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
