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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.Symptoms;
import zw.co.abn.covid.service.impl.GenericService;
import zw.co.abn.covid.util.Constant;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
@Controller
public class SymptomAddController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    private GenericService genericService = new GenericService();

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
    private void addSymptom(ActionEvent event) {
        Map<String,Object> objectMap = new HashMap<>();
        if(name.getText().trim().isEmpty()){
            AlertInformation.showErrorMessage("Empty","Please enter empty field !!");
        }
        objectMap.put("name",name.getText());
        boolean saved = genericService.save(objectMap,Constant.SYMPTOMSVIEW);
        if(saved == true){
            AlertInformation.showSuccesMessage("Yay","Symptom has been saved");
            getStage().close();
            return;
        }

    }

    @FXML
    private void cancel(ActionEvent event) {
        getStage().close();
    }
    
}
