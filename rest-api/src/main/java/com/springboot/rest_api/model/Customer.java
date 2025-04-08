package com.springboot.rest_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity

public class Customer //This automatically takes as the table name
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;//findbyid is the inbuilt method in springboot
	@Column(nullable = false)
	private String name;//findbyname we should declare in the repository
	@Column(nullable = false)
	private String contact;//same as findbyname
	
	private boolean isActive=true;
	
	@OneToOne
	private User user;
	
	public Customer(String name, String contact, User user) {
		super();
		this.name = name;
		this.contact = contact;
		this.user = user;
	}
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}
