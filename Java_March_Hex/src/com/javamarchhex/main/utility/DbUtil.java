package com.javamarchhex.main.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil 
{

	private String userdb  = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/march_java";
	private String driver ="com.mysql.cj.jdbc.Driver";
	
	Connection con;
	
	public Connection dbConnect() 
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
		return con;
	}
	
	public void dbClose() {
		/* Close the connection*/
		try {
			con.close();
//			System.out.println("CONNECTION CLOSED...");
		} catch (SQLException e) {
			 System.out.println(e.getMessage());	
		}
	}
	

}
