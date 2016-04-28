package com.example;

public class Student extends Person
{
	private int nr;
	
	public Student(String name, String surname, int nr)
	{
		super(name, surname);
		this.nr=nr;
		
	}

	
	public String getStudent()
	{
		return Integer.toString(nr)+this.getPerson();
	}
	
	public void printStudent()
	{
		System.out.print(this.nr);
		this.printPerson();
		System.out.print('\n');
	}
		
}
