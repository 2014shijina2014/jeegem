package com.jeegem.common.utils.tree.entity;

import java.io.Serializable;

public class ZNodes implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String pId;
	private String name;
	private String checked;
	private String open;
	private String chkDisabled;	
	private String other;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(String chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
}
