package com.ecom.main.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil 
{
	private final String userdb  = "root";
	private final String password = "";
	private final String url = "jdbc:mysql://localhost:3306/e_com_db";
	private final String driver ="com.mysql.cj.jdbc.Driver";

	Connection conn;
	
	static DBUtil dbUtil;
	static {
		dbUtil = new DBUtil();
	}
	public static DBUtil getInstance()//method to return connection
	{
		return dbUtil;//returning the connection 
	}
	
	public Connection dbConnect()
	{
		try 
		{
			Class.forName(driver);//For driver Loading
//			System.out.println("Driver Loaded");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("Driver not loaded");
		}
		
		try 
		{
			conn = DriverManager.getConnection(url,userdb,password);
//			System.out.println("Connected to DB");
		} 
		catch (SQLException e) 
		{
//			e.printStackTrace();
			System.out.println("Connection Has Issue");
		}
		return conn;
	}

	public void dbClose() {
		/* Close the connection*/
		try {
			conn.close();
			System.out.println("CONNECTION CLOSED...");
		} catch (SQLException e) {
			 System.out.println(e.getMessage());	
		}
	}
	
	
}
