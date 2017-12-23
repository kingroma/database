package com.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	public DB(){
		
	}
	public  Connection conn;
	
    //public Statement stmt = null;
    //public ResultSet resultSet = null;
    //public PreparedStatement pstmt = null;
    String query = "";
    
    Statement stmt = null;
    PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	
	
	String db_jdbc = "com.mysql.jdbc.Driver";
	String db_url = "jdbc:mysql://35.201.215.128:3306/market";
	String db_id = "root";
	String db_pw = "4235";
	
	
	public void start(){
        try {
            Class.forName(db_jdbc);
            conn = DriverManager.getConnection(db_url,db_id,db_pw);
            System.out.println("데이터베이스 접속 성공 [[ START ]]");

        } catch (SQLException e) {
        	try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
           // TODO Auto-generated catch block
        	
           e.printStackTrace();
        }
    }
	
	public void end(){
		try {
			
			
            if (conn != null) conn.close();




            conn = null;
            //stmt = null;
            //resultSet = null;
            //pstmt = null;
            //query = "";
            System.out.println("데이터베이스 종료 완료 [[ END ]]");
        } catch (SQLException e) {
        	
            e.printStackTrace();
        }
	}

	public ResultSet StatementQuery(String sql) {
		
		this.init();
		
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			//if(stmt!=null)
				//stmt.close();
			//if(resultSet!=null)
			//	resultSet.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public void init() {
		try {
			if(resultSet!=null)
				resultSet.close();
			if(stmt != null)
				stmt.close();
			if(pstmt != null)
				pstmt.close();
			
			resultSet= null;
			stmt = null;
			pstmt = null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public PreparedStatement PrepareStatementQuery(String sql) {
		//PreparedStatement stmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			
		}catch(Exception e) {
			
		}
		return pstmt;
	}
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public PreparedStatement getPstmt() {
		return pstmt;
	}

	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public String getDb_jdbc() {
		return db_jdbc;
	}

	public void setDb_jdbc(String db_jdbc) {
		this.db_jdbc = db_jdbc;
	}

	public String getDb_url() {
		return db_url;
	}

	public void setDb_url(String db_url) {
		this.db_url = db_url;
	}

	public String getDb_id() {
		return db_id;
	}

	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}

	public String getDb_pw() {
		return db_pw;
	}

	public void setDb_pw(String db_pw) {
		this.db_pw = db_pw;
	}

	

	
	
	
}
