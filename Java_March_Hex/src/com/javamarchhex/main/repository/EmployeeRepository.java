package com.javamarchhex.main.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamarchhex.main.model.Address;
import com.javamarchhex.main.model.Employee;

public class EmployeeRepository 
{
	private String userdb  = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/march_java";
	private String driver ="com.mysql.cj.jdbc.Driver";
	
	Connection con;
	
	List<Employee> empList = new ArrayList<>();// we are declaring this list outside the method for the access of this in another method
	
	
	public void dbConnect() 
	{
		/*Step 1: Load the driver */
		try 
		{
			Class.forName(driver);
//			System.out.println("DRIVER LOADED SUCCESSFULLY !!!!");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println("DRIVER LOADING FAILED!!!!");
		}
		/* Step 2: Establish connection */
		try 
		{
			con = DriverManager.getConnection(url, userdb, password);
//			System.out.println("CONNECTION ESTABLISHED SUCCESSFULLY...");
		} 
		catch (SQLException e) 
		{
			System.out.println("CONNECTION HAS ISSUE...");
		}
	}
	
	public void dbClose() {
		/* Close the connection*/
		try {
			con.close();
			System.out.println("CONNECTION CLOSED...");
		} catch (SQLException e) {
			 System.out.println(e.getMessage());	
		}
	}
	
	
	
	
	public void populateRecords()
	{
		Employee e1 = new Employee(1,"harry potter", "chennai","Finance", 89000); 
		Employee e2 = new Employee(); 
		e2.setEmpid(2);
		e2.setEmpname("ronald weasley");
		e2.setBranch("mumbai");
		e2.setDepartment("IT");
		e2.setSalary(75000);
		
		Employee e3 = new Employee(); 
		 
		empList.add(e1);
		empList.add(e2);
		empList.add(e3);
	}
	
	//////////////
	
	public List<Employee> getEmployeeList()
	{
//		populateRecords();//we are just calling the method within the class so no obj required
		
		dbConnect(); // connection started
		
		String sql = "select * from employeedetail e join address a on e.address_id = a.id ";
		List<Employee> list = new ArrayList();// For storing the values that are get from the result set and it was come from the object
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);//preparing the sql statement for implementing
			
			ResultSet rst = pstmt.executeQuery();// this executes the query and result set will point the table of contents
			
			while(rst.next()) //This will move the pointing to next rows in a table
			{
				int id = rst.getInt("empid");
				String name = rst.getString("emp_name");
				String branch = rst.getString("branch");
				String department = rst.getString("department");
				double salary = rst.getDouble("salary");
				String city = rst.getString("city");
				String pincode = rst.getString("pincode");
				int addressId = rst.getInt("id");
				
				//create obj of employee and attach the above values to it
				
				Address address = new Address(
						
						addressId,
						city,
						pincode
						);
						
				
				Employee e = new Employee(
						id,
						name,
						branch,
						department,
						salary,
						address);
				
		// This add to list is for allowing the next row value should also be saved 
				// if doesn't we add it to list it will replaced by next value fetched by the resultSet
				list .add(e);
			}
			dbClose();
			return list;
		} 
		catch (SQLException e) 
		{
			System.out.print(e.getMessage());
		}
		
		dbClose();//This closing needs to be declare before the return 
		return empList;// Here is the thing that we are returning the value which get from the list as declared outside the populate method
		
	}

	public void addEmployee(Employee employee)
	{
		  dbConnect();
		String sql1 ="insert into address values (?,?,?)";
		String sql2 = "insert into employeedetail values(?,?,?,?,?,?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, employee.getAddress().getId());
			pstmt.setString(2, employee.getAddress().getCity());
			pstmt.setString(3, employee.getAddress().getPincode());
			pstmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, employee.getEmpid());
			pstmt.setString(2, employee.getEmpname());
			pstmt.setString(3, employee.getBranch());
			pstmt.setString(4, employee.getDepartment());
			pstmt.setDouble(5, employee.getSalary());
			pstmt.setInt(6, employee.getAddress().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		dbClose();
	}

}
