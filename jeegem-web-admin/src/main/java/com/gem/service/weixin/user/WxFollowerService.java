package com.gem.service.weixin.user;

import com.gem.entity.weixin.user.WxFollower;
import com.gem.service.weixin.base.WxBaseService;

public interface WxFollowerService extends WxBaseService<WxFollower>{

	/**
	 * 同步微信服务器关注者数据
	 */
	public void syncFollower();
}
