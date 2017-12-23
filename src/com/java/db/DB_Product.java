package com.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.product.Product;
import com.java.product.Product_comment;
import com.java.product.Product_good_bad;
import com.java.product.Product_image;
import com.java.product.Product_message;
import com.java.product.Product_option;
import com.java.util.MyDate;

public class DB_Product {
	
	private DB db = null;
	private Connection conn = null;
	private Product product;
	
	
	public DB_Product() {
		
	}
	
	public void setDB(DB db) {
		this.db = db;
	}
	
	public void start() {
		db.start();
		conn = db.getConn();
	}
	
	public void end() {
		db.end();
		
	}
	
	
	
	
	
	//insert Product

	/*
	 * insertProduct
	 * insertProductComment
	 * insertProductGoodBad
	 * insertProductOption
	 * insertProductImage
	*/
	
	public int getNewProductNumber() {
		int number = -1 ;
		String sql = "select count(product_number) as count from product";
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			if(resultSet.next()) {
				number = resultSet.getInt("count");
			}
			resultSet.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return number;
	}
	
	public boolean newProduct(Product p) {
		boolean return_value = true;
		
		this.product = p;
		
		
		
		try {
			//this.start();
			
			//set product_number
			this.product.setProduct_number(this.getNewProductNumber());
			
			//set just product
			this.insertProduct(product);
			
			
			/*
			 *  product_comment = new ArrayList<Product_comment>();
        		product_good_bad = new ArrayList<Product_good_bad>();
		        product_image = new ArrayList<Product_image>();
		        product_option = new ArrayList<Product_option>();
			 */
			
			//set product comment not yet
			//this.insertProductComment(p);
			
			//option
			this.insertProductOption(product);
			
			//image
			this.insertProductImage(product);
			
			
			//this.end();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				
		return return_value;
	}
	
	public boolean insertProduct(Product p) {
		boolean return_value = true;
		
		String sql = "insert into product ("
    			+ "product_number,imageUrl,seller_id,date,see_count,"
    			+ "status,title,text,categori"
    			+ ") "
    			+ "values ("
    			+ "?,?,?,?,?,"
    			+ "?,?,?,?"
    			+ ")";    	
    	
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//p.insertProduct(pstmt);
			pstmt.setInt(1, p.getProduct_number());
    		pstmt.setString(2, p.getImageUrl());
    		pstmt.setString(3, p.getSeller_id());
    		pstmt.setString(4, p.getDate());
    		pstmt.setInt(5, 0);
    		
    		pstmt.setString(6,p.getStatus());
    		pstmt.setString(7, p.getTitle());
    		pstmt.setString(8, p.getText());
    		pstmt.setString(9, p.getCategori());
    		
    		pstmt.executeUpdate();
    		pstmt.close();
    		
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return return_value;
	}
	
	public int getCommentNumber() {
		int number = -1;
		
		Statement stmt = null;
		ResultSet resultSet = null;
		String sql = "select count(*) as count from product_comment";
		
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			number = resultSet.getInt("count");
			
			resultSet.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return number;
	}
	
	public boolean insertProductCommentBuyer(Product p) {
		boolean return_value = true;
		int product_number = p.getProduct_number();
		int comment_number = this.getCommentNumber();
		ArrayList<Product_comment> comment_list = p.getProduct_comment();
		String date = new MyDate().getNow();
		
		String sql = "insert into product_comment ("
				+ "product_number,buyer_id,text,ip,comment_number,"
				+ "date,status"
				+ ") "
				+ "values ("
				+ "?,?,?,?,?,"
				+ "?,?"
				+ ")";
		
		PreparedStatement pstmt = null;
		if(comment_list!=null) {
			try {
				for(int i = 0 ; i < comment_list.size() ; i ++) {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, product_number);
					pstmt.setString(2, comment_list.get(i).getBuyer_id());
					pstmt.setString(3, comment_list.get(i).getText());
					pstmt.setString(4, comment_list.get(i).getIp());
					pstmt.setInt(5, comment_number+i);
					
					pstmt.setString(6, date);
					pstmt.setString(7, "normal");
					
					pstmt.executeUpdate();
					
					pstmt.close();
					pstmt=null;
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return return_value;
	}
	
	public boolean checkProductGoodBadId(String buyer_id) {
		boolean return_value = true;
		
		Statement stmt = null;
		ResultSet resultSet = null;
		
		String sql ="select * from product_good_bad where buyer_id = '"+buyer_id+"'";
		
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			if(resultSet.next())
				return_value = false;
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		return return_value;
	}
	
	public boolean insertProductGoodBad(Product p) {
		boolean return_value = true;
		
		ArrayList<Product_good_bad> product_good_bad_list = p.getProduct_good_bad();
		if(product_good_bad_list !=null&product_good_bad_list.size()>0) {
			
			Product_good_bad product_good_bad = product_good_bad_list.get(0);
			String buyer_id = product_good_bad.getBuyer_id();
			String value = product_good_bad.getValue();
			int product_number = p.getProduct_number();
			
			if(checkProductGoodBadId(buyer_id)) {
				String sql = "insert into product_good_bad ("
						+ "product_number,buyer_id,value"
						+ ") values ("
						+ "?,?,?"
						+ ")";
				try{
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, product_number);
					pstmt.setString(2, buyer_id);
					pstmt.setString(3, value);
					pstmt.executeUpdate();
					pstmt.close();
					
				}catch(Exception e) {
					return_value = false;
					e.printStackTrace();
				}
				
				
			}else {
				return_value = false;
			}
			
		}else {
			return_value = false;
		}
		
		
		
		
		return return_value;
	}
	
	public boolean insertProductOption(Product p) {
		boolean return_value = true;
		int number = p.getProduct_number();
		ArrayList<Product_option> option_list = p.getProduct_option();
		
		String sql = "insert into product_option ("
				+ "option_number,option_name,product_number,status,price"
				+ ") "
				+ "values ("
				+ "?,?,?,?,?"
				+ ")";
		PreparedStatement pstmt = null;
		if(option_list!=null) {
			try {
				for(int i = 0 ; i < option_list.size() ; i ++) {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, i);
					pstmt.setString(2, option_list.get(i).getOption_name());
					pstmt.setInt(3,number);
					pstmt.setString(4, "normal");
					pstmt.setInt(5, option_list.get(i).getPrice());
					pstmt.executeUpdate();
					
					pstmt.close();
					pstmt=null;
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return return_value;
	}
	
	public boolean insertProductImage(Product p) {
		boolean return_value = true;
		
		int number = p.getProduct_number();
		ArrayList<Product_image> image_list = p.getProduct_image();
		
		String sql = "insert into product_image ("
				+ "imageUrl,image_number,product_number"
				+ ") "
				+ "values ("
				+ "?,?,?"
				+ ")";
		PreparedStatement pstmt = null;
		
		if(image_list!=null) {
			try {
				for(int i = 0 ; i < image_list.size() ; i ++) {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, image_list.get(i).getImageUrl());
					pstmt.setInt(2, i);
					pstmt.setInt(3,number);
					pstmt.executeUpdate();
					
					pstmt.close();
					pstmt=null;
					
				}
			}catch(Exception e) {
				return_value = false;
				e.printStackTrace();
			}
		}
		
		return return_value;
	}
	
	
	
	//* insertProductGoodBad
	
	//insert Product
	
	
	
	
	
	
	//update Product     ByProductNumber
	/*
	 * Product O
	 * ProductComment  O 
	 * ProductGoodBad  delete 하고 insert 
	 * ProductOption   옵션도 delete insert 
	 * ProductImage delete insert 
	*/
	
	
	
	public boolean updateProductComment(Product_comment product_comment) {
		//int product_number = product_comment.getProduct_number();
		String text = product_comment.getText();
		int comment_number = product_comment.getComment_number();
		String status = product_comment.getStatus();
		
		boolean return_value = true;
		
		String sql = "update product_comment set text = ? , status = ? where comment_number = ?";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, text);
			pstmt.setString(2,status);
			pstmt.setInt(3, comment_number);
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		return return_value;
	}
	
	public boolean updateProduct(Product p) {
		boolean return_value = true;
		
		int product_number = p.getProduct_number();
		String imageUrl = p.getImageUrl();
		String status = p.getStatus();
		String title = p.getTitle();
		String text = p.getText();
		String categori = p.getCategori();
		
		PreparedStatement pstmt = null;
		
		String sql ="update product set imageUrl = ? , status = ? , title = ? , text = ? , categori = ?"
				+ "where product_number = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, imageUrl);
			pstmt.setString(2, status);
			pstmt.setString(3, title);
			pstmt.setString(4, text);
			pstmt.setString(5, categori);
			
			pstmt.setInt(6, product_number);
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		
		return return_value;
	}
	
	
	//update 
	
	
	
	
	
	
	//delete Product   
	/*
	 * Product
	 * ProductComment
	 * ProductGoodBad
	 * ProductOption
	 * ProductImage
	*/
	
	public boolean deleteProduct_good_bad(int product_number,String buyer_id) {
		boolean return_value = true;
		
		PreparedStatement pstmt = null;
		
		String sql = "delete from product_good_bad where buyer_id = ? and product_number = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buyer_id);
			pstmt.setInt(2, product_number);
			pstmt.executeUpdate();
			pstmt.close();
					
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		return return_value;
	}
	   
	
	public boolean deleteProduct_option(int product_number) {
		boolean return_value = true;
		
		String sql = "delete from product_option where product_number = ? ";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_number);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return return_value;
	}
	
	public boolean deleteProduct_image(int product_number) {
		boolean return_value = true;
		String sql = "delete from product_image where product_number = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_number);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return return_value;
	}
	//delete Product
	
	//getProductAllThings & one and one
	/*
	 * Product
	 * ProductComment
	 * ProductGoodBad
	 * ProductOption
	 * ProductImage
	*/
	//getProductAllThings & one and one
	
	
	public Product getDetailProduct(int product_number) {
		Product p = null;
		p = getProduct(product_number);
		p.setProduct_comment(getProduct_comment(product_number));
		p.setProduct_good_bad(getProduct_good_bad(product_number));
		p.setProduct_option(getProduct_option(product_number));
		p.setProduct_image(getProduct_image(product_number));
		
		return p;
	}
	public Product getProduct(int product_number) {
		Product p = null;	
		ResultSet resultSet = null;
		Statement stmt = null;
		
		String sql = "select "
				+ "* "
				+ "from product "
				+ "where product_number = "+product_number;
		
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			if(resultSet.next()) {
				String imageUrl= resultSet.getString("imageUrl");
				String seller_id = resultSet.getString("seller_id");
				String date = resultSet.getString("date");
				int see_count = resultSet.getInt("see_count");
				
				String status = resultSet.getString("status");
				String title = resultSet.getString("title");
				String text = resultSet.getString("text");
				String categori = resultSet.getString("categori");
				int good = resultSet.getInt("good");
				int bad = resultSet.getInt("bad");
				int reply_count = resultSet.getInt("reply_count");
				
				p = new Product();
				p.setProduct_number(product_number);
				p.setImageUrl(imageUrl);
				p.setSeller_id(seller_id);
				p.setDate(date);
				p.setSee_count(see_count);
				p.setStatus(status);
				p.setTitle(title);
				p.setText(text);
				p.setCategori(categori);
				p.setGood(good);
				p.setBad(bad);
				p.setReplay_count(reply_count);
				
			}
			
			resultSet.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
	public ArrayList<Product_comment> getProduct_comment(int product_number) {
		ArrayList<Product_comment> comment_list = null;
		Product_comment product_comment = null;
		ResultSet resultSet = null;
		Statement stmt = null;
		
		String sql = "select "
				+ "* "
				+ "from product_comment "
				+ "where product_number = "+product_number+" "
						+ "order by comment_number asc";
		
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			String buyer_id = "";
			String seller_id = "";
			String text = "";
			String ip = "";
			int comment_number = 0;
			String date = "";
			String status = "";
			
			comment_list = new ArrayList<Product_comment>();
			
			
			while(resultSet.next()) {
				product_comment = null;
				
				
				buyer_id = resultSet.getString("buyer_id");
				seller_id = resultSet.getString("seller_id");
				text = resultSet.getString("text");
				ip = resultSet.getString("ip");
				comment_number = resultSet.getInt("comment_number");
				date = resultSet.getString("date");
				status = resultSet.getString("status");
				
				product_comment = new Product_comment();
				
				product_comment.setProduct_number(product_number);
				product_comment.setBuyer_id(buyer_id);
				product_comment.setSeller_id(seller_id);
				product_comment.setText(text);
				product_comment.setIp(ip);
				product_comment.setComment_number(comment_number);
				product_comment.setDate(date);
				product_comment.setStatus(status);
				
				comment_list.add(product_comment);
			}
			
			resultSet.close();
			stmt.close();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comment_list;
	}
	public ArrayList<Product_good_bad> getProduct_good_bad(int product_number) {
		ArrayList<Product_good_bad> good_bad_list = null;
		Product_good_bad product_good_bad = null;
		ResultSet resultSet = null;
		Statement stmt = null;
		
		String sql = "select "
				+ "*"
				+ "from product_good_bad "
				+ "where product_number = "+product_number+"";
						
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			String buyer_id = "";
			String value = "";
			
			good_bad_list = new ArrayList<Product_good_bad>();
			
			while(resultSet.next()){
				
				buyer_id = resultSet.getString("buyer_id");
				value = resultSet.getString("value");
				
				product_good_bad = new Product_good_bad();
				product_good_bad.setProduct_num(product_number);
				product_good_bad.setBuyer_id(buyer_id);
				product_good_bad.setValue(value);
				
				good_bad_list.add(product_good_bad);
				
			}
			
			resultSet.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return good_bad_list;
	}
	public ArrayList<Product_option> getProduct_option(int product_number) {
		
		ArrayList<Product_option> product_option_list = null;
		Product_option product_option = null;
		ResultSet resultSet = null;
		Statement stmt = null;
		
		String sql = "select "
				+ "*"
				+ "from product_option "
				+ "where product_number = "+product_number+" "
						+ "order by option_number asc";
						
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			int option_number = 0;
			String option_name = "";
			String status = "";
			int price = 0;
			
			product_option_list = new ArrayList<Product_option>();
			
			while(resultSet.next()){
				
				option_number = resultSet.getInt("option_number");
				option_name = resultSet.getString("option_name");
				status = resultSet.getString("status");
				price = resultSet.getInt("price");
				
				product_option = new Product_option();
				
				product_option.setOption_number(option_number);
				product_option.setOption_name(option_name);
				product_option.setStatus(status);
				product_option.setPrice(price);
				
				
				product_option_list.add(product_option);
				
			}
			
			resultSet.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return product_option_list;
	}
	public ArrayList<Product_image> getProduct_image(int product_number) {
		ArrayList<Product_image> product_image_list = null;
		Product_image product_image = null;
		ResultSet resultSet = null;
		Statement stmt = null;
		
		String sql = "select "
				+ "*"
				+ "from product_image "
				+ "where product_number = "+product_number+" "
						+ "order by image_number asc";
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			//String imageUrl = resultSet.getString("imageUrl");
			//int image_number = resultSet.getInt("image_number");
			
			product_image_list = new ArrayList<Product_image>();
			
			while(resultSet.next()){
				
				String imageUrl = resultSet.getString("imageUrl");
				int image_number = resultSet.getInt("image_number");
				
				product_image = new Product_image();
				
				product_image.setImageUrl(imageUrl);
				product_image.setImage_number(image_number);
				product_image.setProduct_number(product_number);
				
				product_image_list.add(product_image);
				
			}
			
			resultSet.close();
			stmt.close();
			
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return product_image_list;
	}
	
	
	public boolean upSeeCount(int product_number) {
		boolean return_value = true;
		
		PreparedStatement pstmt = null;
		String sql = "update product set see_count = see_count+1 where product_number = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, product_number);
			
			pstmt.executeUpdate();
			
			pstmt.close();
					
			
			
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		
		return return_value;
	}
	
	public ArrayList<Integer> getProductBySeller(String seller_id) {
		
		
		Statement stmt = null;
		ResultSet resultSet = null;
		String sql = "select product_number from product where seller_id = '"+seller_id+"' order by product_number desc";
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				list.add(resultSet.getInt("product_number"));
				
			}
			
			resultSet.close();
			stmt.close();
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	public void upGoodCount(int product_number) {
		PreparedStatement pstmt = null;
		String sql = "update product set good = good+1 where product_number = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_number);
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void downGoodCount(int product_number) {
		PreparedStatement pstmt = null;
		String sql = "update product set good = good-1 where product_number = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_number);
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void upBadCount(int product_number) {
		PreparedStatement pstmt = null;
		String sql = "update product set bad = bad+1 where product_number = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_number);
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void downBadCount(int product_number) {
		PreparedStatement pstmt = null;
		String sql = "update product set bad = bad-1 where product_number = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_number);
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void upReplyCount(int product_number) {
		PreparedStatement pstmt = null;
		String sql = "update product set reply_count = reply_count+1 where product_number = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_number);
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertMessage(Product_message product_message) {
		boolean return_value = true;
		PreparedStatement pstmt = null;
		String sql ="insert into product_message ("
				+ "product_number,date,buyer_id,seller_id,text,"
				+ "reply,"
				+ ") values("
				+ "?,?,?,?,?,"
				+ "?"
				+ ")";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_message.getProduct_number());
			pstmt.setString(2, product_message.getDate());
			pstmt.setString(3, product_message.getBuyer_id());
			pstmt.setString(4, product_message.getSeller_id());
			pstmt.setString(5, product_message.getText());
			pstmt.setString(6, product_message.getReply());
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			return_value=false;
			e.printStackTrace();
		}
		return return_value;
	}
	
	public ArrayList<Product_message> getMessageBySeller_id(String seller_id) {
		boolean return_value = true;
		
		Statement stmt = null;
		ResultSet resultSet = null;
		String sql = "select * from product_message where seller_id = '"+seller_id+"'";
		ArrayList<Product_message> message_list = null;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				if(message_list == null) 
					message_list = new ArrayList<Product_message>();
				Product_message pm = new Product_message();
				
				String buyer_id = resultSet.getString("buyer_id");
				String date = resultSet.getString("date");
				int product_number = resultSet.getInt("product_number");
				String reply = resultSet.getString("reply");
				//String seller_id = resultSet.getString("seller_id");
				String text = resultSet.getString("text");
				
				
				pm.setBuyer_id(buyer_id);
				pm.setDate(date);
				pm.setProduct_number(product_number);
				pm.setReply(reply);
				pm.setSeller_id(seller_id);
				pm.setText(text);
				
				message_list.add(pm);
			}
			resultSet.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return message_list;
	}
	public boolean updateReplyMessage(Product_message pm) {
		boolean return_value =true;
		
		PreparedStatement pstmt = null;
		String sql = "update product_message set reply=? where product_number = ? and seller_id = ? and buyer_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pm.getReply());
			pstmt.setInt(2, pm.getProduct_number());
			pstmt.setString(3, pm.getSeller_id());
			pstmt.setString(4, pm.getBuyer_id());
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		
		return return_value;
		
	}
	
	public ArrayList<Integer> searchProductByTitle(String value) {
		String sql = "select product_number from product where title like '%"+value+"%'";
		
		ResultSet resultSet = null;
		Statement stmt = null;
		ArrayList<Integer> product_integer_list = new ArrayList<Integer>();
		Product product_temp = null;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				product_integer_list.add(resultSet.getInt(1));
			}
			
			resultSet.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return product_integer_list;
	}
}
