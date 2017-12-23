package com.java.main;

import com.java.db.DB;
import com.java.db.DB_Seller;

public class main {

	public static void main(String[] args) {
		
		DB db = new DB();
		
		DB_Seller dbs = new DB_Seller();
		dbs.setDB(db);
		dbs.db_start();
		
		try {
			
			System.out.println(dbs.searchSellerById("gr").size());
			//System.out.println(dbb.getBoard(2).getText());
			
			/*
			String sql = "select * from board where board_number = 2";
			Statement stmt = db.getConn().createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			if(resultSet.next()) {
				System.out.println(resultSet.getString("text"));
			}
			
			resultSet.close();
			stmt.close();
			*/
		}catch(Exception e) {
			e.printStackTrace();
		}
		dbs.db_end();
		
		
	}

}
