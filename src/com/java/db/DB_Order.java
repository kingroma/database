package com.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.order.*;

public class DB_Order {

	private DB db = null;
	private Connection conn = null;

	public DB_Order() {
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

	public boolean newOrder(Order order) {
		boolean return_value = true;

		PreparedStatement pstmt = null;
		//date와 order_num는 auto
		String sql = "insert into orderlist ("
				+"buyer_id, seller_id, product_num,"
				+"price, phone_num, memo ) "
				+"values ("
				+" ?, ?, ?, ?, ?, ? )" ;

		try {
			String buyerid = order.getBuyerId();
			String sellerid = order.getSellerId();
			int product_num = order.getProductNum();
			int price = order.getPrice();
			String phone1 = order.getPhoneNum();
			String memo = order.getMemo();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, buyerid);
			pstmt.setString(2, sellerid);
			pstmt.setInt(3, product_num);
			pstmt.setInt(4, price);
			pstmt.setString(5, phone1);
			pstmt.setString(6, memo);

			pstmt.executeUpdate();
			pstmt.close();



		}catch(Exception e) {
			return_value=false;
			e.printStackTrace();
		}

		return return_value;
	}
	
	public boolean updateOrderbyBuyer(String buyer, Order order) {
		boolean return_value = true;
		
		String sql = "update orderlist set buyer_id= ?, seller_id= ?," 
				+ " product_num = ?, price = ?, phone_num=?, memo = ? " 
				+ "where buyer_id= '"+buyer+"'";
		
		try {
			String buyerid = order.getBuyerId();
			String sellerid = order.getSellerId();
			int product_num = order.getProductNum();
			int price = order.getPrice();
			String phone1 = order.getPhoneNum();
			String memo = order.getMemo();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buyerid);
			pstmt.setString(2, sellerid);
			pstmt.setInt(3, product_num);
			pstmt.setInt(4, price);
			pstmt.setString(5, phone1);
			pstmt.setString(6, memo);

			pstmt.executeUpdate();
			pstmt.close();
			
		} catch(Exception e) {
			return_value=false;
			e.printStackTrace();
		}
		
		return return_value;
	}
	
	public boolean updateOrderbySeller(String seller, Order order) {
		boolean return_value = true;
		
		String sql = "update orderlist set buyer_id= ?, seller_id= ?," 
				+ " product_num = ?, price = ?, phone_num=?, memo = ? " 
				+ "where seller_id= '"+seller+"'";
		
		try {
			String buyerid = order.getBuyerId();
			String sellerid = order.getSellerId();
			int product_num = order.getProductNum();
			int price = order.getPrice();
			String phone1 = order.getPhoneNum();
			String memo = order.getMemo();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, buyerid);
			pstmt.setString(2, sellerid);
			pstmt.setInt(3, product_num);
			pstmt.setInt(4, price);
			pstmt.setString(5, phone1);
			pstmt.setString(6, memo);

			pstmt.executeUpdate();
			pstmt.close();
			
		} catch(Exception e) {
			return_value=false;
			e.printStackTrace();
		}
		
		return return_value;
	}
	
	public ArrayList<Order> selectByBuyer(String buyerid){
		ArrayList<Order> orderlist = new ArrayList<Order>();
		
		Statement stmt = null;
		ResultSet result = null;
		
		String sql="select * from orderlist where buyer_id = '"+buyerid+"'";
		Order order = new Order();
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
				order.setOrderNum(result.getInt("order_num"));
				order.setBuyerId(result.getString("buyer_id"));
				order.setSellerId(result.getString("seller_id"));
				order.setPrice(result.getInt("price"));
				order.setProductNum(result.getInt("product_num"));
				order.setPhoneNum(result.getString("phone_num"));
				order.setMemo(result.getString("memo"));
				order.setDate(result.getString("date"));
				orderlist.add(order);
			}
			result.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return orderlist;
	}
	
	public ArrayList<Order> selectBySeller(String sellerid){
		ArrayList<Order> orderlist = new ArrayList<Order>();
		
		Statement stmt = null;
		ResultSet result = null;
		
		String sql="select * from orderlist where seller_id = '"+sellerid+"'";
		Order order = new Order();
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			
			while(result.next()) {
				order.setOrderNum(result.getInt("order_num"));
				order.setBuyerId(result.getString("buyer_id"));
				order.setSellerId(result.getString("seller_id"));
				order.setPrice(result.getInt("price"));
				order.setProductNum(result.getInt("product_num"));
				order.setPhoneNum(result.getString("phone_num"));
				order.setMemo(result.getString("memo"));
				order.setDate(result.getString("date"));
				orderlist.add(order);
			}
			result.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return orderlist;
	}
}
