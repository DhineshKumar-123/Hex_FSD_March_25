package com.assignment.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.course.exception.InvalidUserNameException;
import com.assignment.course.model.User;
import com.assignment.course.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController 
{
	@Autowired
	private AuthService authService;
	@PostMapping("/signup")
	public User signUp(@RequestBody User user) throws InvalidUserNameException
	{
		return authService.signUp(user);
	}

}
