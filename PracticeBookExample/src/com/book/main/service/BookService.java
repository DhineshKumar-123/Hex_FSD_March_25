package com.book.main.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.book.main.factory.BookFactory;
import com.book.main.model.Book;
import com.book.main.repository.BookRepository;

@Component
public class BookService

{
	public List<Book> getBooks() {
		 BookRepository bookRepository = BookFactory.getBookRepository();
		return bookRepository.getBooks();
	}

}
