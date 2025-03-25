package com.book.main.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.book.main.config.BookConfig;
import com.book.main.controller.BookController;
import com.book.main.repository.BookRepository;
import com.book.main.service.BookService;

public class BookFactory 
{
	static ApplicationContext context = 
			new AnnotationConfigApplicationContext(BookConfig.class);

	public static BookController getBookController(){
		return context.getBean(BookController.class);
	}
	
	public static BookService getBookService(){
		return context.getBean(BookService.class);

		}
	

	public static BookRepository getBookRepository(){
		return context.getBean(BookRepository.class);

	}
}
