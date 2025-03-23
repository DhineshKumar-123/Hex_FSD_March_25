package com.ecom.main.model;

public class Customer 
{
	private int cus_id;
	private String cus_name;
	private String contact;
	Address address;
	
	public Customer(String cus_name, String contact) {
		super();
		this.cus_name = cus_name;
		this.contact = contact;
	}
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int cus_id, String cus_name, String contact, Address address) {
		super();
		this.cus_id = cus_id;
		this.cus_name = cus_name;
		this.contact = contact;
		this.address = address;
	}
	
	public Customer(String cus_name, String contact, Address address) {
		super();
		this.cus_name = cus_name;
		this.contact = contact;
		this.address = address;
	}

	public Customer(int cus_id, String cus_name, String contact) {
		super();
		this.cus_id = cus_id;
		this.cus_name = cus_name;
		this.contact = contact;
	}

	public int getCus_id() {
		return cus_id;
	}

	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	@Override
	public String toString() {
		return "Customer [cus_id=" + cus_id + ", cus_name=" + cus_name + ", contact=" + contact + ", address=" + address
				+ "]";
	}

}
