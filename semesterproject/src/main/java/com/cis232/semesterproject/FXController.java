package com.cis232.semesterproject;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void dd0909(ActionEvent event) {
    	String[] a = lvEmployees.getSelectionModel().getSelectedItem().split("-");
    	tfInfoName.setText(a[0].trim());
    	tfInfoId.setText(a[1].trim());
    	tfInfoPos.setText(Environment.getEmployeePosition(Integer.parseInt(tfInfoId.getText())));
    }

    @FXML
    void e5e8eb(ActionEvent event) {

    }
    
    
}
