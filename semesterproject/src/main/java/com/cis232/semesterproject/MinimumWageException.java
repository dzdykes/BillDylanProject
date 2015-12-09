package com.cis232.semesterproject;

public class MinimumWageException extends Exception {
	
	// REQ#11
	public MinimumWageException(double value){
		super(String.format("%.2f is not a valid wage. Should be $15.00 or greater", value));
	}
}
