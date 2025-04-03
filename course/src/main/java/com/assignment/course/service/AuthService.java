package com.assignment.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.course.exception.InvalidUserNameException;
import com.assignment.course.model.User;
import com.assignment.course.repository.AuthRepository;

@Service
public class AuthService {
	
	@Autowired
	private AuthRepository authRepository;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public User signUp(User user) throws InvalidUserNameException 
	{
		//Checking username is unique
		User user1 = authRepository.findByUsername(user.getUsername());
		if(user1!=null)
		{
			throw new InvalidUserNameException("Username Already exist!!!");
		}
		
		if(user.getRole()==null) 
			user.setRole("USER_DEFAULT");
		
		//encode the password for security purpose
		String encodedPass = bcrypt.encode(user.getPassword());
		//attach encoded password to user object
		user.setPassword(encodedPass);
		return authRepository.save(user);
	}

}
