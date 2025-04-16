package com.mockevaluation.book_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockevaluation.book_api.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
