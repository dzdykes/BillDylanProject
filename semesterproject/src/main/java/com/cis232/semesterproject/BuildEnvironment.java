package com.cis232.semesterproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BuildEnvironment {
	//Create and load the database
	public static void createEmployeeTable()
	{
		try {
			//Create connection to database
			Connection conn = DriverManager.getConnection(Environment.DB_URL);
			System.out.println("Connected to database!");
			
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
					+ "position varchar(50))"; 

			stmt.execute(createTable);

			System.out.println("Employee table created.");
			
			for(int i=1; i<100; i++)
			{
				Environment.addEmployee(i, "Employee " + i, "Cashier");
			}
			
			//Close the connection to the database
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error could not create the table.");
		}
	}
}
