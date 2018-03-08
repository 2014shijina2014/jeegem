package com.jeegem.entity.system.org;

import com.jeegem.entity.base.BaseEntity;

public class AccountPosition extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String accountId;
	
	private String posId;

	public AccountPosition() {
		super();
	}

	public AccountPosition(String accountId,String posId) {
		super();
		this.accountId = accountId;
		this.posId = posId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}
	
}
