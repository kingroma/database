package com.java.product;

public class Product_good_bad {
	private String buyer_id;
	private int product_num;
	private String value;
	public Product_good_bad(){
		buyer_id = "";
		product_num = 0;
		value = "";
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public int getProduct_num() {
		return product_num;
	}

	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
