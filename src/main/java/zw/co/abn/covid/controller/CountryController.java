/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import zw.co.abn.covid.alert.AlertInformation;
import zw.co.abn.covid.model.Country;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
@Controller
public class CountryController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private JFXTextField name;
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

    @FXML
    private void addCountry(ActionEvent event) {
        //check to see if its empty
        Optional<String> countryName = Optional.ofNullable(name.getText());
         if(!countryName.isPresent()){
              AlertInformation.showMaterialDialog(rootPane,mainContainer,new ArrayList<>(),"Empty field","Please enter the empty field !!");
              return;
         }

         //check to see if its not in the Database
//        if (DataHelper.isBookExists(bookID)) {
//            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Duplicate book id", "Book with same Book ID exists.\nPlease use new ID");
//            return;
//        }
         Country  country = new Country();
         country.setName(countryName.get());
         //save
//        boolean result = DataHelper.insertNewBook(book);
//        if (result) {
//            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "New book added", bookName + " has been added");
//            clearEntries();
//        } else {
//            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Failed to add new book", "Check all the entries and try again");
//        }

    }



    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
