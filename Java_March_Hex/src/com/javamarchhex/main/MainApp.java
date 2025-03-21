package com.javamarchhex.main;

import java.util.List;
import java.util.Scanner;

import com.javamarchhex.main.controller.EmployeeController;
import com.javamarchhex.main.controller.ProjectController;
import com.javamarchhex.main.model.Project;



public class MainApp {

	public static void main(String[] args) 
	{
		EmployeeController employeecontroller = new EmployeeController();
		ProjectController projectController = new ProjectController();
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("------MENU--------");
			System.out.println("1. Fetch all employees");
			System.out.println("2. Filter by branch,department");
			System.out.println("3. Add Employee with Address");
			System.out.println("4. Assign Project to Employee");
			System.out.println("5. Fetch Project for Employee");
			System.out.println("0. To Exit");
			int input = sc.nextInt();
			if(input ==0) {
				System.out.println("Exiting... ");
				break; 
			}
			switch(input) {
			case 1: 
				employeecontroller.displayEmployeeRecord();// Here the process begins |
				break;							//MainApp ---> Controller --> Service --> Repository --> -->Model --> DB	
			
			case 2: 
				while(true) {
					System.out.println("1. Filter by Branch Name");
					System.out.println("2. Filter by Department Name");
					System.out.println("0. To Exit");
					int op = sc.nextInt();
					if(op == 0) {
						break; 
					}
					
					switch(op) {
					case 1: 
						System.out.println("Enter branch name");
						employeecontroller.filterByBranch(sc.next());
						break; 
					
					case 2: 
						System.out.println("Enter department name");
						employeecontroller.filterByDepartment(sc.next());
						break;
					}
				}
				
				break;
			case 3:
				employeecontroller.addEmployee();
				System.out.println("Employee added with address to DB..");
				break;
				
			case 4:
				employeecontroller.displayEmployeeRecord();
				projectController.displayProjectRecord();
				employeecontroller.assignProject();
				System.out.println("Project assigned to employee..");
				break;
				
			case 5:
				//Returns the list of projects which are assigned to the given empid
				//The empid was get from user in the Controller itself
				List<Project> pList = employeecontroller.getProjectsByEmployeeId();//Calling controller method
				if(pList!=null)//checks whether returning object is null or not
					//THis is to read each values in the object and then print it 
					pList.stream().forEach(p->System.out.println(p));
				break;
				
			}
		}
		sc.close();

	}

}
