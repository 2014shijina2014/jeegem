package com.jeegem.common.utils.weixin.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jeegem.common.utils.weixin.vo.api.AccessToken;


/**
 * 本地缓存AccessToken信息
 * 
 */
public class AccessTokenMemoryCache implements MemoryCache<AccessToken> {

    private Map<String, AccessToken> ats = new ConcurrentHashMap<String, AccessToken>();

    @Override
    public AccessToken get(String mpId) {
        return ats.get(mpId);
    }

    @Override
    public void set(String mpId, AccessToken object) {
        ats.put(mpId, object);
    }

    @Override
    public void remove(String mpId) {
        ats.remove(mpId);
    }

}
