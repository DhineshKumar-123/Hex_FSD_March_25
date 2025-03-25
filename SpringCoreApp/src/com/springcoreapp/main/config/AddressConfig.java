package com.springcoreapp.main.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.springcoreapp.main.util"}) 

//It scans the whole project for the components

public class AddressConfig 
{
	//HEre we need to scan for the components to create the beans automatically

}
