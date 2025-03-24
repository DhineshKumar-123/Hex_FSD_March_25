package com.ecom.main;

import java.util.Scanner;

import com.ecom.main.controller.CategoryController;
import com.ecom.main.controller.CustomerController;
import com.ecom.main.controller.ProductController;
import com.ecom.main.utility.DBUtil;

public class MainApp 
{
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		
		
		
		System.out.println("Welcome to my E-Com");
		while(true)
		{
			System.out.println("1. Fetch all categories");
			System.out.println("2. Add product with category info in DB");
			System.out.println("3. Add Customer with Address infor in DB");
			System.out.println("4. Exit");
			int input = sc.nextInt();
//			sc.close();sc.close();
			if(input==4)
			{
				System.out.println("Exiting");
				break;
			}
			switch(input)
			{
			case 1:
				CategoryController categoryController = new CategoryController();
				categoryController.getCategories();
				System.out.println("The Object Creation for Connection Happened Here : "+DBUtil.getInstance());
//				System.out.println(DBUtil.getInstance());
				break;
			case 2:
				ProductController productController = new ProductController();
				productController.addProduct();
				break;
			case 3:
				CustomerController customerController = new CustomerController();
				customerController.addCustormer();
				break;
				
				
			}
			
		}
		
	
	}

}
