package com.jeegem.common.utils.weixin.vo.api;

import org.nutz.json.JsonField;
import org.nutz.lang.Lang;

/**
 * 微信JSSDK凭证
 * 
 */
public class JSTicket {

    /**
     * 调用微信JS接口的临时票据
     */
    private String ticket;

    /**
     * 凭证有效时间,单位:秒
     */
    @JsonField(value = "expires_in")
    private long expiresIn;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = (expiresIn - 30) * 1000;
    }

    public boolean isAvailable() {
        if (!Lang.isEmpty(ticket) || this.expiresIn >= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "JSTicket [ticket=" + ticket + ", expiresIn=" + expiresIn + "]";
    }

}
