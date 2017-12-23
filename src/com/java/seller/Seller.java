package com.java.seller;

import java.sql.Date;
import java.sql.Timestamp;

public class Seller {

    private String id = "";
    private String pw = "";
    private String name = "";
    private String birth = "";
    private String phone1 = "";
    private String phone2 = "";
    private String status = "";
    private String pw_hint = "";
    private String pw_hint_answer = "";
    private String address = "";
    private String email = "";
    private int cash1 = 0;
    private int cash2 = 0;
    private int point = 0;
    private String level = "";
    private String imageUrl = "";
    private String bank_name ="";
    private String bank_account = "";
    private String intro = "";
    private String account_date;

    public Seller(){
    	id = "";
    	pw = "";
    	name = "";
    	birth = "";
    	phone1 = "";
    	phone2 = "";
    	status = "";
    	pw_hint = "";
    	pw_hint_answer = "";
    	address = "";
    	email = "";
    	cash1 = 0;
    	cash2 = 0;
        point = 0;
        level ="";
        imageUrl = "";
        bank_name = "";
        bank_account = "";
        intro = "";
        account_date = null;


    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPw_hint() {
		return pw_hint;
	}

	public void setPw_hint(String pw_hint) {
		this.pw_hint = pw_hint;
	}

	public String getPw_hint_answer() {
		return pw_hint_answer;
	}

	public void setPw_hint_answer(String pw_hint_answer) {
		this.pw_hint_answer = pw_hint_answer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCash1() {
		return cash1;
	}

	public void setCash1(int cash1) {
		this.cash1 = cash1;
	}

	public int getCash2() {
		return cash2;
	}

	public void setCash2(int cash2) {
		this.cash2 = cash2;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAccount_date() {
		return account_date;
	}

	public void setAccount_date(String account_date) {
		this.account_date = account_date;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}


}
