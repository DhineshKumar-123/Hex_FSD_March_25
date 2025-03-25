package com.springcoreapp.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcoreapp.main.config.AppConfig;
import com.springcoreapp.main.util.DbUtil;
import com.springcoreapp.main.util.MyUtil;

public class App {

	public static void main(String[] args)
	{
//		MyUtil myUtil = new MyUtil();//Instead of doing this we are going for spring
//		String fullname = "DhineshKumar GokulaKannan Murugesan";
//		System.out.println(myUtil.getFirstName(fullname));
		
		// This is for the creation of object calling the config class
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		MyUtil myUtil = context.getBean(MyUtil.class);
		
		// Checking the created objects here
		MyUtil myUtil1 = context.getBean(MyUtil.class); 
		System.out.println(myUtil);
		
		//second object fetch from context
		MyUtil myUtil2 = context.getBean(MyUtil.class); 
		System.out.println(myUtil2);
		
		String name= "harry Dhinesh potters";
		System.out.println(myUtil.getFirstName(name));
		System.out.println(myUtil.getLastName(name));
		
		// HEere the another util file goes down
		DbUtil dbUtil = context.getBean(DbUtil.class);
		dbUtil.dbConnect();
		
		// This is important for the closing the Context
		((AnnotationConfigApplicationContext)context).close();
		
	}

}
