package com.cis232.semesterproject;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
	@Override
	public void start(Stage stage) {
		
	try {
			//Load the FXML file
			// REQ#9
			Parent parent = FXMLLoader.load(getClass().getResource("EmployeeEdit.fxml"));
		
			//Set up a scene using the FXML file
			Scene scene = new Scene(parent);
		
			//Set up our stage using the scene
			stage.setTitle("Bill Freeman and Dylan Dykes Semester Project"); // REQ#1
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			System.out.println("Problem loading fxml file.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if(true)
		{
			BuildEnvironment.createEmployeeTable();
		}
		launch(args);
	}
}
