package com.cis232.semesterproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Environment {
	final static String DB_URL = "jdbc:derby:Personnel/Employee;create=true";
	
	//This method will find an employee based on their Employee ID
	public static ArrayList<Employee> getAllHourlyEmployee()
	{
		try {	
			ArrayList<Employee> hourlyEmp = new ArrayList<>();
			
			Connection conn = DriverManager.getConnection(DB_URL);
			
			Statement stmt = conn.createStatement();
			
			String selectEmployee = "select * from Employee";
					//+ String.format("where isHourly = true");
			
			ResultSet results = stmt.executeQuery(selectEmployee);
			
			while(results.next())
			{
				Employee emp = new Employee();
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
	
	//This class will add an employee into the Employee table
    public static void addEmployee(int id, String name){
		try {
			//Insert employees to the Employee table
			Connection conn = DriverManager.getConnection(DB_URL);
			
			Statement stmt = conn.createStatement();
			
			String insertEmployee = String.format("insert into Employee (id, name)"
					+ " values (%d, '%s')", id, name);
			
			stmt.executeUpdate(insertEmployee);
			
			System.out.println("Employee added!");
			
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR with sql statement.");
		}
	}
    
    //This class will update an employee in the Employee table
    public static void updateEmployee(int employeeId, String employeeName, String position, double hourlyPayRate){
    	try {		
			//Update employees from Employee table
			Connection conn = DriverManager.getConnection(DB_URL);
    		
    		Statement stmt = conn.createStatement();
			
			String editEmployeeById = "update Employee "
					+String.format("set employeeName = '%s', ", employeeName)
					+String.format("position = '%s', ", position)
					+String.format("hourlyPayRate = %.2f ", hourlyPayRate)
					+String.format("where employeeId = %d", employeeId );
			
			stmt.executeUpdate(editEmployeeById);
			
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR with sql statement");
		}
    }
}
