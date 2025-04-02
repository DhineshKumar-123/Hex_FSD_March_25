package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Manager;
import com.springboot.rest_api.repository.ManagerRepository;
@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;
	
	public Manager addManager(Manager manager) {
		return managerRepository.save(manager);
	}

	public List<Manager> getAllManager() {
		return managerRepository.findAll();
	}
	public Manager getById(int managerID) throws InvalidIDException{
		Optional<Manager> optional = managerRepository.findById(managerID);
		if(optional.isEmpty())
			throw new InvalidIDException("Manager Id is Invalid!!!!!");
		return optional.get();
	}

}
