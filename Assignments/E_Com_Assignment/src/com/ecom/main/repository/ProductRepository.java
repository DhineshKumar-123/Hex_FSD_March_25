package com.ecom.main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ecom.main.model.Product;
import com.ecom.main.utility.DBUtil;

public class ProductRepository {

	public void addProducts(Product product) 
	{
		Connection conn = DBUtil.getInstance().dbConnect();
		String sql = "insert into product(prod_title,price,quantity,cate_id) values (?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,product.getProd_title());
			pstmt.setDouble(2, product.getPrice());
			pstmt.setInt(3, product.getQuantity());
			pstmt.setInt(4, product.getCate_id());
			pstmt.executeUpdate();
			System.out.println("Product Inserted Successfully to DB");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		DBUtil.getInstance().dbClose();
	}


}
