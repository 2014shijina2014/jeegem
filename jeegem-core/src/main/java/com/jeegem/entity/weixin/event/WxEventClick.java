package com.jeegem.entity.weixin.event;

import java.util.Date;
import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;

@Alias("WxEventClick")
public class WxEventClick extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String keyId;
	
	private String title;
	
	private String content;
	
	private String picUrl;
	
	private String url;
	
	private Date createTime;
	
	private Date updateTime;

	private Integer sort;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
