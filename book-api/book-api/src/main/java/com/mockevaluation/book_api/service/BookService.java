package com.mockevaluation.book_api.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockevaluation.book_api.model.Book;
import com.mockevaluation.book_api.repository.BookRepository;

@Service
public class BookService 
{

	@Autowired
	private BookRepository bookRepository;

	public Book addBook(Book book)
	{
		return bookRepository.save(book);
	}
	

}
