package com.example;

public class Teacher extends Person
{

	
	
	public Teacher()
	{
		
		super();
	}
	
	public Teacher(String name,String surname)
	{
		super(name,surname);
		
	}
	
	public void printTeacher()
	{
		this.printPerson();
	}
	
	public String getTeacher()
	{	
		return this.getPerson();
	}
	
	
}
