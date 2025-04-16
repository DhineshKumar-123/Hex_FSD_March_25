package com.mockevaluation.book_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockevaluation.book_api.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
