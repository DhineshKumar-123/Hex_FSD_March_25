package com.javamarchhex.main.model;

import java.time.LocalDate;

public class EmployeeProject 
{
	private int id; 
	private LocalDate dateOfAssign; 
	
	private Employee employee;
	private Project project;
	
	public EmployeeProject(int id, LocalDate dateOfAssign, Employee employee, Project project) {
		super();
		this.id = id;
		this.dateOfAssign = dateOfAssign;
		this.employee = employee;
		this.project = project;
	}

	@Override
	public String toString() {
		return "EmployeeProject [id=" + id + ", dateOfAssign=" + dateOfAssign + ", employee=" + employee + "]";
	}
	

}
