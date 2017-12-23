package com.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.buyer.Buyer;
import com.java.buyer.BuyerBoardGoodBad;
import com.java.buyer.BuyerProductGoodBad;
import com.java.buyer.FollowSeller;
import com.java.buyer.Friend;
import com.java.util.MyDate;

public class DB_Buyer {
	DB db = null;
	Connection conn = null;
	public DB_Buyer() {
		
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
	
	public boolean newBuyer(Buyer b) {
		boolean return_value = true;
		
		PreparedStatement pstmt = null;
		String sql = "insert into buyer ("
				+ "id,pw,name,birth,phone1,"
				+ "phone2,pw_hint,pw_hint_answer,address,email,"
				+ "imageUrl,intro,date"
				+ ") "
				+ "values ("
				+ "?,?,?,?,?,"
				+ "?,?,?,?,?,"
				+ "?,?,?"
				+ ")";
		
		String id = b.getId();
		
		if(checkBuyerId(id)) {
			try {
				
				String pw = b.getPw();
				String name = b.getName();
				String birth = b.getBirth();
				String phone1 = b.getPhone1();
				String phone2 = b.getPhone2();
				String pw_hint = b.getPw_hint();
				String pw_hint_answer = b.getPw_hint_answer();
				String address = b.getAddress();
				String email = b.getEmail();
				String imageUrl = b.getImageUrl();
				String intro = b.getIntro();
				String date = new MyDate().getNow();
				
				pstmt = conn.prepareStatement(sql);
				
				
				
				pstmt.setString(1, id);
				pstmt.setString(2 , pw);
				pstmt.setString(3 , name);
				pstmt.setString(4 , birth);
				pstmt.setString(5 , phone1);
				pstmt.setString(6 , phone2);
				pstmt.setString(7 , pw_hint);
				pstmt.setString(8 , pw_hint_answer);
				pstmt.setString(9 , address);
				pstmt.setString(10 , email);
				pstmt.setString(11, imageUrl);
				pstmt.setString(12 , intro);
				pstmt.setString(13 , date);
				
				pstmt.executeUpdate();
				pstmt.close();
				
				
				
			}catch(Exception e) {
				return_value=false;
				e.printStackTrace();
			}
		}
		
		return return_value;
	}
	
	public boolean checkBuyerId(String buyer_id) {
		boolean return_value = true;
		String sql = "select count(*) as count from buyer where id = '"+buyer_id+"'";
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			if(resultSet.next()) {
				if(resultSet.getInt("count")>0)
					return_value = false;	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return return_value;
	}
	
	
	public boolean updateBuyer(Buyer b) {
		PreparedStatement pstmt = null;
		
		boolean return_value = true;

		String id = b.getId();
		String pw = b.getPw();
		String name = b.getName();
		String birth = b.getBirth();
		String phone1 = b.getPhone1();
		String phone2 = b.getPhone2();
		String pw_hint = b.getPw_hint();
		String pw_hint_answer = b.getPw_hint_answer();
		String address = b.getAddress();
		String email = b.getEmail();
		String imageUrl = b.getImageUrl();
		String intro = b.getIntro();
		//String date = new MyDate().getNow();
		String sql = "update buyer "
				+ "set "
				+ "pw = ? , name = ? , birth = ? , "
				+ "phone1 = ? , phone2 = ? , pw_hint = ? ,"
				+ "pw_hint_answer = ? , address = ? , email = ? , "
				+ "imageUrl = ? , intro = ? "
				+ "where id = '"+id+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			
			//pstmt.setString(1, id);
			pstmt.setString(1 , pw);
			pstmt.setString(2 , name);
			pstmt.setString(3 , birth);
			pstmt.setString(4 , phone1);
			pstmt.setString(5 , phone2);
			pstmt.setString(6 , pw_hint);
			pstmt.setString(7 , pw_hint_answer);
			pstmt.setString(8 , address);
			pstmt.setString(9 , email);
			pstmt.setString(10, imageUrl);
			pstmt.setString(11 , intro);
			//pstmt.setString(13 , date);
			
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return return_value;
	}
	
	
	public void insertFriend(Friend f) {
		String sql = "insert into friend (friend_id , buyer_id) values (?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, f.getFriend_id());
			pstmt.setString(2, f.getBuyer_id());
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void insertFollowSeller(FollowSeller fs) {
		String sql = "insert into followseller (seller_id , buyer_id) values (?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fs.getSeller_id());
			pstmt.setString(2, fs.getBuyer_id());
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void insertProductGoodBad(BuyerProductGoodBad pgb) {
		String sql = "insert into buyer_product_good_bad "
				+ "(product_number , buyer_id , value) "
				+ "values (?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pgb.getProduct_number());
			pstmt.setString(2, pgb.getBuyer_id());
			pstmt.setString(3, pgb.getValue());
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void insertBoardGoodBad(BuyerBoardGoodBad bgb) {
		String sql = "insert into buyer_board_good_bad "
				+ "(product_number , buyer_id , value) "
				+ "values (?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bgb.getBoard_number());
			pstmt.setString(2, bgb.getBuyer_id());
			pstmt.setString(3, bgb.getValue());
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//delete
	public void deleteFiend(Friend f) {
		String sql = "delete from friend where friend_id = ? and buyer_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, f.getFriend_id());
			pstmt.setString(2, f.getBuyer_id());
						
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFollowSeller(FollowSeller fs) {
		String sql = "delete from followseller where seller_id = ? and buyer_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fs.getSeller_id());
			pstmt.setString(2, fs.getBuyer_id());
						
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteProductGoodBad(BuyerProductGoodBad pgb) {
		String sql = "delete from buyer_product_good_bad where product_number = ? and buyer_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pgb.getProduct_number());
			pstmt.setString(2, pgb.getBuyer_id());
						
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteBoardGoodBad(BuyerBoardGoodBad bgb) {
		String sql = "delete from buyer_board_good_bad where board_number = ? and buyer_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bgb.getBoard_number());
			pstmt.setString(2, bgb.getBuyer_id());
						
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//get
	public Buyer getBuyer(String id) {
		String sql = "select * from buyer where id = '"+id+"'";
		Buyer buyer = null;
		
		Statement stmt = null;
		ResultSet resultSet = null;
		
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			if(resultSet.next()) {
				buyer = new Buyer();
				String pw = resultSet.getString("pw");
				String name = resultSet.getString("name");
				String birth = resultSet.getString("birth");
				String phone1 = resultSet.getString("phone1");
				String phone2 = resultSet.getString("phone2");
				String status = resultSet.getString("status");
				String pw_hint = resultSet.getString("pw_hint");
				String pw_hint_answer = resultSet.getString("pw_hint_answer");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");
				String level = resultSet.getString("level");
				String imageUrl = resultSet.getString("imageurl");
				String intro = resultSet.getString("intro");
				String date = resultSet.getString("date");
				
				buyer.setId(id);
				buyer.setPw(pw);
				buyer.setName(name);
				buyer.setBirth(birth);
				buyer.setPhone1(phone1);
				buyer.setPhone2(phone2);
				buyer.setStatus(status);
				buyer.setPw_hint(pw_hint);
				buyer.setPw_hint_answer(pw_hint_answer);
				buyer.setAddress(address);
				buyer.setEmail(email);
				buyer.setLevel(level);
				buyer.setImageUrl(imageUrl);
				buyer.setIntro(intro);
				buyer.setDate(date);
				
			}
			resultSet.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return buyer;
	}
	public ArrayList<Friend> getFiend(String id) {
		String sql = "select DISTINCT * from friend where buyer_id = '"+id+"'";
		Statement stmt = null;
		ResultSet resultSet = null;
		ArrayList<Friend> friend_list = new ArrayList<Friend>();	
		Friend f;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				
				friend_list.add(
						new Friend(
								resultSet.getString("buyer_id"),
								resultSet.getString("friend_id")
								));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return friend_list;
		
	}
	public ArrayList<FollowSeller> getFollowSeller(String id) {
		String sql = "select DISTINCT * from followseller where buyer_id = '"+id+"'";
		Statement stmt = null;
		ResultSet resultSet = null;
		ArrayList<FollowSeller> follow_list = new ArrayList<FollowSeller>();
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				follow_list.add(new FollowSeller(
						resultSet.getString("buyer_id"),
						resultSet.getString("seller_id")
						));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return follow_list;
	}
	
	public ArrayList<BuyerProductGoodBad> getProductGoodBad(String id) {
		String sql = "select * from buyer_product_good_bad where buyer_id = '"+id+"'";
		Statement stmt = null;
		ResultSet resultSet = null;
		ArrayList<BuyerProductGoodBad> list = new ArrayList<BuyerProductGoodBad>();
		
		//String buyer_id , int product_number , String value
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				list.add(new BuyerProductGoodBad(
						resultSet.getString("buyer_id"),
						resultSet.getInt("product_number"),
						resultSet.getString("value")
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void getBoardGoodBad(String id) {
		String sql = "select * from buyer_board_good_bad where buyer_id = '"+id+"'";
		Statement stmt = null;
		ResultSet resultSet = null;
		ArrayList<BuyerBoardGoodBad> list = new ArrayList<BuyerBoardGoodBad>();
		
		//String buyer_id , int board_number , String value
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				list.add(new BuyerBoardGoodBad(
						resultSet.getString("buyer_id"),
						resultSet.getInt("board_number"),
						resultSet.getString("value")
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> searchBuyerById(String id) {
		String sql = "select id from buyer where id like '%"+id+"%'";
		ArrayList<String> buyer_id_list = new ArrayList<String>();
		ResultSet resultSet = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				buyer_id_list.add(resultSet.getString(1));
			}
			
			resultSet.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return buyer_id_list;
	}
	
}
