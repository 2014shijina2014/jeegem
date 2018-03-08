package com.jeegem.entity.system.dict;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;
/**
 * 数据字典字段
 */
@Alias("DataDictItem")
public class DataDictItem extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String dictId;//字典id值(DataDict)
	
	private String value;//字典value值(组合主键)
	
	private String name;//字典名字
	
	private int sort;//排序

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
