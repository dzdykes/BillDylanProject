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
    private TextField labelInfoName;

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
			empName.add(e.getName());
		}
		ObservableList<String> list = FXCollections.observableArrayList(empName);
		lvEmployees.setItems(list);
    }
    
    @FXML
    void rbSalaryListener(ActionEvent event) {

    }

    @FXML
    void dd0909(ActionEvent event) {
    }

    @FXML
    void e5e8eb(ActionEvent event) {

    }
    
    
}
