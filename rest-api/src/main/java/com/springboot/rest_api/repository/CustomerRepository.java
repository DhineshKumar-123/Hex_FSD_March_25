package com.springboot.rest_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	//save() method is the prebuild method in jpa
	//findAll() is to fetch all the details in the db
	
	
	//List<Customer> findByName(String name);
	List<Customer> findByContact(String contact);//this is the userdefined method
	List<Customer> findByIsActive(boolean status);
	Customer findByUserUsername(String username);
}
