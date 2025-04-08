package com.springboot.rest_api.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.model.Review;
import com.springboot.rest_api.service.CustomerService;
import com.springboot.rest_api.service.ProductService;
import com.springboot.rest_api.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController 
{
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	
	@PostMapping("/{cid}/{pid}")//with user id
	public Review addReview(@PathVariable int cid,
						    @PathVariable int pid,
						    @RequestBody Review review) 
	{
		Customer customer  = customerService.getSingleCustomer(cid);
		Product product = productService.getById(pid);
		review.setCustomer(customer);
		review.setProduct(product);
		return reviewService.addReview(review);
	}
	
	/*get customers that have purchased by product(id: ask)*/
	@GetMapping("/v1/{pid}")
	public List<Review> getReviewsByProductId(@PathVariable int pid){
		
		return reviewService.getReviewsByProductId(pid);
	}
	/*get products that have purchased by customer(id: ask)*/
	@GetMapping("/v2/{cid}")
	public List<Review> getReviewsByCustomerId(@PathVariable int cid){
		
		return reviewService.getReviewsByCustomerId(cid); 
	}
	
	//Get all reviews
	@GetMapping("/getall-reviews")
	public List<Review> getAllReviews()
	{
		return reviewService.getAllReviews();
	}
	
	@PostMapping("/add-review/{pid}")
	public Review postReview(@PathVariable int pid,
							Principal principal,
							Review review
							)
	{
		String username =principal.getName();
		Customer customer = customerService.getByUsername(username);
		Product product = productService.getById(pid);
		review.setProduct(product);
		review.setCustomer(customer);
		boolean isBought = reviewService.checkIfProductBought(customer,product);
		if(!isBought) {
			throw new RuntimeException("Customer has not bought the product, so review not allowed");
		}
		
		return reviewService.addReview(review);
	}

}
