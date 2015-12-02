package com.cis232.semesterproject;

public class HourlyEmployee extends Employee implements PayableEmployee{
	
	double hours;

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
		if(hours<=40){
			return hours*payRate;
		}else
		{
			return ((40*payRate)+((hours-40)*payRate*1.5));
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
}
