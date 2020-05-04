
package zw.co.abn.covid.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.User;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
@Controller
public class UserController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField email;    
    @FXML
    private JFXTextField mobileNumber;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField retypepassword;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

//    String firstName, String lastName, String email, String password, String mobileNumber

    @FXML
    private void addUser(ActionEvent event) {
        Optional<User> user = Optional.of(new User(firstName.getText(),lastName.getText(),email.getText(),password.getText(),mobileNumber.getText()));
         if(!user.isPresent()){
             AlertInformation.showMaterialDialog(rootPane,mainContainer,new ArrayList<>(),"Empty field","Please enter the empty field !!");
             return;
         }

         //check if duplicate

        //save
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
