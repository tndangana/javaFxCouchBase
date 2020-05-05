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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.Gender;
import zw.co.abn.covid.model.Patient;
import zw.co.abn.covid.service.PatientService;

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
    private JFXComboBox<String> gender;
    @Autowired
    private PatientService patientService;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> list = FXCollections.observableArrayList("FEMALE","MALE");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gender.setItems(list);
    }

    @FXML
    private void addPatient(ActionEvent event) {

        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || dateOfBirth.getValue().equals(null) || gender.getValue().isEmpty()){
            AlertInformation.showMaterialDialog(rootPane,mainContainer,new ArrayList<>(),"Empty field","Please enter the empty field !!");

        }
        Patient patient = new Patient();
        patient.setFirstName(firstName.getText());
        patient.setLastName(lastName.getText());
        patient.setDateOfBirth(dateOfBirth.getValue());
        patient.setGender(gender.getValue());

        System.out.println("patient"+patient.getFirstName() + patient.getLastName() +patient.getGender()+ patient.getDateOfBirth());

        // duplicate check

        //save
        patientService.save(patient);


    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
