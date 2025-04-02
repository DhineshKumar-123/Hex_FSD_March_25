package com.springboot.rest_api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.CustomerProduct;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.service.CustomerProductService;
import com.springboot.rest_api.service.CustomerService;
import com.springboot.rest_api.service.ProductService;

@RestController
@RequestMapping("/api/customer/product")
public class CustomerProductController
{

	@Autowired
	private CustomerProductService customerProductService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;


	@PostMapping("/purchase/{cid}/{pid}")
	public CustomerProduct purchaseProduct(@PathVariable int cid,
								@PathVariable int pid,
								@RequestBody CustomerProduct customerProduct) throws InvalidIDException
	{
		/* fetch customer with id if not throw InvalidID exception*/
		Customer customer = customerService.getSingleCustomer(cid);
		/* fetch product with id if not throw InvalidID exception*/
		Product product = productService.getById(pid);
		/*Attach these to the customerProduct object */
		//We are using the current date
		customerProduct.setCustomer(customer);
		customerProduct.setProduct(product);
		if(customerProduct.getDateofpurchase()==null)
			customerProduct.setDateofpurchase(LocalDate.now());
		
		/*Save them to the customerProduct table in db*/
		return customerProductService.addCustomerProduct(customerProduct);
	}
	/*get customers that have purchased by product(id: ask)*/
	@GetMapping("/v1/{pid}")
	public List<Customer> getCustomerByProductId(@PathVariable int pid){
		
		return customerProductService.getCustomerByProductId(pid);
	}
	/*get products that have purchased by customer(id: ask)*/
	@GetMapping("/v2/{cid}")
	public List<Product> getProductByCustomerId(@PathVariable int cid){
		
		return customerProductService.getProductByCustomerId(cid); 
	}
	
}
