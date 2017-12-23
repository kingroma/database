package com.java.board;

import com.java.util.MyDate;

public class Board {
	
	private int board_number;
	private String buyer_id;
	private String date;
	private int see_count;
	private String status;
	private String title;
	private String categori;
	private String text;
	private int good;
	private int bad;
	
	
	
	public Board() {
		board_number = -1;
		buyer_id = "";
		date = new MyDate().getNow();
		see_count = 0 ;
		status = "normal";
		title = "";
		categori = "";
		text = "";
		good =0;
		bad = 0;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSee_count() {
		return see_count;
	}

	public void setSee_count(int see_count) {
		this.see_count = see_count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategori() {
		return categori;
	}

	public void setCategori(String categori) {
		this.categori = categori;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
}
