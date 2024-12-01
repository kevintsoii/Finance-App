package application.util;

import java.io.IOException;
import java.net.URL;

import application.CommonObjs;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

public class FXUtil {
    static CommonObjs commonObjs = CommonObjs.getInstance();

    public static void setPage(String path) {
        // Set page based on FXML file path
        URL url = FXUtil.class.getResource(path);
        try {
            HBox mainBox = commonObjs.getMainBox();
            if (mainBox.getChildren().size() > 1)
                mainBox.getChildren().remove(1);

            Parent panel = FXMLLoader.load(url);
            mainBox.getChildren().add(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static <T> T setCustomPage(String path) {
        // Set page based on FXML file path
        URL url = FXUtil.class.getResource(path);
        try {
            HBox mainBox = commonObjs.getMainBox();
            if (mainBox.getChildren().size() > 1)
                mainBox.getChildren().remove(1);

            // return the loader to pass account later
            FXMLLoader loader = new FXMLLoader(url);
            Parent panel = loader.load();
            mainBox.getChildren().add(panel);
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
