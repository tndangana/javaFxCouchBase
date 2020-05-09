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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.Patient;
import zw.co.abn.covid.service.impl.GenericService;
import zw.co.abn.covid.util.Constant;

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
    private Map<String,Object> map;
    private Boolean isInEditMode = false;


    private GenericService genericService = new GenericService();

    /**
     * Initializes the controller class.
     */
    ObservableList<String> list = FXCollections.observableArrayList("FEMALE","MALE");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gender.setItems(list);
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void addPatient(ActionEvent event) {

        if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || dateOfBirth.getValue().equals("") || gender.getValue().isEmpty()){
            AlertInformation.showErrorMessage("Empty","Fields are empty");
        }

        if(isInEditMode==true){


        }
        Patient patient = new Patient();
        patient.setFirstName(firstName.getText());
        patient.setLastName(lastName.getText());
        patient.setDateOfBirth(dateOfBirth.getValue());
        patient.setGender(gender.getValue());
        Map<String,Object> patientMap= new HashMap<>();
        patientMap.put("firstName",patient.getFirstName());
        patientMap.put("lastName",patient.getLastName());
        patientMap.put("gender",patient.getGender());
        patientMap.put("dateOfBirth",patient.getDateOfBirth());
        boolean saved = genericService.save(patientMap, Constant.PATIENT);
        if(saved == true){
            AlertInformation.showSuccesMessage("Saved","You have saved your country successfully");
            getStage().close();
            return;
        }

    }

    public void infalteUI(Map<String,Object> patient) {
        map = patient;
        firstName.setText(String.valueOf(patient.get("firstName")));
        lastName.setText(String.valueOf(patient.get("lastName")));
//        dateOfBirth.setValue((LocalDate) patient.get("dateOfBirth"));
        gender.setValue((String) patient.get("gender"));
        isInEditMode = Boolean.TRUE;
    }


    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
