package com.springcoreapp.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcoreapp.main.config.AddressConfig;
import com.springcoreapp.main.controller.AddressController;
import com.springcoreapp.main.util.AddressUtil;

public class Main 
{
	public static void main(String[] args) 
	{
		/*
		 * Hey SPring!!, i am telling you to load AppConfig that has component scan
		 * */
		ApplicationContext context = new AnnotationConfigApplicationContext(AddressConfig.class);
		
//		Calling the controller to perform the getting functions
		
		AddressController addressController = new AddressController(context.getBean(AddressUtil.class));
		System.out.println(addressController.getCity());
		
		((AnnotationConfigApplicationContext)context).close();
		
	}

}
