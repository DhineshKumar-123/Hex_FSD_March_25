package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Category;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.model.Vendor;
import com.springboot.rest_api.repository.CategoryRepository;
import com.springboot.rest_api.repository.ProductRepository;
import com.springboot.rest_api.repository.VendorRepository;
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private VendorRepository vendorRepository;
	
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	public List<Product> getProductByCategory(int catId, Pageable pageable) {
		Optional<Category> optional = categoryRepository.findById(catId);
		if(optional.isEmpty())
			throw new InvalidIDException("Category ID Invalid.."); 
		 
		return productRepository.getProductByCategoryId(catId,pageable);
	}
	public List<Product> getProductByVendor(int vid) {
		Optional<Vendor> optional = vendorRepository.findById(vid);
		if(optional.isEmpty())
			throw new InvalidIDException("Vendor ID Invalid.."); 
		 
		return productRepository.getProductByVendorId(vid);
	}
	public Product getById(int pid) throws InvalidIDException{
		Optional<Product> optional = productRepository.findById(pid);
		if(optional.isEmpty())
			throw new InvalidIDException("Product Id is Invalid!!!!!");
		return optional.get();
	}

}
