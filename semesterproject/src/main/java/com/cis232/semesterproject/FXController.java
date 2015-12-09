package com.cis232.semesterproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

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
    private Button butEdit;
    
    @FXML
    private Label lblEditConfirm;
    
    @FXML
    private Label lblNetPay;

    @FXML
    private Label lblCheckAmountString;
    
    @FXML
    private Label lblName;
    
    @FXML
    private Label lblStreet;
    
    @FXML
    private Label lblCSZ;
    
    @FXML
    private Label lblCheckDate;
    
    @FXML 
    DatePicker dpPayDate;
    
    @FXML
    private TextField tfOvHrs;
    
    @FXML
    private TextField tfTaxes;
    
    @FXML
    private Label lbHours;
    
    @FXML
    private Label  lbOvtHrs;
    
    @FXML
    private Label  lbtaxes;
    
    @FXML
    private Label  lbgrsPay;
    
    @FXML
    private Label  lbTaxes;
    
    @FXML
    private Label  lbPosition;
    
    @FXML
    private Label  lbPayPer;
    
    @FXML
    private Label  lbPayRate;
    
    @FXML
    private Label lblNetPayPayStub;
    
    @FXML
    private Label lblCheckDatePayStub;
    
    @FXML
    private Label lblPosition;
    
    @FXML
    private Label lblPayStubName;
    
    @FXML
    private Tab tabPayCheck;
    
    @FXML
    private Label lblWageError;
    
    @FXML
    void rbHourlyListener(ActionEvent event) {
		populateHourlyEmployee();
    }
    
    @FXML
    void rbSalaryListener(ActionEvent event) {
    	populateSalaryEmployee();
    }
    
    private ArrayList<Employee> employee = new ArrayList<>();
    
    // REQ#10
    public void initialize()
    {
    	employee.clear();
    	for(HourlyEmployee e : Environment.getAllHourlyEmployee())
    	{
    		employee.add(e);
    	}
    	for(SalaryEmployee e: Environment.getAllSalaryEmployee())
    	{
    		employee.add(e);
    	}
    }
    
    @FXML
    private void buttonListenerEdit(ActionEvent event){
    	Employee emp = new Employee();
    	
    	try
    	{
	    	emp.setId(Integer.parseInt(tfInfoId.getText()));
	    	emp.setName(tfInfoName.getText());
	    	emp.setPosition(tfInfoPos.getText());
	    	emp.setStreet(tfInfoStreet.getText());
	    	StringTokenizer tokens = new StringTokenizer(tfInfoCSZ.getText(), ","); // REQ#2
	    	String cityState = tokens.nextToken().toString();
	    	String zip = tokens.nextToken();
	    	emp.setCity(cityState.substring(0, cityState.length()-3));
	    	emp.setState(cityState.substring(cityState.length()-2));
	    	emp.setZip(zip);
	    	emp.setPayRate(Double.parseDouble(tfInfoPayRate.getText()));
        	Environment.updateEmployee(emp);
    		lblEditConfirm.setText("Employee Updated");
    	}catch(MinimumWageException e){ // REQ#11 REQ#12
    		lblWageError.setVisible(true);
    	}catch(Exception e){
    		lblEditConfirm.setText("Error Could Not Update Employee");
    	}
    	
    	if(rbHourly.isSelected())
    	{
    		initialize();
    		populateHourlyEmployee();
    	}else if(rbSalary.isSelected())
    	{
    		initialize();
    		populateSalaryEmployee();
    	}
    }
    
    @FXML
    private void buttonListenerCreatePaycheck(ActionEvent event)
    {
    	tabPayCheck.setDisable(false);
    	try{
    		LocalDate d = dpPayDate.getValue();
        	lblCheckDate.setText(d.toString());
        	lblPayStubName.setText("Pay Stub for " + tfInfoName.getText());
        	
        	double netPay = 0;
        	
        	if(rbHourly.isSelected())
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
            	emp.setHours(Double.parseDouble(tfHours.getText()));
            	if(emp.getHours()>70)
            	{
                	lbOvtHrs.setText(String.format("%.2f", emp.getHours()-70));
            	}
            	lbHours.setText(emp.getHours()+"");
            	lbPayRate.setText(String.format("%.2f", emp.getPayRate()));
            	lbgrsPay.setText(String.format("%.2f", emp.getGrossPay()));
            	lblNetPayPayStub.setText(String.format("%.2f", emp.getNetPay()));
            	lbTaxes.setText(String.format("%.2f", emp.getTaxes()));
            	lblCheckDatePayStub.setText(d.toString());
            	lblPosition.setText(tfInfoPos.getText());
            	
            	netPay = emp.getNetPay();
        	}else if(rbSalary.isSelected())
        	{
        		SalaryEmployee emp = new SalaryEmployee();
        		 
            	emp.setId(Integer.parseInt(tfInfoId.getText()));
            	emp.setName(tfInfoName.getText());
            	emp.setPosition(tfInfoPos.getText());
            	emp.setStreet(tfInfoStreet.getText());
            	emp.setCity(Environment.getEmployeeStrInfo(emp.getId(), "city"));
            	emp.setState(Environment.getEmployeeStrInfo(emp.getId(), "state"));
            	emp.setZip(Environment.getEmployeeStrInfo(emp.getId(), "zip"));
            	emp.setPayRate(Double.parseDouble(tfInfoPayRate.getText()));
            	emp.setHours(Double.parseDouble(tfHours.getText()));
            	if(emp.getHours()>100)
            	{
                	lbOvtHrs.setText(String.format("%.2f", emp.getHours()-100));
            	}
            	lbHours.setText(emp.getHours()+"");
            	lbPayRate.setText(String.format("%.2f", emp.getPayRate()));
            	lbgrsPay.setText(String.format("%.2f", emp.getGrossPay()));
            	lblNetPayPayStub.setText(String.format("%.2f", emp.getNetPay()));
            	lbTaxes.setText(String.format("%.2f", emp.getTaxes()));
            	lblCheckDatePayStub.setText(d.toString());
            	lblPosition.setText(tfInfoPos.getText());
            	
            	netPay = emp.getNetPay();
        	}

        	lblName.setText(tfInfoName.getText());
        	lblStreet.setText(tfInfoStreet.getText());
        	lblCSZ.setText(tfInfoCSZ.getText());
        	lblNetPay.setText(String.format("%.2f", netPay));
        	
        	lblCheckAmountString.setText(CheckWriter.main(String.format("%.2f", netPay)));
    	}catch(Exception e)
    	{
    		
    	}
    }
    
    @FXML
    private void lvListenerGetEmployeeInfo(MouseEvent event)
    {
    	try {
			tabPayCheck.setDisable(true);
			clearTextFields();
			
			String[] a = lvEmployees.getSelectionModel().getSelectedItem().split("-");
			tfInfoId.setText(a[1].trim());
			
			final int ID = Integer.parseInt(tfInfoId.getText());
			
			tfInfoName.setText(Environment.getEmployeeStrInfo(ID, "name"));
			tfInfoPos.setText(Environment.getEmployeeStrInfo(ID,"position"));
			tfInfoStreet.setText(Environment.getEmployeeStrInfo(ID,"street"));
			tfInfoCSZ.setText(Environment.getCityStateZip(ID));
			tfInfoPayRate.setText(String.format("%.2f", Double.parseDouble(Environment.getEmployeeStrInfo(ID, "payRate"))));
			
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

    // REQ#10
    private void populateHourlyEmployee()
    {
    	ArrayList<String> empName = new ArrayList<>();
		for(Employee e : employee)
		{
			if(e instanceof HourlyEmployee)
			{
				empName.add(e.getName() + " - " + e.getId());
			}
		}
		ObservableList<String> list = FXCollections.observableArrayList(empName);
		lvEmployees.setItems(list);
    }

    // REQ#10
    private void populateSalaryEmployee()
    {
    	ArrayList<String> empName = new ArrayList<>();
		for(Employee e : employee)
		{
			if(e instanceof SalaryEmployee)
			{
				empName.add(e.getName() + " - " + e.getId());
			}
		}
		ObservableList<String> list = FXCollections.observableArrayList(empName);
		lvEmployees.setItems(list);
    }
    
	private void clearTextFields() {
		tfInfoId.clear();
    	tfInfoPos.clear();
    	tfInfoName.clear();
    	tfInfoStreet.clear();
    	tfInfoCSZ.clear();
    	tfInfoPayRate.clear();
    	tfHours.clear();
    	lblEditConfirm.setText("");
    	lblWageError.setVisible(false);
	}
}
