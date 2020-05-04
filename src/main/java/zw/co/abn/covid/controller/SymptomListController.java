/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.net.URL;
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
import org.springframework.stereotype.Controller;
import zw.co.abn.covid.model.Patient;
import zw.co.abn.covid.model.Symptoms;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        loadData();
    }


    public void initializeTable(){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void loadData(){
        symptomsObservableList.addAll(new Symptoms("Fever"),new Symptoms("Headache"),new Symptoms("Dry Throat"));
        tableView.setItems(symptomsObservableList);
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
    private void addSymptom(ActionEvent event) {
    }

    @FXML
    private void closeStage(ActionEvent event) {
    }
    
}
