package com.cis232.semesterproject;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FXController {

    @FXML
    private TextField tfInfoName;
    
    @FXML
    private TextField tfInfoId;
    
    @FXML
    private TextField tfInfoPos;
    
    @FXML
    private TextField tfInfoStreet;

    @FXML
    private TextField tfInfoCSZ;
    
    @FXML
    private ToggleGroup employeeType;

    @FXML
    private RadioButton rbHourly;

    @FXML
    private ListView<String> lvEmployees;

    @FXML
    private RadioButton rbSalary;

    @FXML
    void rbHourlyListener(ActionEvent event) {
		ArrayList<String> empName = new ArrayList<>();
		for(Employee e : Environment.getAllHourlyEmployee())
		{
			empName.add(e.getName() + " - " + e.getId());
		}
		ObservableList<String> list = FXCollections.observableArrayList(empName);
		lvEmployees.setItems(list);
    }
    
    @FXML
    void rbSalaryListener(ActionEvent event) {

    }

    @FXML
    void buttonListenerLookUp(ActionEvent event) {;
    	String[] a = lvEmployees.getSelectionModel().getSelectedItem().split("-");
    	tfInfoName.setText(a[0].trim());
    	tfInfoId.setText(a[1].trim());
    	
    	final int ID = Integer.parseInt(tfInfoId.getText());
    	
    	tfInfoPos.setText(Environment.getEmployeeStrInfo(ID,"position"));
    	tfInfoStreet.setText(Environment.getEmployeeStrInfo(ID,"street"));
    	tfInfoCSZ.setText(Environment.getCityStateZip(ID));
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Look, a Confirmation Dialog");
    	alert.setContentText("Are you ok with this?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    // ... user chose OK
    	} else {
    	    // ... user chose CANCEL or closed the dialog
    	}
    }

    @FXML
    void e5e8eb(ActionEvent event) {

    }
    
    
}
