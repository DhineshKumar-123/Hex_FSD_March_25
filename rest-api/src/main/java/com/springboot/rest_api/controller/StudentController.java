package com.springboot.rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.dto.StudentDto;
import com.springboot.rest_api.model.Student;
import com.springboot.rest_api.service.StudentService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:5173/")
public class StudentController 
{


	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentDto dto;
	/*
	 * Dto is the most useful one to create the pagination among the React Frontend
	 * */
	
	@PostMapping("/add")
	public Student addStudent(@RequestBody Student student)
	{
		return studentService.addStudent(student);
	}
	
	@GetMapping("/all")
	public StudentDto getAll(@RequestParam Integer page, @RequestParam Integer size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Page<Student> studentP = studentService.getAll(pageable); //this is to create the pagination using the reactui
		dto.setList(studentP.getContent());//These are the most important to that pagination in ui
		dto.setCurrentPage(page);
		dto.setSize(size);
		dto.setTotalElements((int) studentP.getTotalElements());
		dto.setTotalPages(studentP.getTotalPages()); 
		return dto; 
	}
	
	@DeleteMapping("/delete/{sid}")
	public void delete(@PathVariable int sid)
	{
		studentService.delete(sid);
	}
	
	@PutMapping("/update/{sid}")
	public Student update(@PathVariable int sid,  @RequestBody Student student) {
		Student studentDB = studentService.getStudent(sid); 
		studentDB.setName(student.getName());
		studentDB.setAge(student.getAge()); 
		return studentService.addStudent(studentDB);
	}

}
