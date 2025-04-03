package com.assignment.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.course.model.User;

public interface AuthRepository extends JpaRepository<User, Integer>
{
	User findByUsername(String username);
}
