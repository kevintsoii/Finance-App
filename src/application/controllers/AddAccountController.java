package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AddAccountController {
	CommonObjs commonObjs = CommonObjs.getInstance();
	
	@FXML TextField name;
	@FXML DatePicker date;
	@FXML TextField balance;
	@FXML Text message;
	
	 @FXML
	 public void initialize() {
		 date.setValue(LocalDate.now());
	}
	
	@FXML
	public void showHome() {
		URL url = getClass().getResource("/views/Home.fxml");
		try {
			HBox mainBox = commonObjs.getMainBox();
			if (mainBox.getChildren().size() > 1)
				mainBox.getChildren().remove(1);
				
			AnchorPane panel = (AnchorPane) FXMLLoader.load(url);
			mainBox.getChildren().add(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}
	
	@FXML
	public void submit() {
		message.setText("");
		
		// Validate Fields
		if (name.getText() == null || name.getText().trim().isEmpty()) {
			message.setText("You must enter a name.");
			return;
		}
		if (date.getValue() == null) {
			date.setValue(LocalDate.now());
		}
		try {
			double accountBalance = Double.parseDouble(balance.getText());
			if (accountBalance < 0) {
				message.setText("You must have an account balance.");
				return;
			}
		} catch (NumberFormatException e) {
			message.setText("You must set a numeric account balance.");
			return;
		}
		
		// Create the Account
		name.setText(null);
		date.setValue(null);
		balance.setText(null);
		message.setText("Account Created! (not saved)");
	}
}
