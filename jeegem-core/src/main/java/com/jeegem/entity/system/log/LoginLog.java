package com.jeegem.entity.system.log;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;
@Alias("LoginLog")
public class LoginLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String accountId;
	
	private String aName;
	
	private String loginIP;
	
	private String beginTime;
	
	private Date loginTime;
	
	private String endTime;
	
	private String keyWord;

	public LoginLog(){}
	
	public LoginLog(String accountId, String loginIP) {
		super();
		this.accountId = accountId;
		this.loginIP = loginIP;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



}
