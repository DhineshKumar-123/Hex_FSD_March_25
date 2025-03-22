package com.ecom.main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecom.main.model.Category;
import com.ecom.main.utility.DBUtil;

public class CategoryRepository 
{
	List<Category> catList = new ArrayList<>();
	
	public List<Category> getCategories() 
	{
		List<Category> cList = new ArrayList<>();
		
		Connection conn = DBUtil.getInstance().dbConnect();
		
		String sql = "select * from category";
		try 
		{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				int category_id = rs.getInt("cat_id");
				String category_name = rs.getString("cat_name");
				int priority = rs.getInt("priority");
				
				Category cat = new Category(category_id,category_name,priority);
				
				cList.add(cat);				
			}
			DBUtil.getInstance().dbClose();
			return cList;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		DBUtil.getInstance().dbClose();
		
		return catList;		
		
	}

}
