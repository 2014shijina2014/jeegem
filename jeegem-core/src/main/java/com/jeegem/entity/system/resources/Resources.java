package com.jeegem.entity.system.resources;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;

@Alias("BaseResources")
public class Resources extends BaseEntity {
		
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String parentId;
	private String parentName;
	private String btnId;
	private String btnFun;
	private Integer type;
	private String resUrl;
	private String icon;
	private int sort;
	private Integer isValid;
	private Integer layer;
	
	private List<Resources> nodes =new ArrayList<Resources>();
	
	private Date createTime; 
	
	private Date updateTime; 
	
	private String description;

	private String keyWord;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getBtnId() {
		return btnId;
	}
	public void setBtnId(String btnId) {
		this.btnId = btnId;
	}
	public String getBtnFun() {
		return btnFun;
	}
	public void setBtnFun(String btnFun) {
		this.btnFun = btnFun;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}	
	public Integer getLayer() {
		return layer;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	public String getResUrl() {
		return resUrl;
	}
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public List<Resources> getNodes() {
		return nodes;
	}
	public void setNodes(List<Resources> nodes) {
		this.nodes = nodes;
	}
	@Override
	public String toString() {
		return "Resources [id=" + id + ", name=" + name + ", parentId=" + parentId
				+ ", nodes=" + nodes + "]";
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
