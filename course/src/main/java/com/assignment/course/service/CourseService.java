package com.assignment.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.course.exception.InvalidIDException;
import com.assignment.course.model.Course;
import com.assignment.course.repository.CourseRepository;

@Service
public class CourseService 
{
	@Autowired
	CourseRepository courseRepository;
	public Course addCourse(Course course)
	{
		return courseRepository.save(course);
	}
	public List<Course> getAllCourse() {
				return courseRepository.findAll();
	}
	public Course getSingleCourse(int id) throws InvalidIDException {
		Optional<Course> optional = courseRepository.findById(id);
		if(optional.isEmpty())
		{
			throw new InvalidIDException("Entered ID is invalid.....");
		}
		
		return optional.get();
	}

}
