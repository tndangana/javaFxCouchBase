/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.Country;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
public class CountryListController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private TableView<Country> tableView;
    @FXML
    private TableColumn<Country, String> name;
    @FXML
    private TableColumn<?, ?> action;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleRefresh(ActionEvent event) {
    }

    @FXML
    private void handleBookEditOption(ActionEvent event) {

        Optional<Country> selectForEdit = Optional.ofNullable(tableView.getSelectionModel().getSelectedItem());
        if(!selectForEdit.isPresent()){
            AlertInformation.showErrorMessage("No country selected","Please select a country for edit");
            return;
        }

        try {
            FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource(""));
            Parent parent = fxmlLoader.load();


        }catch (IOException e){

        }

    }



    @FXML
    private void handleBookDeleteOption(ActionEvent event) {
    }

    @FXML
    private void addCountry(ActionEvent event) {
    }

    @FXML
    private void closeStage(ActionEvent event) {
    }
    
}
