package com.jeegem.repository.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeegem.common.mybatis.Page;


public interface BaseDao<T> {
	/**
	 * 保存一个对象
	 * @param o 对象
	 * @return 对象的ID
	 */
	public void insert(T o);
	/**
	 * 删除一个对象
	 * @param o  对象
	 */
	public void delete(T o);
	/**
	 * 更新一个对象
	 * @param o 对象       
	 */
	public void update(T o);
	/**
	 * 批量删除一组对象
	 * @param s (主键)数组
	 */
	public void deleteBatch(List<T> os);
	/**
	 * 获得对象列表
	 * @param o 对象       
	 * @return List
	 */
	public List<T> find(T o);	
	/**
	 * 获得对象列表
	 * @param o 对象       
	 * @param page 分页对象
	 * @return List
	 */
	public List<T> findByPage(@Param("param")T o,Page<T> page);
	/**
	 * 统计数目
	 * @param o 对象      
	 * @return long
	 */
	public int count(T o);
}
