package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.Category;
import com.springboot.rest_api.service.CategoryService;

@RestController
@RequestMapping("/api/category")//this is the base url part
public class CategoryController 
{
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public Category addCategory(@RequestBody Category category)
	{
		return categoryService.addCategory(category);
	}
	
	@GetMapping("/getall")
	public List<Category> getAllCategory()
	{
		return categoryService.getAllCategory();
	}
	

}
