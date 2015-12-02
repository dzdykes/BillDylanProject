package com.cis232.semesterproject;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
    private TextField tfInfoPayRate;
    
    @FXML
    private TextField tfHours;
    
    @FXML
    private ToggleGroup employeeType;

    @FXML
    private RadioButton rbHourly;

    @FXML
    private ListView<String> lvEmployees;

    @FXML
    private RadioButton rbSalary;
    
    @FXML
    private Button butLookUp;
    
    @FXML
    private Button butEdit;
    
    @FXML
    private Label lblEditConfirm;
    
    @FXML
    private Label lblNetPay;

    @FXML
    private Label lblCheckAmountString;
    
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
    	ArrayList<String> empName = new ArrayList<>();
		for(Employee e : Environment.getAllSalaryEmployee())
		{
			empName.add(e.getName() + " - " + e.getId());
		}
		ObservableList<String> list = FXCollections.observableArrayList(empName);
		lvEmployees.setItems(list);
    }

    @FXML
    void buttonListenerLookUp(ActionEvent event) {
		try {
			clearTextFields();
			
			String[] a = lvEmployees.getSelectionModel().getSelectedItem().split("-");
			tfInfoName.setText(a[0].trim());
			tfInfoId.setText(a[1].trim());
			
			final int ID = Integer.parseInt(tfInfoId.getText());
			
			tfInfoPos.setText(Environment.getEmployeeStrInfo(ID,"position"));
			tfInfoStreet.setText(Environment.getEmployeeStrInfo(ID,"street"));
			tfInfoCSZ.setText(Environment.getCityStateZip(ID));
			tfInfoPayRate.setText(Environment.getEmployeeStrInfo(ID, "payRate"));
			
		} catch (Exception e) {
			Alert alert;
			
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Error Message");
			alert.setHeaderText("Whoops you did not select an Employee.");
			alert.setContentText("Try again.");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
	    	    // ... user chose OK
	    	} else {
	    	    // ... user chose CANCEL or closed the dialog
	    	}
		}
    }
    
    @FXML
    private void buttonListenerEdit(ActionEvent event){

    	try
    	{
	    	Employee emp = new Employee();
	    	emp.setId(Integer.parseInt(tfInfoId.getText()));
	    	emp.setName(tfInfoName.getText());
	    	emp.setPosition(tfInfoPos.getText());
	    	emp.setStreet(tfInfoStreet.getText());
	    	emp.setCity(Environment.getEmployeeStrInfo(emp.getId(), "city"));
	    	emp.setState(Environment.getEmployeeStrInfo(emp.getId(), "state"));
	    	emp.setZip(Environment.getEmployeeStrInfo(emp.getId(), "zip"));
	    	emp.setPayRate(Double.parseDouble(tfInfoPayRate.getText()));
    	
        	Environment.updateEmployee(emp);
    		lblEditConfirm.setText("Employee Updated");
    	}catch(Exception e)
    	{
    		lblEditConfirm.setText("Error Could Not Update Employee");
    	}
    }
    
    @FXML
    private void buttonListenerCreatePaycheck(ActionEvent event)
    {
    	HourlyEmployee emp = new HourlyEmployee();
    	emp.setId(Integer.parseInt(tfInfoId.getText()));
    	emp.setName(tfInfoName.getText());
    	emp.setPosition(tfInfoPos.getText());
    	emp.setStreet(tfInfoStreet.getText());
    	emp.setCity(Environment.getEmployeeStrInfo(emp.getId(), "city"));
    	emp.setState(Environment.getEmployeeStrInfo(emp.getId(), "state"));
    	emp.setZip(Environment.getEmployeeStrInfo(emp.getId(), "zip"));
    	emp.setPayRate(Double.parseDouble(tfInfoPayRate.getText()));
    	
    	double hours = Double.parseDouble(tfHours.getText());
    	
    	emp.setHours(hours);
    	
    	lblNetPay.setText(String.format("%.2f", emp.getNetPay()));
    	
    	lblCheckAmountString.setText(CheckWriter.main(String.format("%.2f", emp.getNetPay())));
    }

	private void clearTextFields() {
		tfInfoId.clear();
    	tfInfoPos.clear();
    	tfInfoName.clear();
    	tfInfoStreet.clear();
    	tfInfoCSZ.clear();
    	tfInfoPayRate.clear();
    	lblEditConfirm.setText("");
	}
}
