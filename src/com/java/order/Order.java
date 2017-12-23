package com.java.order;

public class Order {
	private int order_num;
	private String buyer_id;
	private String seller_id;
	private int product_num;
	private int price;
	private String phone_num;
	private String memo;
	private String date;
	
	public Order() {
		this.order_num = 0;
		this.buyer_id = "";
		this.seller_id = "";
		this.product_num = 0;
		this.price = 0;
		this.phone_num = "";
		this.memo = "";
		this.date = "";
	}
	
	public void setOrderNum(int num) {
		this.order_num = num;
	}
	public int getOrderNum() {
		return this.order_num;
	}
	
	public void setBuyerId(String id) {
		this.buyer_id= id;
	}
	
	public String getBuyerId() {
		return buyer_id;
	}
	
	public void setSellerId(String id) {
		this.seller_id=id;
	}
	public String getSellerId() {
		return this.seller_id;
	}
	
	public void setProductNum(int num) {
		this.product_num=num;
	}
	
	public int getProductNum() {
		return this.product_num;
	}
	
	public void setPrice(int cost) {
		this.price = cost;
	}
	public int getPrice() {
		return this.price;
	}
	
	public void setPhoneNum(String num) {
		this.phone_num = num;
	}
	public String getPhoneNum() {
		return this.phone_num;
	}
	
	public void setMemo(String me) {
		this.memo = me;
	}
	public String getMemo() {
		return this.memo;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return this.date;
	}
}
