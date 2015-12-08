package com.cis232.semesterproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Environment extends FXController {
	final static String DB_URL = "jdbc:derby:Personnel/Employee;create=true";
	
	// REQ#8
	public static ArrayList<HourlyEmployee> getAllHourlyEmployee()
	{
		try {	
			ArrayList<HourlyEmployee> hourlyEmp = new ArrayList<>();
			
			Connection conn = DriverManager.getConnection(DB_URL);
			
			Statement stmt = conn.createStatement();
			
			String selectEmployee = "select * from Employee "
					+ String.format("where isHourly = true");
			
			ResultSet results = stmt.executeQuery(selectEmployee);
			
			while(results.next())
			{
				HourlyEmployee emp = new HourlyEmployee();
				emp.setId(results.getInt("id"));
				emp.setName(results.getString("name"));
				hourlyEmp.add(emp);
			}
			
			conn.close();
			
			return hourlyEmp;
			} catch (SQLException e) {
			System.out.println("ERROR with sql statement.");
			return null;
		}
	}
	
	public static ArrayList<SalaryEmployee> getAllSalaryEmployee()
	{
		try {	
			ArrayList<SalaryEmployee> salaryEmp = new ArrayList<>();
			
			Connection conn = DriverManager.getConnection(DB_URL);
			
			Statement stmt = conn.createStatement();
			
			String selectEmployee = "select * from Employee "
					+ String.format("where isHourly = false");
			
			ResultSet results = stmt.executeQuery(selectEmployee);
			
			while(results.next())
			{
				SalaryEmployee emp = new SalaryEmployee();
				emp.setId(results.getInt("id"));
				emp.setName(results.getString("name"));
				salaryEmp.add(emp);
			}
			
			conn.close();
			
			return salaryEmp;
			} catch (SQLException e) {
			System.out.println("ERROR with sql statement.");
			return null;
		}
	}
	// Gets any Employee string field in employees table based
	// on employee id and the field name you are looking for
	public static String getEmployeeStrInfo(int id, String strPar)
	{
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			
			String strInfo = "";
			
			String selectInfo = String.format("select %s from Employee "
					+ "where id = %d", strPar, id);
			
			ResultSet results = stmt.executeQuery(selectInfo);
			
			if(results.next())
			{
				strInfo = results.getString(String.format("%s", strPar));
			}
			
			conn.close();
			
			return strInfo;
			
		}
		catch(SQLException e)
		{
			System.out.println("ERROR with position select sql statment.");
		}
		
		return null;
	}
	
	public static String getCityStateZip(int id)
	{
		String csz = "";
		
		csz = csz.concat(getEmployeeStrInfo(id, "city")+ " ");
		csz = csz.concat(getEmployeeStrInfo(id, "state")+ ", ");
		csz = csz.concat(getEmployeeStrInfo(id, "zip")+ " ");
		
		return csz;
	}
	
	//This class will add an employee into the Employee table
	// REQ#7
    public static void addEmployee(Employee emp, boolean isHourly){
		try {
			//Insert employees to the Employee table
			Connection conn = DriverManager.getConnection(DB_URL);
			
			Statement stmt = conn.createStatement();
			
			String insertEmployee = String.format("insert into Employee (id, name, position, street, city, state, zip, payRate, isHourly)"
					+ " values (%d, '%s', '%s', '%s', '%s', '%s', '%s', %f, %s"
					+ ")", emp.getId(), emp.getName(), emp.getPosition(), emp.getStreet()
					, emp.getCity(), emp.getState(), emp.getZip(), emp.getPayRate(), isHourly);
			
			stmt.executeUpdate(insertEmployee);
			
			System.out.println("Employee added!");
			
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR with sql insert statement.");
		}
	}
    
    //This class will update an employee in the Employee table
    public static void updateEmployee(Employee emp){
    	try {		
			//Update employees from Employee table
			Connection conn = DriverManager.getConnection(DB_URL);
			
    		Statement stmt = conn.createStatement();
    		
			String editEmployeeById = String.format("update Employee "
					+"set name = '%s', position = '%s', street = '%s', "
					+ "city = '%s', state = '%s', zip = '%s', payRate = %f "
					+ "where id = %d", emp.getName(), emp.getPosition(), emp.getStreet(), emp.getCity(), emp.getState(), emp.getZip(), emp.getPayRate(), emp.getId());
			
			stmt.executeUpdate(editEmployeeById);
			
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR with sql statement");
		}
    }
    
}
