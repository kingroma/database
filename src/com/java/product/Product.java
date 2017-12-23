package com.java.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.java.util.MyDate;
public class Product {
	
	private int product_number = 0;
    private String imageUrl = "";
    private String seller_id = "";
    private String date = "";
    private int see_count = 0;
    private String status = "";
    private String title = "";
    private String text = "";
    private String categori = "";
    private int good= 0;
    private int bad = 0;
    private int reply_count = 0 ;
    
    private ArrayList<Product_comment> product_comment;
    private ArrayList<Product_good_bad> product_good_bad;
    //private ArrayList<Product_bad> product_bad;
    private ArrayList<Product_image> product_image;
    private ArrayList<Product_option> product_option;
    
    public Product(){
    	product_number = 0 ;
    	imageUrl = "";
        seller_id = "";
        //date = "";
        date = new MyDate().getNow();
        see_count = 0;
        //status = "";
        status="normal";
        title = "";
        text = "";
        categori = "";
        good= 0;
        bad = 0;
        reply_count=0;
        
        product_comment = new ArrayList<Product_comment>();
        product_good_bad = new ArrayList<Product_good_bad>();
        //product_bad = new ArrayList<Product_bad>();
        product_image = new ArrayList<Product_image>();
        product_option = new ArrayList<Product_option>();
        
    }

    public boolean getAllProductByResultSetNotProductNumber(ResultSet result) {
    	boolean return_value = true;
    	
    	
        try {
        	if(result != null) {
        		this.product_number = result.getInt("product_number");
        		this.imageUrl = result.getString("imageUrl");
        		this.seller_id = result.getString("seller_id");
        		this.date = result.getString("date");
        		this.see_count = result.getInt("see_count");
        		this.status = result.getString("status");
        		this.title = result.getString("title");
        		this.text = result.getString("text");
        		this.categori = result.getString("categori");
        	}
        	
        	
        }catch(Exception e) {
        	return_value = false;
        	e.printStackTrace();
        }
        
    	return return_value;
    }
    
    public boolean insertProduct(PreparedStatement pstmt) {
    	boolean return_value = true;
    	/*
    	String sql = "insert into product ("
    			+ "product_number,imageUrl,seller_id,date,see_count,"
    			+ "status,title,text,categori"
    			+ ") "
    			+ "values ("
    			+ "?,?,?,?,?,"
    			+ "?,?,?,?"
    			+ ")";    	
    	*/
    	try {
    		
    		pstmt.setInt(1, this.product_number);
    		pstmt.setString(2, this.imageUrl);
    		pstmt.setString(3, this.seller_id);
    		pstmt.setString(4, this.date);
    		pstmt.setInt(5, 0);
    		
    		pstmt.setString(6,this.status);
    		pstmt.setString(7, this.title);
    		pstmt.setString(8, this.text);
    		pstmt.setString(9, this.categori);
    		
    		pstmt.executeUpdate();
    		pstmt.close();
    		
    		
    	}catch(Exception e) {
    		return_value = false;
    	}
    	
    	return return_value;
    }
    
    public boolean updateProduct(PreparedStatement pstmt) {
    	boolean return_value =true;
    	
		String sql = "update product "
				+ "set imageUrl = ? , status = ? , title = ? , text = ? , categori = ? "
				+ "where product_number = ? ";
	
    	try {
    		pstmt.setString(1, this.imageUrl);
    		pstmt.setString(2, this.status);
    		pstmt.setString(3, this.title);
    		pstmt.setString(4, this.text);
    		pstmt.setString(5, this.categori);
    		
    		pstmt.setInt(6, this.product_number);
    		
    		pstmt.executeUpdate();
    		pstmt.close();
    		
    		
    	}catch(Exception e) {
    		return_value=false;
    		e.printStackTrace();
    	}
    	
    	return return_value;
    }
    
    
    // delete 느는없는걸로 
    
	public int getProduct_number() {
		return product_number;
	}

	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<Product_comment> getProduct_comment() {
		return product_comment;
	}

	public void setProduct_comment(ArrayList<Product_comment> product_comment) {
		this.product_comment = product_comment;
	}

	public ArrayList<Product_good_bad> getProduct_good_bad() {
		return product_good_bad;
	}

	public void setProduct_good_bad(ArrayList<Product_good_bad> product_good_bad) {
		this.product_good_bad = product_good_bad;
	}

	

	public ArrayList<Product_image> getProduct_image() {
		return product_image;
	}

	public void setProduct_image(ArrayList<Product_image> product_image) {
		this.product_image = product_image;
	}

	public ArrayList<Product_option> getProduct_option() {
		return product_option;
	}

	public void setProduct_option(ArrayList<Product_option> product_option) {
		this.product_option = product_option;
	}

	public String getCategori() {
		return categori;
	}

	public void setCategori(String categori) {
		this.categori = categori;
	}
    
    public void addProduct_option(Product_option po) {
    	if(this.product_option == null)
    		product_option = new ArrayList<Product_option>();
    		
    	product_option.add(po);	
    }
    
    public void addProduct_image(Product_image pi) {
    	if(this.product_image == null)
    		product_image = new ArrayList<Product_image>();
    		
    	product_image.add(pi);	
    }
    
    public void addProduct_comment(Product_comment pc) {
    	if(this.product_comment == null)
    		product_comment = new ArrayList<Product_comment>();
    		
    		product_comment.add(pc);	
    }
    
    public void addProduct_good_bad(Product_good_bad pgb) {
    	if(this.product_good_bad == null)
    		product_good_bad = new ArrayList<Product_good_bad>();
    		
    		product_good_bad.add(pgb);	
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

	public int getReply_count() {
		return reply_count;
	}

	public void setReplay_count(int reply_count) {
		this.reply_count = reply_count;
	}
    
}
