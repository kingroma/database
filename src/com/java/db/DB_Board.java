package com.java.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.board.Board;
import com.java.board.BoardComment;
import com.java.board.BoardGoodBad;
import com.java.board.BoardImage;
import com.java.util.MyDate;

public class DB_Board {
	private DB db = null;
	private Connection conn;
	
	public DB_Board() {
		
	}
	
	
	public void setDB(DB db) {
		this.db=db;
	}
	
	public void start(){
		db.start();
		conn = db.getConn();
	}
	public void end(){
		
		db.end();
	}
	
	public int getNewBoardNumber() {
		int number = -1 ;
		
		Statement stmt = null;
		ResultSet resultSet = null;
		
		String sql = "select count(*) as count from board";
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
	
	public int newBoard(Board board) {
		boolean return_value = true;
		int board_number = this.getNewBoardNumber();
		PreparedStatement pstmt;
		String sql = "insert into board ("
				+ "board_number,buyer_id,date,title,categori,"
				+ "text"
				+ ") "
				+ "values("
				+ "?,?,?,?,?,"
				+ "?"
				+ ")";
		
		try {
			String buyer_id = board.getBuyer_id();
			String date = new MyDate().getNow();
			String title = board.getTitle();
			String categori = board.getCategori();
			String text = board.getText();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_number);
			pstmt.setString(2, buyer_id);
			pstmt.setString(3, date);
			pstmt.setString(4, title);
			pstmt.setString(5, categori);
			pstmt.setString(6, text);
			
			pstmt.executeUpdate();
			pstmt.close();
				
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		
		return board_number;
	}
	
	
	public boolean insertBoard_image(ArrayList<BoardImage> bi) {
		boolean return_value =true;
		
		PreparedStatement pstmt = null;
		String sql = "insert into board_image ( "
				+ "board_number,image_number,imageUrl "
				+ ") "
				+ "values ("
				+ "?,?,?"
				+ ")";
		
		try {
			for(int i = 0 ; i < bi.size() ; i ++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bi.get(i).getBoard_number());
				pstmt.setInt(2, i);
				pstmt.setString(3, bi.get(i).getImageUrl());
				
				pstmt.executeUpdate();
				pstmt.close();
				pstmt = null;
			}
			
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		
		return return_value;
	}
	
	public int getComment_number(int board_number) {
		int comment_number = 0;
		Statement stmt = null;
		ResultSet resultSet = null;
		String sql = "select count(*) as count from board_comment where board_number = "+board_number+"";
		
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			if(resultSet.next()) {
				comment_number = resultSet.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return comment_number;
	}
	
	public boolean insertBoard_comment(BoardComment bc) {
		boolean return_value = true ;
		
		PreparedStatement pstmt = null;
		String sql = "insert into board_comment ("
				+ "board_number,buyer_id,seller_id,text,ip,"
				+ "comment_number,date"
				+ ") values("
				+ "?,?,?,?,?,"
				+ "?,?"
				+ ")";
		
		try {
			int board_number = bc.getBoard_number();
			String buyer_id = bc.getBuyer_id();
			String seller_id = bc.getSeller_id();
			String text = bc.getText();
			String ip = bc.getIp();
			int comment_number = this.getComment_number(board_number);
			String date = new MyDate().getNow();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_number);
			pstmt.setString(2, buyer_id);
			pstmt.setString(3, seller_id);
			pstmt.setString(4, text);
			pstmt.setString(5, ip);
			pstmt.setInt(6, comment_number);
			pstmt.setString(7, date);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			
		}catch(Exception e) {
			return_value =false;
			e.printStackTrace();
		}
		
		return return_value;
	}
	
	public boolean insertBoard_good_bad(BoardGoodBad bgb) {
		boolean return_value = true;
		
		PreparedStatement pstmt = null;
		String sql = "insert into board_good_bad (board_number,buyer_id,value) values (?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,bgb.getBoard_number());
			pstmt.setString(2, bgb.getBuyer_id());
			pstmt.setString(3, bgb.getValue());
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return return_value;
	}
	
	public boolean upSeeCount(int board_number) {
		boolean return_value = true;
		
		String sql = "update board set see_count = see_count+1 where board_number = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_number);
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = null;
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		return return_value;
	}
	
	public boolean updateBoard(Board b) {
		boolean return_value = true;
		
		PreparedStatement pstmt = null;
		String sql = "update board set title = ? , categori = ? , text = ? , status = ? "
				+ "where board_number = "+b.getBoard_number();
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2,b.getCategori());
			pstmt.setString(3, b.getText());
			pstmt.setString(4, b.getStatus());
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return return_value ;
	}
	
