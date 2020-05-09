/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
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
import lombok.Data;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.Country;
import zw.co.abn.covid.service.impl.GenericService;
import zw.co.abn.covid.util.AssistantUtil;
import zw.co.abn.covid.util.Constant;

import javax.swing.text.Document;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
public class CountryListController implements Initializable {




    private ObservableList<Country> list = FXCollections.observableArrayList();
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
    private GenericService genericService = new GenericService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTable();
        loadCountryList();
    }


    public void initializeTable(){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void loadCountryList(){
        list.clear();
         genericService.findAll(Constant.COUNTRYVIEW).stream().forEach(o -> {
             Map<String,Object> map = ( Map<String,Object>) o;
             Country country = new Country();
             country.setId(map.get("_id").toString());
             country.setName(map.get("name")!=null?map.get("name").toString():"");
             list.add(country);
         });
        tableView.setItems(list);
    }


    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }



    @FXML
    private void handleRefresh(ActionEvent event) {
        loadCountryList();
    }

    @FXML
    private void handleBookEditOption(ActionEvent event) {

        Country country = tableView.getSelectionModel().getSelectedItem();

        boolean exist = genericService.recordExist(Constant.COUNTRYVIEW,country.getId());
        if(exist ==false){
            AlertInformation.showErrorMessage("No country selected","Country with give id does not exist");
            return;
        }
        Map<String, Object> properties = genericService.findById(Constant.COUNTRYVIEW,country.getId());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/country/country.fxml"));
            Parent parent = loader.load();

            CountryController controller = loader.getController();
            controller.infalteUI(properties);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Country");
            stage.setScene(new Scene(parent));
            stage.show();
            AssistantUtil.setStageIcon(stage);

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
        boolean isDeleted = genericService.deleteById(Constant.COUNTRYVIEW,id);
        if(isDeleted==true){
            AlertInformation.showSuccesMessage("Success","Country has been deleted succesfully");
            loadCountryList();
            return;
        }else {
            AlertInformation.showErrorMessage("Error","Country has been deleted try again");
        }
    }

    @FXML
    private void addCountry(ActionEvent event) {
        AssistantUtil.loadWindow(getClass().getResource("/views/country/country.fxml"), "Add New Country", null);

    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }



    
}
