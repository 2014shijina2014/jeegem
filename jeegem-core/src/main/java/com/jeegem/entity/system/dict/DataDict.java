package com.jeegem.entity.system.dict;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;
/**
 * 数据字典
 */
@Alias("DataDict")
public class DataDict extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String id;//主键
	
	private String dataKey;//字典key值
	
	private String name;
	
	private Integer isValid;	
	
	private String description;
	
	private Date createTime;
	
	private Date updateTime;
	
	private List<DataDictItem> items=new ArrayList<DataDictItem>();

	private String keyWord;
	
	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<DataDictItem> getItems() {
		return items;
	}

	public void setItems(List<DataDictItem> items) {
		this.items = items;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}
