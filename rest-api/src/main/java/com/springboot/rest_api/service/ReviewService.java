package com.springboot.rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.Review;
import com.springboot.rest_api.repository.ReviewRepository;
@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	public Review addReview(Review review) 
	{
		
		return reviewRepository.save(review);
	}
	public List<Review> getReviewsByProductId(int pid)
	{
		return reviewRepository.findReviewsByProductId(pid);
	}
	public List<Review> getReviewsByCustomerId(int cid) {
		return reviewRepository.findReviewsByCustomerId(cid);
	}
	

}
