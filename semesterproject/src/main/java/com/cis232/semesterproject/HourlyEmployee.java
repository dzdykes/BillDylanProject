package com.cis232.semesterproject;

public class HourlyEmployee extends Employee implements PayableEmployee{
	
	double hours;

	@Override
	public void setHours(double hours)
	{
		this.hours=hours;
	}
	
	public double getHours()
	{
		return hours;
	}

	public double getGrossPay() {
		// TODO Auto-generated method stub
		if(hours<=70){
			return hours*payRate;
		}else
		{
			return ((70*payRate)+((hours-70)*payRate*1.5));
		}
	}

	public double getTaxes() {
		// TODO Auto-generated method stub
		return getGrossPay()*.23;
	}

	public double getNetPay() {
		// TODO Auto-generated method stub
		return getGrossPay()-getTaxes();
	}
	
	public Employee getAllPayable() {
		return null;
	}

	public void setGrosspay(double parseDouble) {
		// TODO Auto-generated method stub
		
	}

	public void setNetPay(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
}
