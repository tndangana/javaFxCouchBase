/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
@Controller
public class MainController implements Initializable {


    @FXML
    private Button btnPatients;
    @FXML
    private Button btn_users;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btn_country;
    @FXML
    private Button btn_symptoms;
    @FXML
    private Button dashBoard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        
        if(mouseEvent.getSource() == dashBoard){
            loadStage("/views/dashboard/dashboard.fxml");
        }
        else if (mouseEvent.getSource() == btnPatients) {
            loadStage("/views/patient/PatientList.fxml");
        } else if (mouseEvent.getSource() == btn_users) {
            loadStage("/views/user/userList.fxml");
        }
        else  if(mouseEvent.getSource() == btn_country){
            loadStage("/views/country/countryList.fxml");
        }
        else  if(mouseEvent.getSource() == btn_symptoms){
            loadStage("/views/symptoms/symptomsList.fxml");
        }
        else  if(mouseEvent.getSource() == btnSettings){

        }

    }


    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
