package com.java.product;

public class Product_image {
	private String imageUrl;
	private int image_number;
	private int product_number;
	
	public Product_image(){
		imageUrl = "";
		image_number = 0;
		product_number = 0;	
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getImage_number() {
		return image_number;
	}

	public void setImage_number(int image_number) {
		this.image_number = image_number;
	}

	public int getProduct_number() {
		return product_number;
	}

	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}
	
	
	
}
