/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.abn.covid.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author bignerd
 */
public class DashboardController implements Initializable {

    @FXML
    private Label totalCountryCount;
    @FXML
    private Label totalUserCount;
    @FXML
    private Label totalSymptomsCount;
    @FXML
    private Label totalPatientCount;
    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadChart();
        loadCount();
    }



    private void loadCount(){
        totalCountryCount.setText("2");
        totalUserCount.setText("23");
        totalPatientCount.setText("9");
        totalSymptomsCount.setText("5");
    }

    private void loadChart()
    {
        PieChart.Data slice1 = new PieChart.Data("Male", 20);
        PieChart.Data slice2 = new PieChart.Data("Female"  , 10);
        PieChart.Data slice3 = new PieChart.Data("Other" , 4);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

    }
    
}
