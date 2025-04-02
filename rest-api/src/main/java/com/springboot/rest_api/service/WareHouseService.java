package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Manager;
import com.springboot.rest_api.model.WareHouse;
import com.springboot.rest_api.repository.WareHouseRepository;
@Service
public class WareHouseService {

	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	
	public WareHouse addWarehouse(WareHouse wareHouse) {
		return wareHouseRepository.save(wareHouse);
	}


	public List<WareHouse> getAllWarehouse() {
		return wareHouseRepository.findAll();
	}
	
	public WareHouse getById(int warehouseID) throws InvalidIDException{
		Optional<WareHouse> optional = wareHouseRepository.findById(warehouseID);
		if(optional.isEmpty())
			throw new InvalidIDException("WareHouse Id is Invalid!!!!!");
		return optional.get();
	}


}
