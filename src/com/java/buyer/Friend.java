package com.java.buyer;

public class Friend {
	private String buyer_id;
	private String friend_id;
	
	public Friend() {
		buyer_id = "";
		friend_id = "";
		
	}
	
	public Friend(String buyer_id , String friend_id) {
		this.buyer_id = buyer_id;
		this.friend_id = friend_id;
		
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}
	
	
}
