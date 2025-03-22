package com.ecom.main.model;

public class Category 
{
	private int cat_id;
	private String cat_name;
	private int priority;
	
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Category(int cat_id, String cat_name, int priority) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
		this.priority = priority;
	}


	public Category(String cat_name, int priority) {
		super();
		this.cat_name = cat_name;
		this.priority = priority;
	}


	public int getCat_id() {
		return cat_id;
	}


	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}


	public String getCat_name() {
		return cat_name;
	}


	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	@Override
	public String toString() {
		return "Category [cat_id=" + cat_id + ", cat_name=" + cat_name + ", priority=" + priority + "]";
	}
	
	

}
