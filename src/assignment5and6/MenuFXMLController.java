/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5and6;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author bener
 */
public class MenuFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private void openUI(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("FXMLAssignment5and6.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("New Window");
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    @FXML
    private void aboutUs(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String titleTxt = "Abous Us";
        alert.setTitle(titleTxt);
        alert.setHeaderText("Welcome");
        String s = "This is Assignment 5 and 6 created by Jeciel Benerayan" + "\n"
                + "This program will gather data based on user input" + "\n" 
                + "User will input information about their vacation";
        alert.setContentText(s);

        alert.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
