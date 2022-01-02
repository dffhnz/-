package com.daojucheng.entity;

public class DAOJUCHENG_USER {
	
	private String user_id;
	private String user_name ;
	private String  user_password;
	private String user_sex;
	private int  user_status;
	private int  user_gameid;
	public DAOJUCHENG_USER(String id, String user_name, String user_password, String user_sex, int user_status,int user_gameid) {
		super();
		this.user_id = id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_sex = user_sex;
		this.user_status = user_status;
		this.user_gameid = user_gameid;
	}
	public void setUser_gameid(int user_gameid) {
		this.user_gameid = user_gameid;
	}
	public int getUser_gameid() {
		return user_gameid;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public int getUser_status() {
		return user_status;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	
}
