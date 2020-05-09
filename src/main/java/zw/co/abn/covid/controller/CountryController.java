/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.Country;
import zw.co.abn.covid.model.Gender;
import zw.co.abn.covid.service.impl.GenericService;
import zw.co.abn.covid.util.Constant;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
@Controller
//controller
public class CountryController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField name;
    private Boolean isInEditMode = false;
    private GenericService genericService = new GenericService();
    private Map<String,Object> map = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void addCountry(ActionEvent event) {
        //check to see if its empty
           if(name.getText().isEmpty()){
                  AlertInformation.showErrorMessage("Empty","Please complete the available form");
               return;
           }

           if(isInEditMode==true){
               map.put("name",name.getText());
               genericService.update(map,map.get("_id").toString());
           }
          Map<String,Object> map = new HashMap<>();
          map.put("name",name.getText());
         boolean saved = genericService.save(map,Constant.COUNTRYVIEW);
         if(saved == true){
             AlertInformation.showSuccesMessage("Saved","Country has been saved");
             getStage().close();
             return;
         }
    }

    public void infalteUI(Map<String,Object> country) {
         map = country;
         name.setText(String.valueOf(country.get("name")));
         isInEditMode = Boolean.TRUE;
    }




    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
