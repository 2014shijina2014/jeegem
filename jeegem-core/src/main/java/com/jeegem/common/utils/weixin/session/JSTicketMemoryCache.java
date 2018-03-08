package com.jeegem.common.utils.weixin.session;



import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jeegem.common.utils.weixin.vo.api.JSTicket;

/**
 * 本地缓存JSTicket信息
 * 
 */
public class JSTicketMemoryCache implements MemoryCache<JSTicket> {

    private Map<String, JSTicket> jsts = new ConcurrentHashMap<String, JSTicket>();

    @Override
    public JSTicket get(String key) {
        return jsts.get(key);
    }

    @Override
    public void set(String key, JSTicket jsTicket) {
        jsts.put(key, jsTicket);
    }

    @Override
    public void remove(String key) {
        jsts.remove(key);
    }

}
