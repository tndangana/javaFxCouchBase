/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Controller;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.Country;
import zw.co.abn.covid.model.Patient;
import zw.co.abn.covid.model.User;
import zw.co.abn.covid.service.impl.GenericService;
import zw.co.abn.covid.util.AssistantUtil;
import zw.co.abn.covid.util.Constant;

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

    private GenericService genericService = new GenericService();

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
        patientList.clear();
        genericService.findAll(Constant.PATIENT).stream().forEach(o -> {
            Map<String, Object> p = (Map<String, Object>) o;
            if(!p.isEmpty()){
                Patient patient = new Patient();
                patient.setId(p.get("_id").toString());
                patient.setGender(p.get("gender")!=null?p.get("gender").toString():"");
                patient.setLastName(p.get("lastName")!=null?p.get("lastName").toString():"");
                patient.setFirstName(p.get("firstName")!=null?p.get("firstName").toString():"");
                patientList.add(patient);
            }
        });
        tableView.setItems(patientList);
    }


    @FXML
    private void handleRefresh(ActionEvent event) {
         loadPatientData();
    }

    @FXML
    private void handleBookEditOption(ActionEvent event) {
        Patient patient = tableView.getSelectionModel().getSelectedItem();

        boolean exist = genericService.recordExist(Constant.PATIENT,patient.getId());
        if(exist ==false){
            AlertInformation.showErrorMessage("No patient selected","Patient with give id does not exist");
            return;
        }
        Map<String, Object> properties = genericService.findById(Constant.PATIENT,patient.getId());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/patient/patient.fxml"));
            Parent parent = loader.load();
            PatientController patientController= loader.getController();
            patientController.infalteUI(properties);
            System.out.println();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Patient");
            stage.setScene(new Scene(parent));
            stage.show();
            AssistantUtil.setStageIcon(stage);
            System.out.println();
            stage.setOnHiding((e) -> {
                handleRefresh(new ActionEvent());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBookDeleteOption(ActionEvent event) {
        String id = tableView.getSelectionModel().getSelectedItem().getId();
        if(id.isEmpty()){
            AlertInformation.showErrorMessage("Empty","Id not available");
            return;
        }
        boolean isDeleted = genericService.deleteById(Constant.PATIENT,id);
        if(isDeleted==true){
            AlertInformation.showSuccesMessage("Success","Patient has been deleted succesfully");
            loadPatientData();
            return;
        }else {
            AlertInformation.showErrorMessage("Error","Patient has not been deleted try again");
        }
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
