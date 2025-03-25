package com.book.main.model;

import org.springframework.stereotype.Component;

@Component
public class Book 
{
	private int bookid;
	private String booktitle;
	private double bookprice;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int bookid, String booktitle, double bookprice) {
		super();
		this.bookid = bookid;
		this.booktitle = booktitle;
		this.bookprice = bookprice;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public double getBookprice() {
		return bookprice;
	}

	public void setBookprice(double bookprice) {
		this.bookprice = bookprice;
	}

	@Override
	public String toString() {
		return "BookModel [bookid=" + bookid + ", booktitle=" + booktitle + ", bookprice=" + bookprice + "]";
	}
	

}
