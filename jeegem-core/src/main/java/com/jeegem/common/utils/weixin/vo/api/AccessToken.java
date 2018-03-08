package com.jeegem.common.utils.weixin.vo.api;

import org.nutz.json.JsonField;
import org.nutz.lang.Lang;

/**
 * 微信API凭证
 * 
 */
public class AccessToken {

    /**
     * 获取到的凭证
     */
    @JsonField(value = "access_token")
    private String accessToken;

    /**
     * 凭证有效时间,单位:秒
     */
    @JsonField(value = "expires_in")
    private long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = (expiresIn - 30) * 1000;
    }

    public boolean isAvailable() {
        if (!Lang.isEmpty(accessToken) || this.expiresIn >= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "AccessToken [accessToken=" + accessToken + ", expiresIn=" + expiresIn + "]";
    }
}
