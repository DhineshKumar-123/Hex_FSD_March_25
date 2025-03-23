package com.ecom.main.service;

import com.ecom.main.model.Customer;
import com.ecom.main.repository.CustomerRepository;

public class CustomerService 
{

	public void addCustomer(Customer customer) 
	{
		CustomerRepository customerRepository = new CustomerRepository();
		customerRepository.addCustomer(customer);
		
	}

}
