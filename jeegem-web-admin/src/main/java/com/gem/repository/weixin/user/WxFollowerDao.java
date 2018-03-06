package com.gem.repository.weixin.user;

import java.util.List;

import com.gem.entity.weixin.user.WxFollower;
import com.gem.repository.base.BaseDao;
import com.gem.repository.base.GemBatis;


/**
 * 微信关注者数据层
 */
@GemBatis
public interface WxFollowerDao  extends BaseDao<WxFollower>{
 
    public void clearFollower();
    
    public void insertFollowers(List<WxFollower> o);
}
