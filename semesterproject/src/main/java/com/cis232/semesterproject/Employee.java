package com.cis232.semesterproject;

// REQ#5
public class Employee {
	int id;
	String name;
	String position;
	String street;
	String city;
	String state;
	String zip;
	double payRate;
	double hours;
	
	public Employee(){
		id=0;
		name="";
		position="";
		street="";
		city="";
		state="";
		zip="";
		payRate=0.0;
	}
	
	public Employee(int id, String name, String position, String street, 
			String city, String state, String zip, double payRate){
		this.id=id;
		this.name=name;
		this.position=position;
		this.street=street;
		this.city=city;
		this.state=state;
		this.zip=zip;
		this.payRate = payRate;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPosition() {
		return position;
	}

	public String getStreet()
	{
		return street;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public String getZip()
	{
		return zip;
	}
	
	public double getPayRate()
	{
		return payRate;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public void setStreet(String street)
	{
		this.street = street;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	
	public void setPayRate(double payRate) throws MinimumWageException
	{
		if(payRate<15)
    	{
    		throw new MinimumWageException(payRate);
    	}
		this.payRate = payRate;
	}

	public void setHours(double hours) {
		this.hours=hours;
	}
}
