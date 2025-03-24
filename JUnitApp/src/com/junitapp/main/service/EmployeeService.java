package com.junitapp.main.service;

import java.util.List;

import com.junitapp.main.model.Employee;
import com.junitapp.main.repository.EmployeeRepository;

public class EmployeeService 
{
	EmployeeRepository employeeRepository = new EmployeeRepository();
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployee();
	}
	
	public List<Employee> filterListByBranch(List<Employee> list, String branch) {
		return list.stream()
				.filter((e)->e.getBranch().equals(branch))
				.toList();
	}
	
	public List<String> getEmployeeName(List<Employee> list) {
		return  list.stream()
					.map(e->e.getName())
					.toList();
		/* @Test
	public void getEmployeeName() {
 
		Employee e1 = new Employee(1, "harry", "mumbai", "IT", 98000);
		Employee e2 = new Employee(2, "ronald", "chennai", "IT", 89000);
		Employee e3 = new Employee(3, "hermione", "mumbai", "FIANNCE", 130000);
 
		List<Employee> list = Arrays.asList(e1, e2, e3);
 
		List<String> list1 = Arrays.asList(e1.getName(), e2.getName(), e3.getName());
		Assert.assertEquals(list1, employeeService.getEmployeeName(list));
	}*/
	}

}
