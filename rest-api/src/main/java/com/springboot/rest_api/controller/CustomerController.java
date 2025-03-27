package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.service.CustomerService;

@RestController
public class CustomerController
{
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/api/customer/hello")//Just for get or show the details
	public String sayHello()
	{
		return "Hello from the First api in Springboot !!!!!!!";
	}
	
	@PostMapping("/api/customer/add")//it is to send the details to db which are come from postman via this api
	public Customer addCustomer(@RequestBody Customer customer)//this @requestbody is to get the request and give it to the Customer Model
	{
		//From this method we can able to read the data given by end-user
//		System.out.println(customer.getName());
//		System.out.println(customer.getContact());
		return customerService.addCustomer(customer);
	}
	
	@GetMapping("/api/customer/getall")
	public List<Customer> getAllCustomer()
	{
		return customerService.getAllCustomer();
	}
	
	@GetMapping("/api/customer/getone/{id}")
	public Customer getSingleCustomer(@PathVariable int id)
	{
		try
		{
			Customer customer = customerService.getSingleCustomer(id);
			return customer;
		}
		catch(InvalidIDException e)
		{
			
		}
		return null;
		
	}

}
