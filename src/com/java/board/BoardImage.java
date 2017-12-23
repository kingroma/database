package com.java.board;

public class BoardImage {
	private int board_number;
	private int image_number ; 
	private String imageUrl;
	
	public BoardImage() {
		board_number = 0 ;
		image_number = 0 ; 
		imageUrl = "";
		
	}

	public int getBoard_number() {
		return board_number;
	}

	public void setBoard_number(int board_number) {
		this.board_number = board_number;
	}

	public int getImage_number() {
		return image_number;
	}

	public void setImage_number(int image_number) {
		this.image_number = image_number;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
