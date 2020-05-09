
package zw.co.abn.covid.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
import zw.co.abn.covid.service.impl.GenericService;
import zw.co.abn.covid.util.Constant;

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

    private GenericService userService = new GenericService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }


    @FXML
    private void addUser(ActionEvent event) {
        Map<String,Object> map = new HashMap<>();
        if (firstName.getText().trim().isEmpty() ||
                lastName.getText().trim().isEmpty() ||
                email.getText().isEmpty() ||
                mobileNumber.getText().isEmpty() ||
                password.getText().isEmpty() ||
                retypepassword.getText().isEmpty()) {
            AlertInformation.showErrorMessage("Error", "Fields need to be completed !!");
        } else if (!password.getText().trim().equals(retypepassword.getText().trim())) {
            AlertInformation.showErrorMessage("Error", "Fields need to been the same");
        }
        map.put("firstName",firstName.getText());
        map.put("lastName",lastName.getText());
        map.put("email",email.getText());
        map.put("mobileNumber",mobileNumber.getText());
        map.put("password",password.getText());

        boolean saved = userService.save(map, Constant.USERVIEW);
        if(saved == true){
            AlertInformation.showSuccesMessage("Yay","User has been saved ");
            getStage().close();
            return;
        }


    }
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
