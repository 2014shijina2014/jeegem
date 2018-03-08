package com.jeegem.common.utils.weixin.api;

import java.util.List;

/**
 * 微信凭据授权接口
 * 
 * @author 
 * @since 2.0
 */
public interface CredentialAPI {

    /**
     * 获取access_token地址
     */
    static String get_at = "/token?grant_type=client_credential&appid=%s&secret=%s";
    /**
     * 获取微信服务器IP地址
     */
    static String cb_ips = "/getcallbackip?access_token=";

    /**
     * 长链接转短链接地址
     */
    static String short_url = "/shorturl?access_token=";

    /**
     * JSSDK临时凭证地址
     */
    static String js_ticket = "/ticket/getticket?type=jsapi&access_token=";

    /**
     * 获取微信服务凭证
     * 
     * @return 凭证
     */
    String getAccessToken();

    /**
     * 获取微信服务器IP地址
     * 
     * @return IP地址
     */
    List<String> getServerIps();

    /**
     * 长链接转短链接
     * 
     * @param longUrl
     *            需要转换的长链接,支持http://,https://,weixin://wxpay 格式的url
     * @return 短链接
     */
    String getShortUrl(String longUrl);

    /**
     * 获取JSSDK凭证
     * 
     * @return 凭证
     */
    String getJSTicket();
}
