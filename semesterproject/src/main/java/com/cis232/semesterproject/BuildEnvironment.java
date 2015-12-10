package com.cis232.semesterproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class BuildEnvironment {
	//Create and load the database
	public static void createEmployeeTable()
	{
		try {
			//Create connection to database
			Connection conn = DriverManager.getConnection(Environment.DB_URL);
			System.out.println("Connected to database!");
			
			Random rn = new Random();
			
			Statement stmt = conn.createStatement();
			
			//Drop the table if it exists
			try{
				String dropTable = "drop table Employee ";
				stmt.execute(dropTable);
				System.out.println("Employee table dropped.");
			}
			catch(SQLException e)
			{
				System.out.println("Employee table does not exist.");
			}
		
			//Create the table Employee
			String createTable = "create table Employee("
					+ "id int not null primary key, "
					+ "name varchar(50), "
					+ "position varchar(50), "
					+ "street varchar(50), "
					+ "city varchar(50), "
					+ "state varchar(50), "
					+ "zip varchar(15), "
					+ "payRate double, "
					+ "isHourly boolean)";

			stmt.execute(createTable);

			System.out.println("Employee table created.");
			
			int[] empId = randomId2d(21);
			
			for(int i = 1; i<15; i++)
			{
				Employee newEmp = new HourlyEmployee();
				newEmp.setId(empId[i-1]);
				newEmp.setName("Employee ");
				newEmp.setPosition("Cashier");
				newEmp.setStreet("TestStreet St");
				newEmp.setCity("TestCityVille");
				newEmp.setState("TS");
				newEmp.setZip("55555-5555");
				newEmp.setPayRate(15.15);
				Environment.addEmployee(newEmp, true);
			}
			
			for(int i = 15; i<21; i++)
			{
				Employee newEmp = new Employee(empId[i], "Semployee ", "Cashier", "TestStreet St", "TestCityVille", "TS", "55555-5555", 70000.00);
				Environment.addEmployee(newEmp, false);
			}
			//Close the connection to the database
			conn.close();
		} catch (SQLException | MinimumWageException e) {
			System.out.println("Error could not create the table.");
		}
	}

	static Random rn = new Random();
	
	public static int[] randomId2d(int n)
	{
		int[] ints = new int[n];
		return randomId(0, n, ints);
	}

	private static int[] randomId(int m, int n, int[] ints) {
		
		int x = rn.nextInt(85000)+12345;
		
		if(m==n) {return ints;}
		if(m==0) {
			ints[0]=x;
		}
		for(int i=0; i<m; i++)
		{
			if(ints[i]==x){return randomId(m, n, ints);}
		}
		ints[m] = x;
		m++;
		return randomId(m, n, ints);
	}
}
