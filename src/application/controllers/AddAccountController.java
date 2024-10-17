package application.controllers;

import java.io.IOException;
import java.net.URL;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class AddAccountController {
	CommonObjs commonObjs = CommonObjs.getInstance();
	
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
}
