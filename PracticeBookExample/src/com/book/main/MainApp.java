package com.book.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.book.main.config.BookConfig;
import com.book.main.controller.BookController;
import com.book.main.factory.BookFactory;
import com.book.main.model.Book;

public class MainApp 
{
	public static void main(String[] args) 
	{
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(BookConfig.class);
		BookController bookController = BookFactory.getBookController(); 
		List<Book> list =  bookController.getBooks();
		for(Book b : list) {
			System.out.println(b);
		}
		((AnnotationConfigApplicationContext)context).close();
	}

}
