package com.mockevaluation.book_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mockevaluation.book_api.model.User;

public interface AuthRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
