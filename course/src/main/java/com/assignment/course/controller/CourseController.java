package com.assignment.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.course.dto.MessageResponseDto;
import com.assignment.course.exception.InvalidIDException;
import com.assignment.course.model.Course;
import com.assignment.course.service.CourseService;

@RestController
public class CourseController 
{
	@Autowired
	CourseService courseService;
	
	@GetMapping("/api/course/public/welcome")
	public String welcomeallUsers()
	{
		return "Welcome to our Courses !!!";
	}
	@GetMapping("/api/course/private/welcome")
	public String welcomePrivateUsers()
	{
		return "Welcome to our Courses you enrolled Java,Python. Here we go!!!!!";
	}
	
	@PostMapping("/api/course/add")
	public Course addCourse(@RequestBody Course course)//this @requestbody is to get the request and give it to the Customer Model
	{
				return courseService.addCourse(course);
	}
	
	@GetMapping("/api/course/getall")
	public List<Course> getAllCourse()
	{
		return courseService.getAllCourse();
	}
	
	@GetMapping("/api/course/getone/{id}")
	public ResponseEntity<?> getSingleCourse(@PathVariable int id,MessageResponseDto messageDto)
	{
		try
		{
			Course course = courseService.getSingleCourse(id);
			return ResponseEntity.ok(course);
		}
		catch(InvalidIDException e)
		{
			messageDto.setBody(e.getMessage());
			messageDto.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDto);
		}

	}
}
