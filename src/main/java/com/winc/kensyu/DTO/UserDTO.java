package com.winc.kensyu.DTO;

import java.sql.Date;

public class UserDTO {

	private String userId;
	private String userPass;
	private String userCompany;
	private String userName;
	private String userPhonenumber;
	private Date submitUserDate;
	private Date updateUserDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserCompany() {
		return userCompany;
	}
	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhonenumber() {
		return userPhonenumber;
	}
	public void setUserPhonenumber(String userPhonenumber) {
		this.userPhonenumber = userPhonenumber;
	}
	public Date getSubmitUserDate() {
		return submitUserDate;
	}
	public void setSubmitUserDate(Date submitUserDate) {
		this.submitUserDate = submitUserDate;
	}
	public Date getUpdateUserDate() {
		return updateUserDate;
	}
	public void setUpdateUserDate(Date updateUserDate) {
		this.updateUserDate = updateUserDate;
	}
	
	
}
