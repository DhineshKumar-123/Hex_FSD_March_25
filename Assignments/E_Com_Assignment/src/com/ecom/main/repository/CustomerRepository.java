package com.ecom.main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ecom.main.model.Customer;
import com.ecom.main.utility.DBUtil;

public class CustomerRepository 
{

	public void addCustomer(Customer customer) 
	{
		Connection conn = DBUtil.getInstance().dbConnect();
		String sql1 = "insert into address values (?,?,?)";
		String sql2 = "insert into customer values (?,?,?,?)";
		
		
		try 
		{
			PreparedStatement pstmt = conn.prepareStatement(sql1) ;
			pstmt.setInt(1, customer.getAddress().getAdd_id());
			pstmt.setString(2, customer.getAddress().getCity());
			pstmt.setString(3, customer.getAddress().getPincode());
			pstmt.executeUpdate();
			System.out.println("Address Added to DB !!");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			PreparedStatement pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, customer.getCus_id());
			pstmt.setString(2, customer.getCus_name());
			pstmt.setString(3, customer.getContact());
			pstmt.setInt(4, customer.getAddress().getAdd_id());
			pstmt.executeUpdate();
			System.out.println("Customer with AddressID added to DB");
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtil.getInstance().dbClose();
		
	}

}
