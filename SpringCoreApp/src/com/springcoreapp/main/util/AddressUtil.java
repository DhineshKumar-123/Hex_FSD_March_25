package com.springcoreapp.main.util;

import org.springframework.stereotype.Component;

@Component 


public class AddressUtil 
{
	//This is Our method or the working thing 

	public String getCity(String address) { //street-name address-line city pincode country 
		String[] addr = address.split(" "); 
		return addr[2];

}
}
