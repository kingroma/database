package com.java.product;

import com.java.util.MyDate;

public class Product_message {
	private int product_number;
	private String date;
	private String buyer_id;
	private String seller_id;
	private String text;
	private String reply;
	
	
	public Product_message() {
		product_number = 0 ;
		date = new MyDate().getNow();
		buyer_id = "";
		seller_id ="";
		text = "";
		reply ="";
	}


	public int getProduct_number() {
		return product_number;
	}


	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getBuyer_id() {
		return buyer_id;
	}


	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}


	public String getSeller_id() {
		return seller_id;
	}


	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getReply() {
		return reply;
	}


	public void setReply(String reply) {
		this.reply = reply;
	}
	
	
}
