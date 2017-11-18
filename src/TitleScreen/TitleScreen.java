package TitleScreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * bad-wolf
 *
 * @author Kayso
 * date 11/17/2017
 * @version 1.0.0
 */
public class TitleScreen extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent menu = FXMLLoader.load(getClass().getResource("titleScreen.fxml"));
        primaryStage.setScene(new Scene(menu));
        primaryStage.show();
    }
}
