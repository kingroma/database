package com.java.buyer;

public class FollowSeller {
	
	private String buyer_id;
	private String seller_id;
	
	public FollowSeller() {
		this.buyer_id = "";
		this.seller_id = "";
	}
	
	public FollowSeller(String buyer_id , String seller_id) {
		this.buyer_id = buyer_id;
		this.seller_id = seller_id;
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
	
	
}
