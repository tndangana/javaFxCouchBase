/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.net.URL;
import java.util.Map;
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
public class UserListController implements Initializable {


    ObservableList<User> userObservableList  = FXCollections.observableArrayList();

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> firstName;
    @FXML
    private TableColumn<User, String> lastName;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> mobileNumber;
    @FXML
    private TableColumn<?, ?> verified;
    private GenericService userService = new GenericService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        loadUserData();
    }


    public void initiateTable(){

        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }


    public void loadUserData(){
        userObservableList.clear();
        userService.findAll(Constant.USERVIEW).stream().forEach(o -> {
            Map<String,Object> map = (Map<String, Object>) o;
            User user = new User();
            user.setMobileNumber(map.get("mobileNumber")!=null?map.get("mobileNumber").toString():"");
            user.setEmail(map.get("email")!=null?map.get("email").toString():"");
            user.setLastName(map.get("lastName")!=null?map.get("lastName").toString():"");
            user.setVerified(map.get("verified")!=null?((Boolean) map.get("verified")).booleanValue():false);
            user.setId(map.get("_id").toString());
            userObservableList.add(user);
        });
        tableView.setItems(userObservableList);
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadUserData();
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
        boolean isDeleted = userService.deleteById(Constant.USERVIEW,id);
        if(isDeleted==true){
            AlertInformation.showSuccesMessage("Success","User has been deleted successfully");
            loadUserData();
            return;
        }else {
            AlertInformation.showErrorMessage("Error","User has not been deleted try again");
        }
    }

    @FXML
    private void addUser(ActionEvent event) {
        AssistantUtil.loadWindow(getClass().getResource("/views/user/user.fxml"), "Add New User", null);

    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }
    
}
