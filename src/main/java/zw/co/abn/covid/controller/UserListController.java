/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.net.URL;
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
import zw.co.abn.covid.model.User;
import zw.co.abn.covid.util.AssistantUtil;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTable();
        loadData();
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


    public void loadData(){
        userObservableList.clear();

        userObservableList.addAll(
                new User("Tonderai","Ndangana","ndangana8@gmail.com","0772275148",Boolean.FALSE),
                new User("Tonderai","Ndangana","ndangana8@gmail.com","0772275148",Boolean.FALSE),
                new User("Tonderai","Ndangana","ndangana8@gmail.com","9887845845",Boolean.TRUE));

        tableView.setItems(userObservableList);
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
    private void addUser(ActionEvent event) {
        AssistantUtil.loadWindow(getClass().getResource("/views/user/user.fxml"), "Add New User", null);

    }

    @FXML
    private void closeStage(ActionEvent event) {
        getStage().close();
    }
    
}
