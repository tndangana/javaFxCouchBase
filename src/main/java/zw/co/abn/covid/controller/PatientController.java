/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.Gender;
import zw.co.abn.covid.model.Patient;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
@Controller
public class PatientController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXDatePicker dateOfBirth;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXComboBox<Gender> gender;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addPatient(ActionEvent event) {

        Optional<Patient> optionalPatient = Optional.of(new Patient(firstName.getText(),lastName.getText(),dateOfBirth.getValue(),gender.getValue()));
        if(!optionalPatient.isPresent()){
            AlertInformation.showMaterialDialog(rootPane,mainContainer,new ArrayList<>(),"Empty field","Please enter the empty field !!");
            return;
        }

        // duplicate check

        //save

    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
