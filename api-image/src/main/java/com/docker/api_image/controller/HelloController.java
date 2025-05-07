package com.docker.api_image.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
	@GetMapping("/api/hello")
	public String sayHello()
	{
		return ("Hello World, from Docker Image API");
	}
}
