/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.net.URL;
import java.util.Map;
import java.util.Observable;
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
import org.springframework.stereotype.Controller;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.Patient;
import zw.co.abn.covid.model.Symptoms;
import zw.co.abn.covid.service.impl.GenericService;
import zw.co.abn.covid.util.AssistantUtil;
import zw.co.abn.covid.util.Constant;

/**
 * FXML Controller class
 *
 * @author bignerd
 */

@Controller
public class SymptomListController implements Initializable {

     ObservableList<Symptoms> symptomsObservableList = FXCollections.observableArrayList();

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private TableView<Symptoms> tableView;
    @FXML
    private TableColumn<Symptoms, String> name;
    @FXML
    private TableColumn<?, ?> action;
    private GenericService genericService = new GenericService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        loadData();
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }



    public void initializeTable(){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void loadData(){
        symptomsObservableList.clear();
        genericService.findAll(Constant.SYMPTOMSVIEW).stream().forEach(o -> {
            Map<String,Object> map = (Map<String, Object>)o;
            Symptoms symptoms = new Symptoms();
            symptoms.setId(map.get("_id").toString());
            symptoms.setName(map.get("name")!=null?map.get("name").toString():"");
            symptomsObservableList.add(symptoms);
        });

        tableView.setItems(symptomsObservableList);
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

    @FXML
    private void handleBookEditOption(ActionEvent event) {
    }

    @FXML
    private void handleBookDeleteOption(ActionEvent event) {

        String id = tableView.getSelectionModel().getSelectedItem().getId();
        if(id.isEmpty()){
            AlertInformation.showErrorMessage("Empty","Id not available");
            return;
        }
        boolean isDeleted = genericService.deleteById(Constant.SYMPTOMSVIEW,id);
        if(isDeleted==true){
            AlertInformation.showSuccesMessage("Success","Symptom has been deleted successfully");
            loadData();
            return;
        }else {
            AlertInformation.showErrorMessage("Error","Symptom has not been deleted try again");
        }
    }

    @FXML
    private void addSymptom(ActionEvent event) {
        AssistantUtil.loadWindow(getClass().getResource("/views/symptoms/symptoms.fxml"), "Add New Symptom", null);
    }

    @FXML
    private void closeStage(ActionEvent event) {

        getStage().close();
    }
    
}
