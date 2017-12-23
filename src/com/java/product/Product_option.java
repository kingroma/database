package com.java.product;

public class Product_option {
	private int option_number;
	private String option_name;
	private int product_number;
	private String status;
	private int price;
	
	public Product_option(){
		option_number = 0 ;
		option_name = "";
		product_number = 0 ;
		status = "";
		price = 0;
		
	}

	
	
	public int getOption_number() {
		return option_number;
	}

	public void setOption_number(int option_number) {
		this.option_number = option_number;
	}

	public String getOption_name() {
		return option_name;
	}

	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}

	public int getProduct_number() {
		return product_number;
	}

	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
