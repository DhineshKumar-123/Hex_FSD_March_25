package com.springboot.rest_api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.config.JwtUtil;
import com.springboot.rest_api.dto.TokenDto;
import com.springboot.rest_api.exception.InvalidUsernameException;
import com.springboot.rest_api.model.User;
import com.springboot.rest_api.service.AuthService;
import com.springboot.rest_api.service.MyUserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController 
{
	@Autowired
	private AuthService authService;
	
	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signup")
	public User signUp(@RequestBody User user) throws InvalidUsernameException//this exception will show the enduser that this username already exists
	{
		return authService.signUp(user);		
	}
	
	@GetMapping("/login")
	public UserDetails login(Principal principal)//we are redirecting the user logged in to their concerned dashboard according to their role
	{
		/* Make this login as Authenticated API 
		 * If this method is called, it means that Spring Filter already
		 * has correct username/password
		 * 
		 * Can i ask spring filter to share these username and password  with me?
		 * -- yes but only username, spring filter never ever shares user-password 
		 * */
		String username = principal.getName();
		return myUserService.loadUserByUsername(username);
	}
	
	@PostMapping("/token/generate")
	public TokenDto generatedToken(@RequestBody User user, TokenDto dto)
	{
		/*Step 1. Build authentication ref based on username,passord given*/
		Authentication auth = 
		new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
	
		authenticationManager.authenticate(auth);//This is the person who checks the credentials are correct or not
		
		/*Step 2: Generate the token since we know that credentials are correct */
		String token =  jwtUtil.generateToken(user.getUsername()); 
		dto.setToken(token);
		dto.setUsername(user.getUsername());
		dto.setExpiry(jwtUtil.extractExpiration(token).toString());
		return dto; //This dto gets the token,username and expiry timings
	}
	@GetMapping("/user/details")
	public UserDetails getUserDetails(Principal principal) 
	{
		String username = principal.getName();//this is for getting username
		return myUserService.loadUserByUsername(username);
	}
}
