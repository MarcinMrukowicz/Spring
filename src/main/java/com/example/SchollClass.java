package com.example;

import java.util.ArrayList;

public class SchollClass 
{
	private String name;
	private ArrayList<Student> students;
	private ArrayList<Subject> subjects;
	
	
	public String chooseSubject()
	{
		return "Wybierz przedmiot, który Cię interesuje, spośród"+getAllSubjects();
	}
	
	public SchollClass(String name, ArrayList<Student> students,ArrayList<Subject> subjects)
	{
		this.name=name;
		this.students=students;
		this.subjects=subjects;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void printName()
	{
		System.out.println(this.name);
	}
	
	
	public Subject getSubject(String name)
	{
		for (int i=0; i<subjects.size(); i++)
		{
			if (subjects.get(i).equals(name))
				return subjects.get(i);
		}
		
		return null;
	}
	
	public String getAllSubjects()
	{
		String sub=""; 
		
		for (int i=0; i<subjects.size(); i++)
		{
			sub+=subjects.get(i).getName();
			sub+=subjects.get(i).getTeacher();
		}
		
		return sub;
	}
	
	public void printAllSubjects()
	{
		for (int i=0; i<subjects.size(); i++)
		{
			subjects.get(i).printName();
			subjects.get(i).printTeacher();
		}
		
	}
	
	
	public String getAllStudents()
	{
		String std="";
		
		for (int i=0; i<students.size(); i++)
		{
			std+=students.get(i).getStudent();
		}
		
		return std;
		
	}
	
	
	public void printAllStudents()
	{
		
		for (int i=0; i<students.size(); i++)
		{
			students.get(i).printStudent();
			
		}
		
		
	}
	
	
}
