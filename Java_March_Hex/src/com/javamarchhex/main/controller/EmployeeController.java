package com.javamarchhex.main.controller;

import java.util.List;
import java.util.Scanner;

import com.javamarchhex.main.model.Address;
import com.javamarchhex.main.model.Employee;
import com.javamarchhex.main.services.EmployeeService;

public class EmployeeController 
{
	EmployeeService employeeservice = new EmployeeService();// to access it in all methods and it is main to implement the 
	// db operations in repository
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
	public void addEmployee() 
	{
		/*Take input from User */
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name");
		String name = sc.next();
		System.out.println("Enter branch");
		String branch = sc.next();
		System.out.println("Enter department");
		String department = sc.next();
		System.out.println("Enter salary");
		double salary = sc.nextDouble();
		System.out.println("Enter city");
		String city = sc.next();
		System.out.println("Enter pincode");
		String pincode = sc.next();
		
		// give it to the Object of employee for setting the input to the model instance variables
		Employee employee =  new Employee(name,branch,department,salary);
		Address address = new Address(city,pincode);
		
		/*We are mannualy Generate random primary keys for employee id and address id  */
		double random = Math.random() * 10000000; // 0.00  0.99  * 10000000
		int addressId = (int) random; 
		
		random = Math.random() * 10000000; 
		int empId = (int) random; 
		
		// Setting the generated id to the id present in the address table
		address.setId(addressId);
		employee.setEmpid(empId);
		
		// Adding or Connect the address to Employee models
		//Because the Employee model contains the Address so we are attaching the value of address to
		//the instance variable present in the Employee Model
		employee.setAddress(address);
		
		// Now we create a service for adding employee
		// So we are calling the addEmployee Method present inside the EmployeeService Class with EmployeeService Object
		
		employeeservice.addEmployee(employee);
	}
}
