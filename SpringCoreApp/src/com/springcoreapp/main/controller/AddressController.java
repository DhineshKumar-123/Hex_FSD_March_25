package com.springcoreapp.main.controller;

import com.springcoreapp.main.util.AddressUtil;

public class AddressController {

	AddressUtil addressUtil;

	public AddressController(AddressUtil addressUtil) {
		super();
		this.addressUtil = addressUtil;
	}

	public String getCity() {

		String city = addressUtil.getCity("101,kings-lane some-address mumbai 459798 india");
		return city;
	}

}
