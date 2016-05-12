package com.example;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class MyController 
{
	static
	{
	new MyController();
	}
	
	ArrayList<User> users=new ArrayList<>();
	
	ArrayList<SchollClass> classes=new ArrayList<>();
	
	ArrayList<Student> students=new ArrayList<>();
	
	ArrayList<Subject> subjects=new ArrayList<>();
	
	User active;
	
	
	public MyController()
	{
		users.add(new User("admin","na",0));
    	users.add(new User("mkowalska","na",1));
    	users.add(new User("anowak","na",2));
    	
    	students.add(new Student("Kamil","Bednarek",1));
    	students.add(new Student("Jan","Brzoza",2));
    	students.add(new Student("Adam","Cebulski",3));
		
	}
	
	
	@RequestMapping(value ="/", method=RequestMethod.GET)
	public String init(Model model)
	{
		model.addAttribute("person", new Person());
		
		return "index";
	}
	
	@RequestMapping(value ="/", method=RequestMethod.POST)
	public String login(@ModelAttribute Person person, Model model)
	{
		 model.addAttribute("person", person);
		 
		 for(int i=0; i<users.size(); i++)
	    	{
	    		System.out.print(users.get(i).getLogin());
	    		
	    		if (users.get(i).getLogin().equals(person.getName()))
	    			if (users.get(i).isLoggedProperly(person.getName(),person.getSurname()))
	    			{
	    				active=users.get(i);
	    				
	    				if (active.getPermission()==0)
	    				  return "admin";
	    				else 
	    					if(active.getPermission()==1)
	    						return "teacher";
	    					else
	    						if(active.getPermission()==2)
	    							return "student";
	    				
	    			}
	    	}
	
			 return "index";
	}
	
	@RequestMapping(value ="/adduser", method=RequestMethod.GET)
	public String addUser(Model model)
	{
		model.addAttribute("user",new User());
		
		return "adduser";
	}
	
	@RequestMapping(value ="/adduser", method=RequestMethod.POST)
	public String addUserRE(@ModelAttribute User user, Model model)
	{
		model.addAttribute("user",user);
		
		return "adduserRE";
	}
	
	@RequestMapping(value ="/addschoolclass", method=RequestMethod.GET)
	public String addSchoolClass(Model model)
	{
		model.addAttribute("schoolclass",new SchollClass());
		
		return "addschoolclass";
	}
	
	@RequestMapping(value ="/addschoolclass", method=RequestMethod.POST)
	public String addSchoolClassRE(@ModelAttribute SchollClass schoolclass, Model model)
	{
		model.addAttribute("schoolclass",schoolclass);
		
		return "addschoolclassRE";
	}
	
	@RequestMapping(value ="/addstudent", method=RequestMethod.GET)
	public String addStudent(Model model)
	{
		
		model.addAttribute("student", new Student("nic","brak",0));
		return "addstudent";
	}
	
	@RequestMapping(value ="/addstudent", method=RequestMethod.POST)
	public String addStudentRE(@ModelAttribute Student student, Model model)
	{
		
		model.addAttribute("student", student);
		return "addstudentRE";
	}
	
	@RequestMapping(value ="/addteacher", method=RequestMethod.GET)
	public String addTeacher(Model model)
	{
		model.addAttribute("teacher", new Teacher());
		return "addteacher";
	}
	
	@RequestMapping(value ="/addteacher", method=RequestMethod.POST)
	public String addTeacherRE(@ModelAttribute Teacher teacher, Model model)
	{
		model.addAttribute("teacher", teacher);
		return "addteacherRE";
	}
	
	@RequestMapping(value ="/blockuser", method=RequestMethod.GET)
	public String blockUser(Model model)
	{
		model.addAttribute("testowa",new Testowa());
		
		return "blockuser";
	}
	
	
	
	
}
