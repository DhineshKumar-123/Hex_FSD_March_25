package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.dto.MessageResponseDto;
import com.springboot.rest_api.exception.InvalidContactException;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.service.CustomerService;

@RestController
public class CustomerController
{

	@Autowired
    private MessageResponseDto messageDto;
	@Autowired
	private CustomerService customerService;

//    CustomerController(MessageResponseDto messageResponseDto) {
//        this.messageResponseDto = messageResponseDto;
//    }
//	
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
	public List<Customer> getAllCustomer(@RequestParam int page, @RequestParam int size)
	{
		Pageable pageable = PageRequest.of(page, size+1);//sir put +1 but no need i think
		return customerService.getAllCustomer(pageable);
	}
	
	@GetMapping("/api/customer/getone/{id}")
	public ResponseEntity<?> getSingleCustomer(@PathVariable int id,MessageResponseDto messageDto)
	{
		try
		{
			Customer customer = customerService.getSingleCustomer(id);
			return ResponseEntity.ok(customer);
		}
		catch(InvalidIDException e)
		{
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto);
		}
//		return null;
		
	}
	/*
	 * Delete: Soft delete, hard delete 
	 * */
	@DeleteMapping("/api/customer/hard-delete/{id}")
	public ResponseEntity<?> hardDeleteCustomer(@PathVariable int id) {
		try {
			//lets validate id and if valid fetch customer object
			Customer customer = customerService.getSingleCustomer(id);
			//since it is valid here at line 63, lets delete this record 
			customerService.hardDelete(customer);
			messageDto.setBody("Customer record hard deleted from DB!!");
			messageDto.setStatusCode(200);
			return ResponseEntity.ok(messageDto);
		} catch (InvalidIDException e) {
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto); 
		}
	}
	
	@DeleteMapping("/api/customer/soft-delete/{id}")
	public ResponseEntity<?> softDelete(@PathVariable int id)
	{
		try//we are checking whether the id is present or not if not we are set the error code and message
		{
			Customer customer = customerService.getSingleCustomer(id);
			customerService.softDelete(customer);
			messageDto.setBody("Customer record Soft deleted from DB!!");
			messageDto.setStatusCode(200);
			return ResponseEntity.ok(messageDto);
		}
		catch(InvalidIDException e)
		{
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto);
		}
	}
	
	//Update Option
	
	@PutMapping("/api/customer/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody Customer newValue)//Request body is used to get the input from the user
	{
		//@pathvariable is to get and pass the variable to the service method
		try
		{
			Customer customer = customerService.getSingleCustomer(id);
			if(newValue.getName() != null)//check whether the new value given is null or not for avoiding the 500 internal error while we give the one value update 
				customer.setName(newValue.getName());
			if(newValue.getContact() != null)
				customer.setContact(newValue.getContact()); 
			customer = customerService.addCustomer(customer);
			return ResponseEntity.ok(customer);//returning the ok status
		} catch (InvalidIDException e) {
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto);//returning the error status according to our exception
		}
	}
	
	@GetMapping("/api/customer/contact")
	public ResponseEntity<?> getAllCustomersByContact(@RequestParam String contact) {
		try {
			List<Customer> list =  customerService.getAllCustomersByContact(contact);
			return ResponseEntity.ok(list); 
		} catch (InvalidContactException e) {
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto);
		}
	}
	
	@GetMapping("/api/customer/isactive")
	public List<Customer> getByIsActive(@RequestParam boolean status) {
		return customerService.getByIsActive(status); 
	}



}