	public boolean updateBoard_comment(BoardComment bc) {
		boolean return_value = true;
		
		PreparedStatement pstmt = null;
		String sql = "update board_comment set text = ? , status = ? "
				+ "where board_number = ? and comment_number = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bc.getText());
			pstmt.setString(2, bc.getStatus());
			pstmt.setInt(3, bc.getBoard_number());
			pstmt.setInt(4, bc.getComment_number());
			
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		return return_value;
	}
	
	
	public boolean deleteBoard_good_bad(BoardGoodBad bgb) {
		boolean return_value = true;
		PreparedStatement pstmt = null;
		String sql = "delete from board_good_bad where board_number = ? and buyer_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bgb.getBoard_number());
			pstmt.setString(2, bgb.getBuyer_id());
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		return return_value;
	}
	
	public boolean deleteBoard_image(int board_number) {
		boolean return_value = true;
		
		PreparedStatement pstmt = null;
		String sql = "delete from board_image where board_number = ? ";
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_number);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			
		}catch(Exception e) {
			return_value = false;
			e.printStackTrace();
		}
		return return_value;
	}
	
	public ArrayList<Integer> getBoardByBuyerId(String buyer_id){
		ArrayList<Integer> int_list = new ArrayList<Integer>();
		
		Statement stmt = null;
		ResultSet resultSet = null;
		String sql = "select * from board where buyer_id = '"+buyer_id+"'";
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				int_list.add(resultSet.getInt(1));
			}
			
			resultSet.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return int_list;
	}
	
	public void upGoodCountByBoardNumber(int board_number) {
		String sql = "update board set good = good+1 where board_number = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_number);
			
			pstmt.executeQuery();
			
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void downGoodCountByBoardNumber(int board_number) {
		String sql = "update board set good = good-1 where board_number = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_number);
			
			pstmt.executeQuery();
			
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void upBadCountByBoardNumber(int board_number) {
		String sql = "update board set bad = bad+1 where board_number = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_number);
			
			pstmt.executeQuery();
			
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void downBadCountByBoardNumber(int board_number) {
		String sql = "update board set bad = bad-1 where board_number = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_number);
			
			pstmt.executeQuery();
			
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Board getBoard(int board_number) {
		String sql = "select * from board where board_number = "+board_number;
		Statement stmt = null;
		ResultSet resultSet = null;
		Board board = new Board();
		
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			if(resultSet.next()) {
				board.setBoard_number(board_number);
				board.setBuyer_id(resultSet.getString("board_number"));
				board.setCategori(resultSet.getString("categori"));
				board.setDate(resultSet.getString("date"));
				board.setSee_count(resultSet.getInt("see_count"));
				board.setStatus(resultSet.getString("status"));
				board.setText(resultSet.getString("text"));
				board.setTitle(resultSet.getString("title"));
				board.setGood(resultSet.getInt("good"));
				board.setBad(resultSet.getInt("bad"));
			}
			
			resultSet.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	public BoardImage getBoardImage(int board_number) {
		String sql = "select * from board_image where board_number = "+board_number;
		Statement stmt = null;
		ResultSet resultSet = null;
		BoardImage bi = new BoardImage();
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			if(resultSet.next()) {
				//resultSet.getInt("board_number");
				bi.setBoard_number(board_number);
				bi.setImage_number(resultSet.getInt("image_number"));
				bi.setImageUrl(resultSet.getString("imageUrl"));
			}
			
			resultSet.close();
			stmt.close();
		}catch(Exception e) {
			
		}
		return bi;
	}
	public ArrayList<BoardComment> getBoardComment(int board_number) {
		String sql = "select * from board_comment where board_number = "+board_number;
		Statement stmt = null;
		ResultSet resultSet = null;
		ArrayList<BoardComment> comment_list = new ArrayList<BoardComment>();
		BoardComment bc = new BoardComment();
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				bc=null;
				bc = new BoardComment();
				
				bc.setBoard_number(board_number);
				bc.setBuyer_id(resultSet.getString("buyer_id"));
				bc.setComment_number(resultSet.getInt("comment_number"));
				bc.setDate(resultSet.getString("date"));
				bc.setIp(resultSet.getString("ip"));
				bc.setSeller_id(resultSet.getString("seller_id"));
				bc.setStatus(resultSet.getString("status"));
				bc.setText(resultSet.getString("text"));
				//resultSet.getString(columnIndex)
				//resultSet.getInt("board_number");
				comment_list.add(bc);
			}
			
			resultSet.close();
			stmt.close();
		}catch(Exception e) {
			
		}
		return comment_list;
	}
	public ArrayList<BoardGoodBad> getBoardGoodBad(int board_number) {
		String sql = "select * from board_good_bad where board_number = "+board_number;
		Statement stmt = null;
		ResultSet resultSet = null;
		ArrayList<BoardGoodBad> bgb_list = new ArrayList<BoardGoodBad>();
		BoardGoodBad bgb = null;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()) {
				bgb = null;
				bgb = new BoardGoodBad();
				
				bgb.setBoard_number(board_number);
				bgb.setBuyer_id(resultSet.getString("buyer_id"));
				bgb.setValue(resultSet.getString("value"));
				bgb_list.add(bgb);
				
			}
			
			resultSet.close();
			stmt.close();
		}catch(Exception e) {
			
		}
		return bgb_list;
	}
}
