package com.dto;

import java.util.Date;

public class DTO {
	private String userId;
	private String userPw;
	private String userName;
	private int userSSN1;
	private int userSSN2;
	private String nickname;
	private String userGender;
	private int userPhoneNum1;
	private int userPhoneNum2;
	private int userPhoneNum3;
	private String userEmailId;
	private String userEmailDomain;
	private Date userSignDate;
	public DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DTO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userSSN1=" + userSSN1
				+ ", userSSN2=" + userSSN2 + ", nickname=" + nickname + ", userGender=" + userGender
				+ ", userPhoneNum1=" + userPhoneNum1 + ", userPhoneNum2=" + userPhoneNum2 + ", userPhoneNum3="
				+ userPhoneNum3 + ", userEmailId=" + userEmailId + ", userEmailDomain=" + userEmailDomain
				+ ", userSignDate=" + userSignDate + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserSSN1() {
		return userSSN1;
	}
	public void setUserSSN1(int userSSN1) {
		this.userSSN1 = userSSN1;
	}
	public int getUserSSN2() {
		return userSSN2;
	}
	public void setUserSSN2(int userSSN2) {
		this.userSSN2 = userSSN2;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public int getUserPhoneNum1() {
		return userPhoneNum1;
	}
	public void setUserPhoneNum1(int userPhoneNum1) {
		this.userPhoneNum1 = userPhoneNum1;
	}
	public int getUserPhoneNum2() {
		return userPhoneNum2;
	}
	public void setUserPhoneNum2(int userPhoneNum2) {
		this.userPhoneNum2 = userPhoneNum2;
	}
	public int getUserPhoneNum3() {
		return userPhoneNum3;
	}
	public void setUserPhoneNum3(int userPhoneNum3) {
		this.userPhoneNum3 = userPhoneNum3;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserEmailDomain() {
		return userEmailDomain;
	}
	public void setUserEmailDomain(String userEmailDomain) {
		this.userEmailDomain = userEmailDomain;
	}
	public Date getUserSignDate() {
		return userSignDate;
	}
	public void setUserSignDate(Date userSignDate) {
		this.userSignDate = userSignDate;
	}
	
	
	
}
