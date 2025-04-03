package com.assignment.course.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.assignment.course.service.MyUserService;

@Configuration
@EnableWebSecurity
public class AuthConfig
{
	
	@Autowired
	private MyUserService myUserService;
	@Bean//this will taken from the official docs of the spring
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception //we are applying the chain for the filter which protects our spring app
	{
		http
			.csrf(csrf->csrf.disable())/*This will disable the csrf(cross site refernece forgery)
			 							when the user tries to send the post request spring app
			 							will block them so we are disabling it*/
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/course/public/welcome").permitAll()//This permitAll Will permits all the users
				.requestMatchers("/api/course/private/welcome").authenticated()//while this will only permits the authorized users
				.requestMatchers("/api/signup").permitAll()//This enables the new user to access the signup page without any authentication
				.anyRequest().authenticated()//if any other requests are given from the unauthorized on it will shows the 401 unauthorized error to them
			)
			.authenticationProvider(getAuth())
			/* Activating basic Auth */
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}

	@Bean
	AuthenticationProvider getAuth()//
	/*this willautomatically check the db for username and pasword 
	 * then it will allow us to see the output.
	 * We are adding more details for the authentication provider to provide the appropriate authentication
	 */
	{
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passEncoder());
		dao.setUserDetailsService(myUserService);	
		return dao;
	}
	@Bean
	BCryptPasswordEncoder passEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
