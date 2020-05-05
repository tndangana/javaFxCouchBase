/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import zw.co.abn.covid.model.Gender;
import zw.co.abn.covid.model.Patient;
import zw.co.abn.covid.service.PatientService;
import zw.co.abn.covid.util.AssistantUtil;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
@Controller
public class PatientListController implements Initializable {

    ObservableList<Patient> patientList = FXCollections.observableArrayList();
    private String sex;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private TableView<Patient> tableView;
    @FXML
    private TableColumn<Patient, String> firstName;
    @FXML
    private TableColumn<Patient, String> lastName;
    @FXML
    private TableColumn<Patient, String> gender;
    @FXML
    private TableColumn<Patient, LocalDate> dateOfBirth;
    @FXML
    private TableColumn<?, ?> action;
    @Autowired
    private PatientService patientService;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initializeTable();
        loadPatientData();

    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }


    public void initializeTable() {
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    public void loadPatientData() {

//        patientService.findAll().get().stream().forEach(patient -> {
//            System.out.println("hhghghghgh"+patient);
//                    patientList.add(patient);
//        });

        patientList.addAll(
                new Patient("tonderai", "Ndangana", LocalDate.now(), "FEMALE"),
                new Patient("Gerald", "Matsika", LocalDate.now(), "MALE"));

////        Optional<List<Patient>> patientList = patientService.findAll();
        tableView.setItems(patientList);
    }


    @FXML
    private void handleRefresh(ActionEvent event) {

    }

    @FXML
    private void handleBookEditOption(ActionEvent event) {
    }

    @FXML
    private void handleBookDeleteOption(ActionEvent event) {
    }

    @FXML
    private void addPatient(ActionEvent event) {
        AssistantUtil.loadWindow(getClass().getResource("/views/patient/patient.fxml"), "Add New Patient", null);

    }

    @FXML
    private void closeStage(ActionEvent event) {

        getStage().close();
    }

}
