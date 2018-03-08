package com.jeegem.entity.system.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;
import com.jeegem.entity.system.log.LoginLog;
import com.jeegem.entity.system.org.Position;
/**
 * 用户帐号表
 */
@Alias("BaseAccount")
public class Account extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	private String accountId;

	private String loginName;

	private String password;
	
	private String salt;

	private String name;
	
	private String picUrl;
	
	private String skin;
	
	private String roleId;
	
	private String roleName;

	private String email;

	private String description;
	
	private Integer isValid;

	private Date createTime;

	private Date updateTime;
	
    private LoginLog loginLog=new LoginLog();
	
	private String keyWord;
	
	private List<Position> poss=new ArrayList<Position>();

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", loginName=" + loginName + ", password=" + password + ", name=" + name
				+ ", skin=" + skin + ", roleId=" + roleId + ", email=" + email + ", isValid=" + isValid
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public LoginLog getLoginLog() {
		return loginLog;
	}

	public void setLoginLog(LoginLog loginLog) {
		this.loginLog = loginLog;
	}

	public List<Position> getPoss() {
		return poss;
	}

	public void setPoss(List<Position> poss) {
		this.poss = poss;
	}
}