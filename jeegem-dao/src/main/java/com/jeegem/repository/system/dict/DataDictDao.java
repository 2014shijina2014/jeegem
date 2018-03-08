package com.jeegem.repository.system.dict;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeegem.entity.system.dict.DataDict;
import com.jeegem.entity.system.dict.DataDictItem;
import com.jeegem.repository.base.BaseDao;
import com.jeegem.repository.base.JeeGemBatis;
/**
 * 数据字典数据层
 */
@JeeGemBatis
public interface DataDictDao extends BaseDao<DataDict>{
	/**
	 * 查找数据字典
	 * @param keys 关键字集合
	 * @return
	 */
	public List<DataDict> findDatas(@Param(value="keys")List<String> keys);
	/**
	* 批量插入数据字典字段
	* @param o 数据字典字段集合
	*/
	public void insertItems(List<DataDictItem> o);
	/**
	* 批量删除数据字典
	* @param dictId 数据字典Id
	*/
	public void deleteItems(@Param("dictId")String dictId);
	/**
	* 批量删除数据字典
	* @param os 数据字典类
	*/
	public void deleteBatchItems(List<DataDict> os);
}
