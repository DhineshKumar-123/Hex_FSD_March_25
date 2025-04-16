package com.mockevaluation.book_api.controller;



import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockevaluation.book_api.model.Author;
import com.mockevaluation.book_api.model.Book;
import com.mockevaluation.book_api.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController 
{
	@Autowired
	private BookService bookService;
	
	
	@PostMapping("/add")
	public Book addBook(@RequestBody Book book,
						Principal principal,
						@RequestBody Author author)
	{
		return bookService.addBook(book);
		
	}

}
