package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import javax.swing.table.TableColumn;
import java.awt.*;

public class Controller {

    @FXML
    private static TextField hamID, spamID;
    @FXML
    private TableColumn fileID, classID, probID;


    public void goResult(ActionEvent event) {
        //sample.WordDetector.main();
        //Stage display = new Stage();
        //display.getClass().getResource("Result.fxml");
        //display.setTitle("Result");
        //display.show();
    }

    public static String getHam(){
        return hamID.getText();
    }

    public static String getSpam(){
        return spamID.getText();
    }


}