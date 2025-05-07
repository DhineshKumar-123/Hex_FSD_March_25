package com.springboot.rest_api.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.rest_api.dto.BarChartDto;
import com.springboot.rest_api.dto.MessageResponseDto;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Category;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.model.Vendor;
import com.springboot.rest_api.model.WareHouse;
import com.springboot.rest_api.service.CategoryService;
import com.springboot.rest_api.service.ProductService;
import com.springboot.rest_api.service.VendorService;
import com.springboot.rest_api.service.WareHouseService;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private WareHouseService wareHouseService;
	@Autowired
	private MessageResponseDto dto;

	//while the product is adding we are not sure about the image to be uploaded
		//this is because if the image uploading is fails the total product add will get collapsed
		//so that we are creating the new api for image or file uploading
		// The product is added using the category and vendor id and also now warehouse id
	@PostMapping("/add/{catid}/{wid}")//instead of getting vendor id manually we find it by using username given during the login
	public Product addProduct(@PathVariable int catid, 
							  Principal principal, 
							  @PathVariable int wid,
							  @RequestBody Product product)// here
																												// product
	/* we need to check whether the category is valid or not */
	/* Then also we need to check the vendor id */
	{
		// Checking of category id 
		// instead of using try catch we are using the Global Exception handler
		Category category = categoryService.getById(catid);
//			return ResponseEntity.ok(category);
		Vendor vendor = vendorService.getByUsername(principal.getName());
		
		WareHouse warehouse = wareHouseService.getById(wid);
		// Attach these obj of category and vendor into product model
		product.setCategory(category);
		product.setVendor(vendor);
		product.setWareHouse(warehouse);

		/// Save the product object into the db
		
		return productService.addProduct(product);

	}

	@GetMapping("/category/{catId}")//for display when filter is applied to our app
	public ResponseEntity<?> getProductByCategory(@PathVariable int catId, @RequestParam int page,
			@RequestParam int size) {

		//for page creation how many products should be in 0th page or etc
		Pageable pageable = PageRequest.of(page, size);
		try {
			//list cretion if the method in service is workig fine
			List<Product> list = productService.getProductByCategory(catId, pageable);
			return ResponseEntity.ok(list);
		} catch (InvalidIDException e) {
			dto.setBody(e.getMessage());
			dto.setStatusCode(400);
			return ResponseEntity.status(400).body(dto);
		}

	}

	@GetMapping("/vendor/{vid}")//For this we are not using the pageable because we need to show all the products in a single page for a logged in vendor
	public ResponseEntity<?> getProductByVendor(@PathVariable int vid) 
	{
		try {
			List<Product> list = productService.getProductByVendor(vid);
			return ResponseEntity.ok(list);
		} catch (InvalidIDException e) {
			dto.setBody(e.getMessage());
			dto.setStatusCode(400);
			return ResponseEntity.status(400).body(dto);
		}
	}
	
	@PostMapping("/image/upload/{pid}")//pid is product ID
	public Product uploadImage(@PathVariable int pid,
			 					@RequestParam MultipartFile file) throws IOException, InvalidIDException// This is for the use of file handling
	{
		return productService.uploadImage(file,pid);
	}
	
	@GetMapping("/bar-chart")
	public BarChartDto getBarChartData() {
		return productService.getBarChartData();
	}
	
	
	
}
