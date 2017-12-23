package com.java.buyer;

public class BuyerBoardGoodBad {
	private String buyer_id;
	private int board_number;
	private String value;
	
	public BuyerBoardGoodBad() {
		buyer_id = "";
		board_number = -1;
		value = "";
	}
	
	public BuyerBoardGoodBad(String buyer_id , int board_number , String value) {
		this.buyer_id = buyer_id;
		this.board_number = board_number;
		this.value = value;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public int getBoard_number() {
		return board_number;
	}

	public void setBoard_number(int board_number) {
		this.board_number = board_number;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
