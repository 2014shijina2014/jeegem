package com.jeegem.repository.weixin.user;

import java.util.List;

import com.jeegem.entity.weixin.user.WxFollower;
import com.jeegem.repository.base.BaseDao;
import com.jeegem.repository.base.JeeGemBatis;


/**
 * 微信关注者数据层
 */
@JeeGemBatis
public interface WxFollowerDao  extends BaseDao<WxFollower>{
 
    public void clearFollower();
    
    public void insertFollowers(List<WxFollower> o);
}
