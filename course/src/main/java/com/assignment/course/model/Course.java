package com.assignment.course.model;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String title;
	@ColumnDefault("10000")
	private double fee;
	@ColumnDefault("100")
	private int credits;
	
//	
//	
//	public Course() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//
//
//	public Course(int id, String title, double fee, int credits) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.fee = fee;
//		this.credits = credits;
//	}//Thwese are not want



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public double getFee() {
		return fee;
	}



	public void setFee(double fee) {
		this.fee = fee;
	}



	public int getCredits() {
		return credits;
	}



	public void setCredits(int credits) {
		this.credits = credits;
	}
	
		
	
	
}
