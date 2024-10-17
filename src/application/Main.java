package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// start by showing Main (sidebar)
			HBox mainBox = (HBox)FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
			Scene scene = new Scene(mainBox, 1200, 800);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			
			// Keep a reference to the main box
			CommonObjs commonObjs = CommonObjs.getInstance();
			commonObjs.setMainBox(mainBox);
			
			// Configure the window
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.setTitle("Budget Buddy");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
