package com.ecom.webapp.model;

public class User {
	
	private int userId;
	private String username ;
	private String useremail;
	private String password ;
	private String cnfPassword;
	
	public User(String username, String useremail, String password, String cnfPassword) {
		super();
		this.username = username;
		this.useremail = useremail;
		this.password = password;
		this.cnfPassword = cnfPassword;
	}
	public User() {}
	
	public User(String useremail, String password) {
		this.useremail = useremail;
		this.password = password;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCnfPassword() {
		return cnfPassword;
	}
	public void setCnfPassword(String cnfPassword) {
		this.cnfPassword = cnfPassword;
	};
	
}
