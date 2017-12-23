package com.java.board;

public class BoardGoodBad {
	private int board_number;
	private String buyer_id;
	private String value;
	
	public BoardGoodBad() {
		board_number = 0 ; 
		buyer_id = "";
		value = "";
	}

	public int getBoard_number() {
		return board_number;
	}

	public void setBoard_number(int board_number) {
		this.board_number = board_number;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
