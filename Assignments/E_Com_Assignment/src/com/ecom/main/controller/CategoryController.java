package com.ecom.main.controller;

import java.util.List;

import com.ecom.main.model.Category;
import com.ecom.main.service.CategoryService;
import com.ecom.main.utility.DBUtil;

public class CategoryController 
{
	CategoryService categoryService = new CategoryService();

	public void getCategories() 
	{
		System.out.println(DBUtil.getInstance());
		List<Category> c = categoryService.getCategories();
		for(Category categ : c)
			System.out.println(categ);
	}
	

}
