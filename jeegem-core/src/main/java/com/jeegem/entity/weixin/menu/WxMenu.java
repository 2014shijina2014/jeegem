package com.jeegem.entity.weixin.menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;
import com.jeegem.entity.weixin.event.WxEventClick;

@Alias("WxMenu")
public class WxMenu extends BaseEntity{

	private static final long serialVersionUID = 1L;
		
	private String id;
	
	private String pId;
	
	private String name;
	
	private String type;
	
	private String keyId;
	
	private String url;
		
	private Integer sort;
	
	private String remark;
	
	private Date createTime; 
	
	private Date updateTime;
	
	private List<WxMenu> nodes =new ArrayList<WxMenu>();
	
	private List<WxEventClick> items=new ArrayList<WxEventClick>();

	private String selectType;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<WxMenu> getNodes() {
		return nodes;
	}

	public void setNodes(List<WxMenu> nodes) {
		this.nodes = nodes;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<WxEventClick> getItems() {
		return items;
	}

	public void setItems(List<WxEventClick> items) {
		this.items = items;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	} 
	
	
}
