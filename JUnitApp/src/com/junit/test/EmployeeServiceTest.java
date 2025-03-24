package com.junit.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.junitapp.main.model.Employee;
import com.junitapp.main.service.EmployeeService;
import java.util.*;

public class EmployeeServiceTest 
{
	EmployeeService employeeService = new EmployeeService();
	
	@Test
	public void getAllEmployeesTest()
	{
		Employee e1 = new Employee(1,"harry","mumbai","IT",98000);
		Employee e2 = new Employee(2,"ronald","chennai","IT",89000);
		Employee e3 = new Employee(3,"hermione","mumbai","FIANNCE",130000);
		
		List<Employee> list = Arrays.asList(e1,e2,e3);
		/*
		 * List<Employee> expected = list; List<Employee> actual =
		 * employeeService.getAllEmployees();
		 */ 
		Assert.assertEquals(list, employeeService.getAllEmployees());
	}
	
	@Test
	public void filterListByBranchTest()
	{
		
		Employee ee1 = new Employee(1,"harry","mumbai","IT",98000);
		Employee ee2 = new Employee(2,"ronald","chennai","IT",89000);
		Employee ee3 = new Employee(3,"hermione","mumbai","FIANNCE",130000);
		
		List<Employee> wholelist = Arrays.asList(ee1,ee2,ee3);
		
		//use case 1: branch mumbai
		Employee e1 = new Employee(1,"harry","mumbai","IT",98000);
		Employee e3 = new Employee(3,"hermione","mumbai","FIANNCE",130000);
		List<Employee> list1 = Arrays.asList(e1,e3);
		
		//use case 2: branch chennai
		Employee e2 = new Employee(2,"ronald","chennai","IT",89000);
		List<Employee> list2 = Arrays.asList(e2);
		Assert.assertEquals(list1, employeeService.filterListByBranch(wholelist,"mumbai"));
		Assert.assertEquals(list2,employeeService.filterListByBranch(wholelist,"chennai"));
		
		//use case 3 : branch Null
//		assertNotNull(employeeService);
//		Assert.assertThrows(NullPointerException.class,()->.filterListByBranch(employeeService.filterListByBranch(),null));
	}

}
