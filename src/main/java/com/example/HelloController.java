package com.example;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController 
{
	ArrayList<User> users=new ArrayList<>();
	
	ArrayList<SchollClass> classes=new ArrayList<>();
	
	ArrayList<Student> students=new ArrayList<>();
	
	ArrayList<Subject> subjects=new ArrayList<>();
	
	User active;
	
	@RequestMapping(value="/mainpannel/viewClasses/{name}/{target}/{targetName}/removeMark/{who}/{what}", method=RequestMethod.GET)
	public String removeMark(@PathVariable String name, @PathVariable String target,@PathVariable String targetName ,@PathVariable String who, @PathVariable String what)
	{
		String message="";
		
		if (active!=null)
			if (active.getPermission()==1)
			{
			for (int i=0; i<classes.size();i++)
				if (name.equals(classes.get(i).getName()))
					{
					if (target.equals("subjects"))
						{
						for (int j=0; j<subjects.size(); j++)
							if (subjects.get(j).getName().equals(targetName))
							{
								subjects.get(i).removeMark(Integer.parseInt(who), Integer.parseInt(what));
							}
						
							}
					}
			}
			else message="Nie możesz usuwać ocen";
		
		return message;
	}
	
	@RequestMapping(value="/mainpannel/viewClasses/{name}/{target}/{targetName}", method=RequestMethod.GET)
	public String viewClassSubject(@PathVariable String name, @PathVariable String target, @PathVariable String targetName)
	{
		String message="";
		
		if (active!=null)	
			for (int i=0; i<classes.size();i++)
				if (name.equals(classes.get(i).getName()))
					{
					if (target.equals("subjects"))
							{
								for (int j=0; j<subjects.size(); j++)
								if (subjects.get(j).getName().equals(targetName))
								{
									message+=subjects.get(j).getAllMarks();
								}
							}
					
					}
		
		
		return message;
	}
	
	@RequestMapping(value="/mainpannel/viewClasses/{name}/{target}/{targetName}/addMark/{who}/{what}", method=RequestMethod.GET)
	public String addMark(@PathVariable String name, @PathVariable String target,@PathVariable String targetName ,@PathVariable String who, @PathVariable String what)
	{
		String message="";
		
		if (active!=null)
			if (active.getPermission()==1)
			{
			for (int i=0; i<classes.size();i++)
				if (name.equals(classes.get(i).getName()))
					{
					if (target.equals("subjects"))
						{
						for (int j=0; j<subjects.size(); j++)
							if (subjects.get(j).getName().equals(targetName))
							{
								subjects.get(j).addMark(Integer.parseInt(who), Double.parseDouble(what));
								message+="Dodano uczniowi o nr "+who+"ocenę "+what;
							}
						}
					}
			} else message="Nie możesz dodawać ocen";
	
		return message;
	}
	
	
	@RequestMapping(value="/mainpannel/viewClasses/{name}/{target}", method=RequestMethod.GET)
	public String viewClassTarget(@PathVariable String name, @PathVariable String target)
	{
		String message="";
		
	if (active!=null)	
		for (int i=0; i<classes.size();i++)
			if (name.equals(classes.get(i).getName()))
				{
					message+="Podgląd klasy "+name+" wpisz subjects, żeby zobaczyć przedmioty, a students uczniow";
		
					if (target.equals("subjects"))
						{
						message+=classes.get(i).chooseSubject();
						return message;
						}
					else
					if (target.equals("students"))
						{
						message+=classes.get(i).getAllStudents();
						return message;
						}
					else
						message+="Błędny wybór, wpisz subjects albo students";
		
				}
		
		return message;
	}
	
	
	
	@RequestMapping(value="/mainpannel/viewClasses/{name}", method=RequestMethod.GET)
	public String viewClass(@PathVariable String name)
	{
		String message="";
		
		if (active!=null)
		{
		
		for (int i=0; i<classes.size();i++)
		if (name.equals(classes.get(i).getName()))
			message+="Podgląd klasy "+name+" wpisz subjects, żeby zobaczyć przedmioty, a students uczniow";
		
		message+="Błędna nazwa klasy";
		}
		
		
		
		return message;
	}
	
	@RequestMapping(value="/mainpannel/viewClasses", method=RequestMethod.GET)
	public String viewClasses()
	{
		
		String cls="";
		
		if (active!=null)
		{
			
			for (int i=0; i<classes.size(); i++)
				cls+=classes.get(i).getName()+'\n';
		
		} else return "acces denied";
		
		
		return cls;
	}
	
	@RequestMapping(value= "/mainpannel/addUser/{login}/{password}/{permissions}", method=RequestMethod.GET)
	public String addUser(@PathVariable String login, @PathVariable String password, @PathVariable String permissions)
	{
		
		if (active!=null)
		{
			if (active.getPermission()==0)
				users.add(new User(login,password,Integer.parseInt(permissions)));
			else return "Acces denied";
		}else return "Acces denied";
		
		return "Dodano użytkownika:"+login;
	}
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello()
    {
    	users.add(new User("admin","na",0));
    	users.add(new User("mkowalska","na",1));
    	users.add(new User("anowak","na",2));
    	
    	students.add(new Student("Kamil","Bednarek",1));
    	students.add(new Student("Jan","Brzoza",2));
    	students.add(new Student("Adam","Cebulski",3));
    	
    	ArrayList<ArrayList<Double>> marks=new ArrayList<>();
    	
    	for (int i=0; i<students.size(); i++)
    		{
    		marks.add(new ArrayList<>());
    		}
    	
    	marks.get(0).add(5.0);
    	marks.get(0).add(4.0);
    	
    	subjects.add(new Subject("Język polski",new Teacher("Maciej","Kot"),marks));
    	
    	classes.add(new SchollClass("1a",students , subjects));
    	
        return "Witaj w wirtualnym dzienniku. Musisz podać swój login i haslo. \n wpisz w adresie przegląrki.";
    }
    
    
    @RequestMapping(value="/mainpannel", method=RequestMethod.GET)
    public String mainPannel()
    {
    	
    	String message="Error";
    	
    	if (active!=null)
    	{
    		message="Witaj w menu głównym:"+active.getLogin()+'\n';
    		message+="Jesteś zalogowany jako:"+active.getPermissionName()+'\n';
    		message+=active.printMainMenu();
    	}
    	
    	
    	return message;
    }
    
    @RequestMapping(value = "/log/{login}/{password}", method = RequestMethod.GET)
    public String login(@PathVariable String login,@PathVariable String password)
    {
    	System.out.print(users.size());
    	
    	for(int i=0; i<users.size(); i++)
    	{
    		System.out.print(users.get(i).getLogin());
    		
    		if (users.get(i).getLogin().equals(login))
    			if (users.get(i).isLoggedProperly(login, password))
    			{
    				active=users.get(i);
    				System.out.print("Działam, jesteś zalogowany");
    				break;
    			}
    	}
    	
    	return "ok";
    }
    
    
    
    
    @RequestMapping(value="/diaryadd/{name}/{surname}", method=RequestMethod.GET)
    public String submitPersonForm(@PathVariable String name,@PathVariable String surname)
    {
    	return name+"\n"+surname;
    }
    
    
}
