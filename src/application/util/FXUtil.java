package application.util;

import java.io.IOException;
import java.net.URL;

import application.CommonObjs;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
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

            AnchorPane panel = (AnchorPane) FXMLLoader.load(url);
            mainBox.getChildren().add(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
