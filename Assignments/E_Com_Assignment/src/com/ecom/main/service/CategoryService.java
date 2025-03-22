package com.ecom.main.service;

import java.util.List;

import com.ecom.main.model.Category;
import com.ecom.main.repository.CategoryRepository;

public class CategoryService 
{
	CategoryRepository categoryRepository = new CategoryRepository();

	public List<Category> getCategories() {
//		categoryRepository.getCategories();
		return categoryRepository.getCategories();
		
		
	}
	

}
