package com.java.buyer;

public class BuyerProductGoodBad {
	private String buyer_id;
	private int product_number;
	private String value;
	public BuyerProductGoodBad() {
		this.buyer_id = "";
		this.product_number = -1;
		this.value = "";
	}
	
	public BuyerProductGoodBad(String buyer_id , int product_number , String value) {
		this.buyer_id = buyer_id;
		this.product_number = product_number;
		this.value = value;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public int getProduct_number() {
		return product_number;
	}

	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
