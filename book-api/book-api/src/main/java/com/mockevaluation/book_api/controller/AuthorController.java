package com.mockevaluation.book_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockevaluation.book_api.model.Author;
import com.mockevaluation.book_api.service.AuthorService;

@RestController
@RequestMapping("/api/author")
public class AuthorController
{
	@Autowired
	private AuthorService authorService;
	
	@PostMapping("/add")
	public Author addAuthor(@RequestBody Author author)
	{
		return authorService.addAuthor(author);
	}

}
