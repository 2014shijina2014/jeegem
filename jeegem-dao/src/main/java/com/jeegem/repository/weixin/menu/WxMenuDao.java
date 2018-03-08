package com.jeegem.repository.weixin.menu;

import org.apache.ibatis.annotations.Param;

import com.jeegem.entity.weixin.menu.WxMenu;
import com.jeegem.repository.base.BaseDao;
import com.jeegem.repository.base.JeeGemBatis;

/**
 * 微信菜单数据层
 */
@JeeGemBatis
public interface WxMenuDao  extends BaseDao<WxMenu>{
	/**
	 * 统计菜单数目
	 * @param o 对象      
	 * @return long
	 */
	public int menuCount(WxMenu o);
	
	public WxMenu getWxMenuByKeyId(@Param("keyId")String keyId);
}
