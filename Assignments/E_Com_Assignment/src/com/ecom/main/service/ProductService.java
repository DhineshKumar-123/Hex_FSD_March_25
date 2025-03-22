package com.ecom.main.service;

import com.ecom.main.model.Product;
import com.ecom.main.repository.ProductRepository;

public class ProductService {

	public void addProducts(Product product) {
		ProductRepository productRepository = new ProductRepository();
		productRepository.addProducts(product);
		System.out.println("Given products from controller are sent to repository through service successfully!");
		
	}

}
