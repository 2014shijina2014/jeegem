package com.jeegem.service.weixin.user;

import com.jeegem.entity.weixin.user.WxFollower;
import com.jeegem.service.weixin.base.WxBaseService;

public interface WxFollowerService extends WxBaseService<WxFollower>{

	/**
	 * 同步微信服务器关注者数据
	 */
	public void syncFollower();
}
