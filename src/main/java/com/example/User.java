package com.example;

public class User 
{
	private String login;
	private String password;
	private int permissions;
	private boolean blocked;
	private int miss;
	
	public User(String login, String password, int permissions)
	{
		this.login=login;
		this.password=password;
		this.permissions=permissions;
		this.blocked=false;
		this.miss=0;
	}
	
	public int getPermission()
	{
		return permissions;
	}
	
	public String printMainMenu()
	{
		String menu="";
		
		if (permissions==0)
		{
			menu+="1. Dodaj użytkownika \n";
			menu+="2. Zablokuj użytkownika \n";
			menu+="3. Dodaj klasę \n";
			menu+="4. Dodaj przedmiot \n";
			menu+="5. Dodaj ucznia \n";
		}
		
		if (permissions==1)
		{
			menu+="1. Dodaj ocene";
			menu+="2. Wyswietl oceny klasy";
		}
		
		if (permissions==2)
		{
			menu+="1. Wyswietl oceny";
		}
			
		
		return menu;
	}
	
	public String getPermissionName()
	{
		switch(this.permissions)
		{
		case 0:return "administrator";
		case 1:return "nauczyciel";
		case 2:return "uczeń";
		default:return " ";
		}
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public boolean isLoggedProperly(String login, String password)
	{
		if (login.equals(this.login) && password.equals(this.password))
			return true;
		else 
			this.miss++;
		
		if (miss>=3) this.blocked=true;
		
		return false;
		
	}
	
	
	public void blockAccount()
	{
		this.blocked=true;
	}
	
	public void unBlockAccount(String code)
	{
		if (code.equals("1lg75dą"))
			this.blocked=false;
	}
	
	
}
