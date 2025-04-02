package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.Manager;
import com.springboot.rest_api.model.WareHouse;
import com.springboot.rest_api.service.ManagerService;
import com.springboot.rest_api.service.WareHouseService;

@RestController
@RequestMapping("/api/warehouse")
public class WareHouseController 
{
	@Autowired
	private WareHouseService wareHouseService;
	@Autowired
	private ManagerService managerService;
	
	@PostMapping("/add/{managerID}")
	public WareHouse addWarehouse(@PathVariable int managerID,@RequestBody WareHouse wareHouse)
	{
		Manager manager = managerService.getById(managerID);
		wareHouse.setManager(manager);
		return wareHouseService.addWarehouse(wareHouse);
	}
	
	@GetMapping("/getall")
	public List<WareHouse> getAllWarehouse()
	{
		return wareHouseService.getAllWarehouse();
	}
}
