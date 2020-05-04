/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.net.URL;
import java.time.LocalDate;
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
import org.springframework.stereotype.Controller;
import zw.co.abn.covid.model.Gender;
import zw.co.abn.covid.model.Patient;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
@Controller
public class PatientListController implements Initializable {

    ObservableList<Patient> patientList = FXCollections.observableArrayList();
    private Gender sex;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initializeTable();
        loadPatientData();

    }

    public void initializeTable() {
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    public void loadPatientData() {

        patientList.addAll(
                new Patient("tonderai", "Ndangana", LocalDate.now(), sex.FEMALE),
                new Patient("Gerald", "Matsika", LocalDate.now(), sex.MALE));
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
    }

    @FXML
    private void closeStage(ActionEvent event) {
    }

}
