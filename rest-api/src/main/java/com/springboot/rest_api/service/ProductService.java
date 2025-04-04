package com.springboot.rest_api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public Product uploadImage(MultipartFile file, int pid) throws IOException 
	{
		/*check if pid isvalid */
		Product product = productRepository.findById(pid).orElseThrow(()->new InvalidIDException("Invalid Product ID is given"));
		
		// Setting the valid extentions for files we can change these to our wish
		List<String> allowedExtensions = Arrays.asList("jpg","jpeg","png","gif","svg");
		//Get the original file name of the uploaded image
		String originalFileName = file.getOriginalFilename();
		//Now SPlit the filename and extentions into two
		String extension = originalFileName.split("\\.")[1];//this gets the extension of file
		//Now checking that the file is correct extension or not
		if( !(allowedExtensions.contains(extension))) {
			throw new RuntimeException("Image Type Invalid");
		}
		
		// This is to specify the uploading path into our project(APP)
		String uploadPath= "C:\\Users\\dhine\\OneDrive\\Desktop\\March_Hex\\rest-api\\uploads";
		//If the mentioned path is not there we need to create the directory
		Files.createDirectories(Paths.get(uploadPath));
		//Give the fullname of the path with the foldername and also the fileName
		Path path = Paths.get(uploadPath+"\\"+originalFileName);
		//Copy the image into our local directory which was created within our Springapp
		Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
		//THe FINAL WORK IS TO SAVE THE PATH OF THIS IMAGE INTO THE DB TO CONCERN PRODUCT ID
		product.setImageUrl(path.toString());
		
		return productRepository.save(product);
	}

}
