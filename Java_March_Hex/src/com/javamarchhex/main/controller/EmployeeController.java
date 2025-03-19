package com.javamarchhex.main.controller;

import java.util.List;

import com.javamarchhex.main.model.Employee;
import com.javamarchhex.main.services.EmployeeService;

public class EmployeeController 
{
	EmployeeService employeeservice = new EmployeeService();// to access it in all methods
	public void displayEmployeeRecord()
	{
		
		List<Employee> empList  =  employeeservice.getEmployees();
		 
		for(Employee e:empList)
		{
			System.out.println(e);
		}
	}
	public void filterByBranch(String ibranch) {
		List<Employee> empList =  employeeservice.getEmployees();
		
		empList = employeeservice.filterEmployeeByBranch(empList,ibranch);
		
		for(Employee e : empList) { //[e1,e2,e3]  e=e1; e=e2; e=e3 
			System.out.println(e);
		}
	}

	public void filterByDepartment(String idepartment) {
		List<Employee> empList =  employeeservice.getEmployees();
		empList = employeeservice.filterEmployeeByDeptartment(empList,idepartment);
		
		for(Employee e : empList) { //[e1,e2,e3]  e=e1; e=e2; e=e3 
			System.out.println(e);
		}
	}

}
