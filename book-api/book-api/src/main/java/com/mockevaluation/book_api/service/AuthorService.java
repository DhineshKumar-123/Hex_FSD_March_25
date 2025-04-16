package com.mockevaluation.book_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockevaluation.book_api.model.Author;
import com.mockevaluation.book_api.repository.AuthorRepository;

@Service
public class AuthorService 
{

	@Autowired
	private AuthorRepository authorRepository;
	public Author addAuthor(Author author) 
	{
		return authorRepository.save(author) ;
	}

}
