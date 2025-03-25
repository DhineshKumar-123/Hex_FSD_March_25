package com.springcoreapp.main.util;

public class MyUtil 
{
	//Getting input in the form of string and separate them as a fname,mname,lname;
	
	public String getFirstName(String fullname)
	{
		String[] name = fullname.split(" ");
		return name[0];
	}
	public String getLastName(String fullname)
	{
		String[] name = fullname.split(" ");
		return name[name.length-1];// instead of saying the third value just returning the last value in the string array
	}

}
