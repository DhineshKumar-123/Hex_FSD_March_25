package com.javamarchhex.main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.javamarchhex.main.model.Address;
import com.javamarchhex.main.model.Employee;
import com.javamarchhex.main.model.EmployeeProject;
import com.javamarchhex.main.model.Project;
import com.javamarchhex.main.utility.DbUtil;

public class EmployeeRepository 
{	
//	private String userDb="root";
//	private String dbPass="";
//	private String url="jdbc:mysql://localhost:3306/march_java";
//	private String driver = "com.mysql.cj.jdbc.Driver";
//	Connection con; 
	
	List<Employee> empList = new ArrayList<>();// we are declaring this list outside the method for the access of this in another method
	
//	public void dbConnect() {
//		/*Step 1: Load the driver */
//		try {
//			Class.forName(driver);
//			//System.out.println("DRIVER LOADED!!!!");
//		} catch (ClassNotFoundException e) {
//			System.out.println("DRIVER LOADING FAILED!!!!");
//		}
//		/* Step 2: Establish connection */
//		try {
//			con = DriverManager.getConnection(url, userDb, dbPass);
//			//System.out.println("CONNECTION ESTABLISHED...");
//		} catch (SQLException e) {
//			System.out.println("CONNECTION iSSUE...");
//		}
//	}
	
//	public void dbClose() {
//		/* Close the connection*/
//		try {
//			con.close();
//			//System.out.println("connection closed...");
//		} catch (SQLException e) {
//			 System.out.println(e.getMessage());	
//		}
//	}
	
	
//	public void populateRecords()
//	{
//		Employee e1 = new Employee(1,"harry potter", "chennai","Finance", 89000); 
//		Employee e2 = new Employee(); 
//		e2.setEmpid(2);
//		e2.setEmpname("ronald weasley");
//		e2.setBranch("mumbai");
//		e2.setDepartment("IT");
//		e2.setSalary(75000);
//		
//		Employee e3 = new Employee(); 
//		 
//		empList.add(e1);
//		empList.add(e2);
//		empList.add(e3);
//	}
//	
//	//////////////
	
	public List<Employee> getEmployeeList()
	{
//		populateRecords();//we are just calling the method within the class so no obj required
		
		//Here the Thing is to create the con object for using within the pstmt
		
		Connection con = DbUtil.getInstance().dbConnect(); // connection started
		System.out.println(DbUtil.getInstance());//for checking whehter it give same adddress for object
		System.out.println(DbUtil.getInstance());//creation
		
		String sql = "select * from employeedetail e join address a on e.address_id = a.id ";
		List<Employee> list = new ArrayList<>();// For storing the values that are get from the result set and it was come from the object
		
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
			DbUtil.getInstance().dbClose();
			return list;
		} 
		catch (SQLException e) 
		{
			System.out.print(e.getMessage());
		}
		
		DbUtil.getInstance().dbClose();//This closing needs to be declare before the return 
		return empList;// Here is the thing that we are returning the value which get from the list as declared outside the populate method
		
	}

	public void addEmployee(Employee employee)
	{
		Connection con = DbUtil.getInstance().dbConnect();
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
		DbUtil.getInstance().dbClose();
	}

	public void assignProject(EmployeeProject employeeProject, int empId, int projectId) {
		Connection con = DbUtil.getInstance().dbConnect();
		String sql="insert into employee_project values (?,?,?,?)";
		
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeProject.getId());
			pstmt.setInt(2, empId);
			pstmt.setInt(3, projectId);
			//convert date to string as it is varchar in DB
			pstmt.setString(4, employeeProject.getDateOfAssign().toString()); 
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.getInstance().dbClose();
	}
	
	public Optional<Employee> getEmployeeById(int eid) {
		Connection con = DbUtil.getInstance().dbConnect();
		
		System.out.println(DbUtil.getInstance());//for checking whehter it give same adddress for object
		System.out.println(DbUtil.getInstance());//creation
		
		String sql="select * from employeedetail where empid=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, eid);
			
			ResultSet rst = pstmt.executeQuery();
			if(rst.next()) {
				int id = rst.getInt("empid");
				String name = rst.getString("emp_name");
				String branch = rst.getString("branch");
				String department = rst.getString("department");
				double salary = rst.getDouble("salary");
				
				Employee e = new Employee(
						id,
						name,
						branch,
						department,
						salary);
				DbUtil.getInstance().dbClose();
				return Optional.of(e); 
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.getInstance().dbClose();
		return Optional.ofNullable(null);
	}

	public List<Project> getProjectsByEmployeeId(int eid) {
		Connection con = DbUtil.getInstance().dbConnect();
		
		/*
		 * Whenever the object is created for the connection dbconnect or dbclose 
		 * they must be in a single object address for the Memory limitation purpose
		 * it must be the best practice to do in an Enterprise level Project*/
		System.out.println(DbUtil.getInstance());//for checking whehter it give same adddress for object
		System.out.println(DbUtil.getInstance());//creation
		
		 String sql="select p.* "
		 		+ " from employeedetail e JOIN employee_project ep ON e.empid = ep.employee_id"
		 		+ " JOIN project p ON ep.project_id = p.id"
		 		+ " where e.empid=?";
		 List<Project> list = new ArrayList<>();
		 try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, eid);
			
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				Project project = new Project(
						 rst.getInt("id"),
						 rst.getString("title"),
						 rst.getInt("credits"));
				list.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 DbUtil.getInstance().dbClose();
		return list;
	}
}
		


