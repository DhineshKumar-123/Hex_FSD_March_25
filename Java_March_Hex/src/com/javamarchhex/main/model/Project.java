package com.javamarchhex.main.model;

public class Project 
{
	private int id; 
	private String title; 
	private int credits;
	
	
	
	
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Project(int id, String title, int credits) {
		super();
		this.id = id;
		this.title = title;
		this.credits = credits;
	}


	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", credits=" + credits + "]";
	}
	
	

}
