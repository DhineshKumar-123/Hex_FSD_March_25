package com.javamarchhex.main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javamarchhex.main.model.Project;
import com.javamarchhex.main.utility.DbUtil;

public class ProjectRepository
{
//	DbUtil dbUtil = new DbUtil();

	public List<Project> fetchAllProjects() {
		Connection con = DbUtil.getInstance().dbConnect();
		String sql="select * from project";
		List<Project> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst =  pstmt.executeQuery();
			while(rst.next()) {
				Project project = new Project(
						 rst.getInt("id"),
						 rst.getString("title"),
						 rst.getInt("credits"));
				list.add(project);
				
			}
			DbUtil.getInstance().dbClose();
			return list; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DbUtil.getInstance().dbClose();
		return null;
	}

}
