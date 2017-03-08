/*
 *  Name: Betty Kwong
 *  ID:   100587230
 *  Due:  March 7, 2017
 *
 *  Main.java
 */

package sample;

//imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Directory.fxml"));
        primaryStage.setTitle("Directory");
        primaryStage.setScene(new Scene(root, 490, 110));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
