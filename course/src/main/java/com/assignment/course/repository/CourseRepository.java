package com.assignment.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>
{

}
