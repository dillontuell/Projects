package com.revature.models;


public class User {

	private int user_id;
	private String user_username;
	private String user_password;
	
	
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String user_username, String user_password, int user_id) {
		super();
		this.user_id = user_id;
		this.user_username = user_username;
		this.user_password = user_password;
		
	}

	//public User(String user_username, String user_password) {
		//super();
		//this.user_username = user_username;
		//this.user_password = user_password;
		
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_username=" + user_username + ", user_password=" + user_password +"]";
				
	}

	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	
	} 
	
