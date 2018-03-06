package com.gem.repository.weixin.event;

import java.util.List;

import com.gem.entity.weixin.event.WxEventClick;
import com.gem.repository.base.BaseDao;
import com.gem.repository.base.GemBatis;
/**
 * 微信点击事件数据层
 */
@GemBatis
public interface WxEventClickDao  extends BaseDao<WxEventClick>{

	/**
	* 批量插入微信点击事件
	* @param o 微信点击事件集合
	*/
	public void insertItems(List<WxEventClick> o);
	
}
