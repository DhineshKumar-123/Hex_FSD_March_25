package com.ecom.main.controller;

import java.util.Scanner;

import com.ecom.main.model.Product;
import com.ecom.main.service.ProductService;

public class ProductController 
{
	Scanner sc = new Scanner(System.in);
	public void addProduct() 
	{
		System.out.println("Enter Product Name: ");
		String productname = sc.next();
		System.out.println("Enter Product Price: ");
		double productprice = sc.nextDouble();
		System.out.println("Enter the Product Quantity: ");
		int productquantity = sc.nextInt();
		CategoryController categoryController = new CategoryController();
		categoryController.getCategories();
		System.out.println("Choose and Enter the Category ID of your Product: ");
		int categoryid = sc.nextInt();
		System.out.println("Inputs are Got Successfully from Cotroller!!");
		
		Product product = new Product(productname,productprice,productquantity,categoryid);
		
		ProductService productService = new ProductService();
		productService.addProducts(product);
		
	}
	
}
