package com.java.product;

import java.util.ArrayList;

public class Product_comment {
	private int product_number;
	private String buyer_id;
	private String seller_id;
	private String text;
	private String ip;
	private int comment_number;
	private String date;
	private String status;
	
	//private ArrayList<Product_comment_reply> product_comment_reply;
	
	
	public Product_comment(){
		product_number = 0 ;
		buyer_id = "";
		seller_id = "";
		text = "";
		ip = "";
		comment_number = 0 ;
		date = "";
		status = "";
		//product_comment_reply = new ArrayList<Product_comment_reply>();
	}

	public int getProduct_number() {
		return product_number;
	}

	public void setProduct_number(int product_number) {
		this.product_number = product_number;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getComment_number() {
		return comment_number;
	}

	public void setComment_number(int comment_number) {
		this.comment_number = comment_number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	/*
	public ArrayList<Product_comment_reply> getProduct_comment_reply() {
		return product_comment_reply;
	}

	public void setProduct_comment_reply(ArrayList<Product_comment_reply> product_comment_reply) {
		this.product_comment_reply = product_comment_reply;
	}
	*/
	
}
