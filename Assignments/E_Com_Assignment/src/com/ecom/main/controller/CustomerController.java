package com.ecom.main.controller;

import java.util.Scanner;

import com.ecom.main.model.Address;
import com.ecom.main.model.Customer;
import com.ecom.main.service.CustomerService;
import com.ecom.main.utility.IdGeneratorUtil;

public class CustomerController 
{

	Scanner sc = new Scanner(System.in);
	public void addCustormer() 
	{
		System.out.println("Enter Name :");
		String name = sc.next();
		System.out.println("Enter your mobile : ");
		String mobile = sc.next();
		System.out.println("Enter your city : ");
		String city = sc.next();
		System.out.println("Enter your pincode : ");
		String pincode = sc.next();
		
		Customer customer = new Customer(name,mobile);
		Address address = new Address(city,pincode);
		
		IdGeneratorUtil id = new IdGeneratorUtil();
		
		customer.setCus_id(id.getRandomId());
		address.setAdd_id(id.getRandomId());
		
		customer.setAddress(address);
		
		
		CustomerService customerService = new CustomerService();
		customerService.addCustomer(customer);
	}
	

}
