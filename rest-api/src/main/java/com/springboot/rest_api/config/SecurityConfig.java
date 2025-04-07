package com.springboot.rest_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.rest_api.service.MyUserService;



@Configuration
@EnableWebSecurity
public class SecurityConfig
{
	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean//this will taken from the official docs of the spring
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception //we are applying the chain for the filter which protects our spring app
	{
		http
			.csrf(csrf->csrf.disable())/*This will disable the csrf(cross site refernece forgery)
			 							when the user tries to send the post request spring app
			 							will block them so we are disabling it*/
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/auth/token/generate").permitAll()	
				.requestMatchers("/api/auth/user/details").authenticated()
				.requestMatchers("/api/customer/public/hello").permitAll()//This permitAll Will permits all the users
				.requestMatchers("/api/customer/private/hello").authenticated()//while this will only permits the authorized users
				.requestMatchers("/api/auth/signup").permitAll()//This enables the new user to access the signup page without any authentication
				.requestMatchers("/api/auth/login").authenticated()
				.requestMatchers("/api/product/image/upload/{pid}").hasAnyAuthority("VENDOR","ADMIN")
				.anyRequest().authenticated()//if any other requests are given from the unauthorized on it will shows the 401 unauthorized error to them
				
					
				)
		.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
		
		;
		return http.build();
	}
//	@Bean//We are need to move for the fetching of username and password in the db that is next step
//	UserDetailsService userDetailsService()//this is to set the username and password for the authentication while we are using the postman to test our api's 
//	{
//		
//		User user1 = (User) User.withUsername("harry")//instead of fetching details from db
//						.password("{noop}potter")//we are using this dummy values for calling api
//						.roles("USER_DEFAULT")
//						.build();
//		
//		User user2 = (User) User.withUsername("john")
//						.password("{noop}doe")
//						.roles("USER_DEFAULT")
//						.build();
//		 
//		return new InMemoryUserDetailsManager(user1,user2);
//	}
	/*Instead of the manual loading the authenticated users like above 
	 * we use the db to fetch the username and password for authenticate*/
	
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
	BCryptPasswordEncoder passEncoder()/*This is to Encrypt the password into hashcode manner
	 									to protect our privacy instead of storing the 
	 									password as a plain text*/ 
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception {
		  return auth.getAuthenticationManager();
	 }
}
