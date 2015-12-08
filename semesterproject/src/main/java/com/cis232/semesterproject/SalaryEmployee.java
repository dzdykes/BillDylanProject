package com.cis232.semesterproject;

// REQ#4 REQ#6
public class SalaryEmployee extends Employee implements PayableEmployee {
	double hours;
	
	public void setHours(double hours)
	{
		this.hours=hours;
	}
	
	public double getHours()
	{
		return hours;
	}
	
	@Override
	public double getGrossPay() {
		// TODO Auto-generated method stub
		double bonus=0.0;
		if(hours>=100)
		{
			bonus=(payRate/52)*.2;
		}
		return ((payRate/52) + bonus);
	}

	@Override
	public double getTaxes() {
		// TODO Auto-generated method stub
		return (payRate/52)*.23;
	}

	@Override
	public double getNetPay() {
		// TODO Auto-generated method stub
		return getGrossPay()-getTaxes();
	}
	
}
