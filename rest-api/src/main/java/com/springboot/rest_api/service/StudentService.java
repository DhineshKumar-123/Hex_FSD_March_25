package com.springboot.rest_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.Student;
import com.springboot.rest_api.repository.StudentRepository;

@Service
public class StudentService 
{
	@Autowired
	private StudentRepository studentRepository;

	public Student addStudent(Student student) 
	{
		return studentRepository.save(student);
	}

	public Page<Student> getAll(Pageable pageable) //Instead of list of students it returns the page of students
	{
		return studentRepository.findAll(pageable);
	}

	public void delete(int sid) 
	{
		studentRepository.deleteById(sid);
	}

	public Student getStudent(int sid) {
		Optional<Student> optional =  studentRepository.findById(sid);
		if(optional.isEmpty())
			throw new RuntimeException("Student id invalid");
		return optional.get();
	}

}
