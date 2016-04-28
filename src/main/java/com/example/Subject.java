package com.example;

import java.util.ArrayList;

public class Subject
{
	private String name;
	private Teacher teacher;
	private ArrayList<ArrayList<Double>> marks;
	
	
	public void printName()
	{
			System.out.print(name);
	}
	
	public String getName()
	{
		return name;
	}
	
	public Subject(String name, Teacher teacher, ArrayList<ArrayList<Double>> marks)
	{
		this.name=name;
		this.teacher=teacher;
		this.marks=marks;
	}
	
	public void removeMark(int who, double what)
	{
		this.marks.get(who).remove(what);
	}
	
	public void addMark(int who, double what)
	{
		this.marks.get(who).add(what);
	}
	
	public String getAllMarks()
	{
		String mark="";
		
		for (int i=0; i<marks.size(); i++)
		{
			mark+=Integer.toString(i)+':';
			for (int j=0; j<marks.get(i).size(); j++)
			{
				mark+=marks.get(i).get(j);
				
			}
			mark+=";\n";
		}
		
		return mark;
	}
	
	public void printAllMarks()
	{
		for (int i=0; i<marks.size(); i++)
		{
			System.out.print(i);
			for (int j=0; j<marks.get(i).size(); j++)
			{
				System.out.print(marks.get(i).get(j));
				
			}
			System.out.println();
		}
	}
	
	
	public String getTeacher()
	{
		return teacher.getTeacher();
	}
	
	public void printTeacher()
	{
		teacher.printTeacher();
	}
	
}
