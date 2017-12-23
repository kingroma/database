package com.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.seller.Seller;
import com.java.util.MyDate;

public class DB_Seller {
	private DB db = null;
	private Connection conn;
	public DB_Seller(){
		
	}
	
	public void setDB(DB db) {
		this.db=db;
	}
	
	public void db_start(){
		db.start();
		conn = db.getConn();
	}
	public void db_end(){
		
		db.end();
	}
	
	/*
	//기본 change seller 
	public boolean changeSeller(Seller seller){
		if(conn == null)
			this.db_start();
		
		
		try {
			conn.setAutoCommit(false);
			if(changeSellerUpdate(seller)){
				System.out.println("변경 성공 : "+seller.getId());
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	*/
	
	//update seller
	public boolean changeSellerUpdate(Seller seller){
		String query = "update seller "
				+ "set "
				+ "name = ? , pw = ? , phone1 = ? , pw_hint = ? , pw_hint_answer = ?,"
				+ "birth = ? , phone2 = ? , address = ? , email = ? , imageUrl = ? , "
				+ "bank_name = ? , bank_account = ? ,intro = ?"
				+ "where id = '"+seller.getId()+"' ";
		PreparedStatement pstmt = null;
		
		boolean retual_value = false;
		
		String name = seller.getName();
        //String id = seller.getId();
        String pw = seller.getPw();
        String phone1 = seller.getPhone1();
        String pw_hint = seller.getPw_hint();
        String pw_hint_answer = seller.getPw_hint_answer();
        String birth = seller.getBirth();

        String phone2 = seller.getPhone2();
        String address = seller.getAddress();
        String email = seller.getEmail();
        String imageUrl = seller.getImageUrl();
        
        String bank_name = seller.getBank_name();
        String bank_account = seller.getBank_account();
        String intro = seller.getIntro();
		
		try {
			if(conn==null)
				this.db_start();
			
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, name);
			pstmt.setString(2, pw);
			pstmt.setString(3, phone1);
			pstmt.setString(4, pw_hint);
			pstmt.setString(5, pw_hint_answer);
			pstmt.setString(6, birth);
			pstmt.setString(7, phone2);
			pstmt.setString(8, address);
			pstmt.setString(9, email);
			pstmt.setString(10, imageUrl);
			pstmt.setString(11, bank_name);
			pstmt.setString(12, bank_account);
			pstmt.setString(13, intro);
			
			
			pstmt.executeUpdate();
			
			
			pstmt.close();
			retual_value=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			retual_value=false;
			e.printStackTrace();
		}
		
		return retual_value;
	}
	
	//기본  new Seller 
	public boolean newSeller(Seller seller){
		//String query="";
		boolean returnValue = false;
		if(conn == null)
			this.db_start();
		
		try {
			conn.setAutoCommit(false);
			if(newSellerCheckId(seller.getId())){
				System.out.println("중복테스트 통과");
				if(newSellerInsert(seller)){
					System.out.println("ID : "+seller.getId()+" 만들기 성공 ! ");
				}
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		}
		
		
		
		return returnValue;
	}
	
	
	//insert into newSeller 
	public boolean newSellerInsert(Seller seller){
		
		String sql = "INSERT INTO seller("
                + "id,pw,birth,phone1,phone2,"
                + "pw_hint,pw_hint_answer,address,email,name,"
                + "imageUrl,bank_name,bank_account,intro,account_date"
                + ")"
                + "VALUES(?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?)";
		
		String name = seller.getName();
        String id = seller.getId();
        String pw = seller.getPw();
        String phone1 = seller.getPhone1();
        String pw_hint = seller.getPw_hint();
        String pw_hint_answer = seller.getPw_hint_answer();
        String birth = seller.getBirth();

        String phone2 = seller.getPhone2();
        String address = seller.getAddress();
        String email = seller.getEmail();
        String imageUrl = seller.getImageUrl();
        
        String bank_name = seller.getBank_name();
        String bank_account = seller.getBank_account();
        String intro = seller.getIntro();
        String account_date = new MyDate().getNow();
        PreparedStatement pstmt = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, birth);
            pstmt.setString(4, phone1);
            pstmt.setString(5, phone2);
            pstmt.setString(6, pw_hint);
            pstmt.setString(7, pw_hint_answer);
            pstmt.setString(8, address);
            pstmt.setString(9, email);
            pstmt.setString(10, name);
            pstmt.setString(11, imageUrl);
            pstmt.setString(12, bank_name);
            pstmt.setString(13, bank_account);
            pstmt.setString(14, intro);
			pstmt.setString(15, account_date);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			return false;                                                   
		}
		
		
		return false;
	}
	
	//아이디 중복 체크
	public boolean newSellerCheckId(String id){
		boolean return_value = true;
		String query = "select id from seller where id='"+id+"'";
		
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			if(conn == null)
				this.db_start();
							
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(query);
			
			if(resultSet!=null){
				try {
					if(resultSet.next()){
						return_value = false; // 있음 통과하면 안됨.
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			resultSet.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return return_value;
	}
	
	public boolean changeSeller(){
		return false;
	}
	
	public boolean loginSeller(String id , String pw) {
		boolean return_value = false;
		
		Statement stmt = null;
		ResultSet resultSet = null;
		String sql = "select pw from seller where id = '"+id+"'";
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			if(resultSet.next()) {
				String resultPw = resultSet.getString("pw");
				if(pw.equals(resultPw)) {
					return_value = true;
				}else {
					return_value = false;
				}
				
			}else {
				return_value = false;
			}
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		
		return return_value;
		
	}
	public ArrayList<String> searchSellerById(String id) {
		String sql = "select id from seller where id like '%"+id+"%'";
		ArrayList<String> seller_id_list = new ArrayList<String>();
		ResultSet resultSet = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				seller_id_list.add(resultSet.getString(1));
			}
			
			resultSet.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return seller_id_list;
	}
	
	public Seller getSeller(String id) {
		Seller seller = null;
		String sql = "select * from seller where id = '"+id+"'";
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
					
			if(resultSet.next()) {
				seller = new Seller();
				seller.setId(resultSet.getString("id"));
				seller.setPw(resultSet.getString("pw"));
				seller.setName(resultSet.getString("name"));
				seller.setBirth(resultSet.getString("birth"));
				seller.setPhone1(resultSet.getString("phone1"));
				seller.setPhone2(resultSet.getString("phone2"));
				seller.setStatus(resultSet.getString("status"));
				seller.setPw_hint(resultSet.getString("pw_hint"));
				seller.setPw_hint_answer(resultSet.getString("pw_hint_answer"));
				seller.setAddress(resultSet.getString("address"));
				seller.setEmail(resultSet.getString("email"));
				seller.setLevel(resultSet.getString("level"));
				seller.setImageUrl(resultSet.getString("imageUrl"));
				seller.setBank_name(resultSet.getString("bank_name"));
				seller.setBank_account(resultSet.getString("bank_account"));
				seller.setIntro(resultSet.getString("intro"));
				seller.setAccount_date(resultSet.getString("account_date"));
				
			}
			
			resultSet.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return seller;
	}
}
