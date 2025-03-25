package com.springcoreapp.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springcoreapp.main.util.DbUtil;
import com.springcoreapp.main.utility.MyUtil;

@Configuration
public class AppConfig //This file is to manage the object creation in a single memory location
{
	@Bean
	public MyUtil getMyUtilInstance()
	{
		return  new MyUtil();
	}
	@Bean
	public DbUtil getDbUtilInstance()
	{
		return new DbUtil();
	}

}
