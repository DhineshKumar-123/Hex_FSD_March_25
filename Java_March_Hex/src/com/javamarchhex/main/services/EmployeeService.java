package com.javamarchhex.main.services;

import java.util.List;
import java.util.stream.Stream;

import com.javamarchhex.main.model.Employee;
import com.javamarchhex.main.repository.EmployeeRepository;

public class EmployeeService {

	public List<Employee> getEmployees()
	{
		EmployeeRepository employeerepository = new EmployeeRepository();
		return employeerepository.getEmployeeList();
	}
	
	public List<Employee> filterEmployeeByBranch(List<Employee> empList,String ibranch) {
		//convert list to stream
		Stream<Employee> empStream =  empList.parallelStream(); //20 records
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		//apply the filter
		empStream = empStream
						.filter(e-> e.getBranch().equalsIgnoreCase(ibranch)); //this criteria in filter must evaluate to true or false		
		
		//recovert to List 
		empList =  empStream.toList();
		return empList;
	}

	public List<Employee> filterEmployeeByDeptartment(List<Employee> empList, String idepartment) {
		//convert list to stream + apply the filter + recovert to List 
		return empList.parallelStream()
					.filter(e->e.getDepartment().equalsIgnoreCase(idepartment))
					.toList(); 
	}

	

}
