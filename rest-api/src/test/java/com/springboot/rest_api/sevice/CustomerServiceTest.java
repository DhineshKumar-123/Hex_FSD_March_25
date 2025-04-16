package com.springboot.rest_api.sevice;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springboot.rest_api.exception.InvalidContactException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.User;
import com.springboot.rest_api.repository.CustomerRepository;
import com.springboot.rest_api.service.CustomerService;

@SpringBootTest
/*This tells Spring that we are going to use Mocking we activating that extention */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest 
{
	
	@InjectMocks//injecting the mocks for the fake outputs
	private CustomerService customerService;/* This is the service class where I want to mock my repository */

	/*This is my actual repo which i am mocking*/
	@Mock
	private CustomerRepository customerRepository;
	
	/*This i the Static variables to access it anywhere*/
	Customer c1;
	Customer c2;
	Customer c3;
	Customer c4;
	Customer c33;
	
	@BeforeEach
	public void init()
	{
		//creating fake records for the exact output purpose
		//this doesn't affect db and it must be in the form of model
		 c1 = new Customer(1,"Customer1","9866523696",true,new User(1,"Customer1@example.com","1234","CUSTOMER"));
		 c2 = new Customer(2,"Customer2","9866523696",true,new User(2,"Customer2@example.com","1234","CUSTOMER"));
		 c3 = new Customer(3,"Customer3","333333",true,new User(3,"Customer3@example.com","1234","CUSTOMER"));
		 c4 = new Customer(4,"Customer4","444444",false,new User(4,"Customer4@example.com","1234","CUSTOMER"));
		 c33 = new Customer(3,"customer3", "333333",true,new User(3,"customer3@example.com", "1234","CUSTOMER"));
	}
	
	@Test//all method should be void because no one is calling this
	public void getAllCustomerTest()
	{
		Pageable pageable = PageRequest.of(0,5);//creating the pageable first for the request of setting page size and number of content
		List<Customer> list = Arrays.asList(c1,c2,c3);//preparing the list of customer 
		Page<Customer> page = new PageImpl<>(list);//adding to the fake output
		
		/* I am telling Spring to return this page having list of 
		 * 3 objects c1,c2,c3 whenever it encounters 
		 * customerRepository.findAll(pageable) */
		
		when(customerRepository.findAll(pageable)).thenReturn(page);
		/* I do mocking because I do not want to rely on DB records 
		 * as my test case will fail if DB records were to be deleted */
		//Now we are checking the assertions
		//UseCase - 1 -- Call time - 1
		assertEquals(3, customerService.getAllCustomer(pageable).size());
		
		list = Arrays.asList(c1,c2,c4); 
		page = new PageImpl<>(list); //fake output
		when(customerRepository.findAll(pageable)).thenReturn(page);
		/*This 2 as expected is because I am filtering out 'inactive' customers 
		 * and c4 customer in the list is in-active. hence 2 is correct expectation */
		
		//UseCase -2 -- Call time - 2
		assertEquals(2, customerService.getAllCustomer(pageable).size());
		
		verify(customerRepository,times(2)).findAll(pageable);//checking how many times the customerRepository is called
	}
	
	@Test
	public void addCustomerTest()
	{
		when(customerRepository.save(c3)).thenReturn(c33);
		//use case - 1;
		assertEquals(c33, customerService.addCustomer(c3));//dummy addition within our dummy records 
		
		verify(customerRepository,times(1)).save(c3);
		
	}

	@Test
	public void getAllCustomersByContactTest() throws InvalidContactException
	{
		//use case -1 valid contact given and users(list) find
		//expectation : List<Customer> list = Arrays.asList(c1,c2); 
		//actual: customerService.getAllCustomersByContact(9866523696)
		
		List<Customer> list = Arrays.asList(c1,c2);
		String contact = "9866523696";//storing contact in a string
		when(customerRepository.findByContact(contact)).thenReturn(list);
		assertEquals(list, customerService.getAllCustomersByContact(contact));
		
		//use case 2: contact invalid
		
		contact ="85475648";
		try {
			assertEquals(list,customerService.getAllCustomersByContact(contact));
		} catch (InvalidContactException e) {
			assertEquals("Contact number invalid and must be 10 digits!!!", e.getMessage());
		}	
	}
	
	@Test
	public void getByIsActiveTest()
	{
		//use case -1 valid isActive given and users(list) find
				//expectation : List<Customer> list = Arrays.asList(c1,c2,c3,c33); 
				//actual: customerService.getByIsActive(true)
		List<Customer> list = Arrays.asList(c1,c2,c3,c33);
		when(customerRepository.findByIsActive(true)).thenReturn(list);
		assertEquals(list, customerService.getByIsActive(true));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
