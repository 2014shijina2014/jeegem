package com.jeegem.common.utils.weixin.api;

import java.io.File;

import com.jeegem.common.utils.weixin.vo.api.QRTicket;

/**
 * 微信二维码接口
 * 
 * @author 
 * @since 2.0
 */
public interface QRCodeAPI {

    /**
     * 创建二维码ticket地址
     */
    static String create_qrcode = "/qrcode/create?access_token=";

    /**
     * 下载二维码地址
     */
    static String show_qrcode = "/showqrcode?ticket=";

    /**
     * 创建二维码ticket
     * 
     * <pre/>
     * 参考[https://github.com/nutzam/nutzwx/blob/master/src/main/java/org/nutz/weixin/impl/
     * WxApi2Impl.
     * java]
     * 
     * @param sceneId
     *            场景Id(大于0 表示 临时码,Number 表示永远二维码, String 表示永远字符串)
     * @param expireSeconds
     *            二维码有效时间，以秒为单位。 最大不超过604800(即7天)
     * @return
     */
    QRTicket createQR(Object sceneId, int expireSeconds);

    /**
     * 通过ticket换取二维码
     * 
     * @param ticket
     *            二维码生成时的Ticket
     * @return 二维码图片
     */
    File getQR(String ticket);
}
