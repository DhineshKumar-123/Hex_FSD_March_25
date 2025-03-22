package com.ecom.main.model;

public class Product 
{
	private int prod_id;
	private String prod_title;
	private double price;
	private int quantity;
	private int cate_id;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
//	public Product(int prod_id, String prod_title, double price, int quantity, int cate_id) {
//		this.prod_id = prod_id;
//		this.prod_title = prod_title;
//		this.price = price;
//		this.quantity = quantity;
//		this.cate_id = cate_id;
//	}
	public Product(String prod_title, double price, int quantity, int cate_id) {
		this.prod_title = prod_title;
		this.price = price;
		this.quantity = quantity;
		this.cate_id = cate_id;
	}
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_title() {
		return prod_title;
	}
	public void setProd_title(String prod_title) {
		this.prod_title = prod_title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	@Override
	public String toString() {
		return "Product [prod_id=" + prod_id + ", prod_title=" + prod_title + ", price=" + price + ", quantity="
				+ quantity + ", cate_id=" + cate_id + "]";
	}
	
	

}
