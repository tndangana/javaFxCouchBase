/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Controller;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.util.AssistantUtil;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
@Controller
public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    private String userNameTemp="admin";
    private String passwordTemp="admin";

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {

        if(username.getText().equals(userNameTemp.trim()) && password.getText().equals(passwordTemp)){
            closeStage();
            loadMain();
        }
         else if (username.getText().isEmpty() && !password.getText().isEmpty()){
            AlertInformation.showErrorMessage("Username is empty","Please fill in field !!");
        }
        else if (!username.getText().isEmpty() && password.getText().isEmpty()){
            AlertInformation.showErrorMessage("Password is empty","Please fill in field !!");
        }
        else if (username.getText().isEmpty() && password.getText().length() < 6){
            AlertInformation.showErrorMessage("Password is short","Please fill in field !!");
        }
        else{
            AlertInformation.showErrorMessage("Login Failure","Enter Correct Credentials");
            System.out.println("'''''");
                username.getStyleClass().add("wrong-credentials");
                password.getStyleClass().add("wrong-credentials");
            }
          }

    @FXML
    private void handleRegisterAction(ActionEvent event) {
        AssistantUtil.loadWindow(getClass().getResource("/views/user/user.fxml"), "User Registration", null);

    }

    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }

    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/views/dashboard/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Covid Java FX Crud Basic");
            stage.setScene(new Scene(parent));
            stage.show();
            AssistantUtil.setStageIcon(stage);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
