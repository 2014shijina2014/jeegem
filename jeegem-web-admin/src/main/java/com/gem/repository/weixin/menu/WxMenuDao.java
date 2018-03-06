package com.gem.repository.weixin.menu;

import org.apache.ibatis.annotations.Param;

import com.gem.entity.weixin.menu.WxMenu;
import com.gem.repository.base.BaseDao;
import com.gem.repository.base.GemBatis;

/**
 * 微信菜单数据层
 */
@GemBatis
public interface WxMenuDao  extends BaseDao<WxMenu>{
	/**
	 * 统计菜单数目
	 * @param o 对象      
	 * @return long
	 */
	public int menuCount(WxMenu o);
	
	public WxMenu getWxMenuByKeyId(@Param("keyId")String keyId);
}
