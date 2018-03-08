package com.jeegem.common.utils.weixin.exception;

/**
 * 微信高级API请求异常
 * 
 * @author 
 * @since 2.0
 */
public class WechatApiException extends Exception {

    private static final long serialVersionUID = -303278319021435258L;

    public WechatApiException() {
        super();
    }

    public WechatApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatApiException(String message) {
        super(message);
    }

    public WechatApiException(Throwable cause) {
        super(cause);
    }

}
