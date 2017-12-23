package com.java.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
	public MyDate(){
		/*
		 Date d = new Date();
	        
	      String s = d.toString();
	      System.out.println("현재날짜 : "+ s);
	        
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      //										   0123456789012345678
	      System.out.println("현재날짜 : "+ sdf.format(d));

	      //SimpleDateFormat formatter_one = new SimpleDateFormat ( "EEE, dd MMM yyyy hh:mm:ss",Locale.ENGLISH ); 
	      //SimpleDateFormat formatter_two = new SimpleDateFormat ( "yyyy-MM-dd" ); 
	      String inString = sdf.format(d); 
	      ParsePosition pos = new ParsePosition ( 0 ); 
	      Date frmTime = sdf.parse ( inString, pos ); 
	      
	      //Date temp =new Date(sdf.format(d));
	      
	      System.out.println(frmTime.getYear());
	      */
	}
	
	public String getNow(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(new Date());
	}
	
	public int returnYear(String input){
		
		int year = Integer.parseInt(input.substring(0, 4));
		int month = Integer.parseInt(input.substring(5, 7));
		int day = Integer.parseInt(input.substring(8, 10));
		int hours = Integer.parseInt(input.substring(11, 13));
		int min = Integer.parseInt(input.substring(14, 16));
		int sec = Integer.parseInt(input.substring(17, 19));
		
		return year;
	}
	
	public int returnMonth(String input){
		
		int year = Integer.parseInt(input.substring(0, 4));
		int month = Integer.parseInt(input.substring(5, 7));
		int day = Integer.parseInt(input.substring(8, 10));
		int hours = Integer.parseInt(input.substring(11, 13));
		int min = Integer.parseInt(input.substring(14, 16));
		int sec = Integer.parseInt(input.substring(17, 19));
		
		return month;
	}
	
	public int returnDay(String input){
		
		int year = Integer.parseInt(input.substring(0, 4));
		int month = Integer.parseInt(input.substring(5, 7));
		int day = Integer.parseInt(input.substring(8, 10));
		int hours = Integer.parseInt(input.substring(11, 13));
		int min = Integer.parseInt(input.substring(14, 16));
		int sec = Integer.parseInt(input.substring(17, 19));
		
		return day;
	}
	public int returnHours(String input){
		
		int year = Integer.parseInt(input.substring(0, 4));
		int month = Integer.parseInt(input.substring(5, 7));
		int day = Integer.parseInt(input.substring(8, 10));
		int hours = Integer.parseInt(input.substring(11, 13));
		int min = Integer.parseInt(input.substring(14, 16));
		int sec = Integer.parseInt(input.substring(17, 19));
		
		return hours;
	}
	
	public int returnMin(String input){
		
		int year = Integer.parseInt(input.substring(0, 4));
		int month = Integer.parseInt(input.substring(5, 7));
		int day = Integer.parseInt(input.substring(8, 10));
		int hours = Integer.parseInt(input.substring(11, 13));
		int min = Integer.parseInt(input.substring(14, 16));
		int sec = Integer.parseInt(input.substring(17, 19));
		
		return min;
	}
	
	public int returnSec(String input){
		
		int year = Integer.parseInt(input.substring(0, 4));
		int month = Integer.parseInt(input.substring(5, 7));
		int day = Integer.parseInt(input.substring(8, 10));
		int hours = Integer.parseInt(input.substring(11, 13));
		int min = Integer.parseInt(input.substring(14, 16));
		int sec = Integer.parseInt(input.substring(17, 19));
		
		return sec;
	}
}
