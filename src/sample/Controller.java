/*
 *  Name: Betty Kwong
 *  ID:   100587230
 *  Due:  March 7, 2017
 *
 *  Main.java
 */

package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import javax.swing.table.TableColumn;
import java.awt.*;

public class Controller {

    //variables
    @FXML
    private static TextField hamID, spamID;
    @FXML
    private TableColumn fileID, classID, probID;

    //when the submite button is clicked
    public void goResult(ActionEvent event) {
        //sample.WordDetector.main();
        //Stage display = new Stage();
        //display.getClass().getResource("Result.fxml");
        //display.setTitle("Result");
        //display.show();
    }

    //getHam and getSpam
    public static String getHam(){
        return hamID.getText();
    }

    public static String getSpam(){
        return spamID.getText();
    }


}