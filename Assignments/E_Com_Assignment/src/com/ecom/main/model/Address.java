package com.ecom.main.model;

public class Address 
{
	private int add_id;
	private String city;
	private String pincode;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(int add_id, String city, String pincode) {
		super();
		this.add_id = add_id;
		this.city = city;
		this.pincode = pincode;
	}
	public Address(String city, String pincode) {
		super();
		this.city = city;
		this.pincode = pincode;
	}
	public int getAdd_id() {
		return add_id;
	}
	public void setAdd_id(int add_id) {
		this.add_id = add_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Address [add_id=" + add_id + ", city=" + city + ", pincode=" + pincode + "]";
	}
	
	

}
