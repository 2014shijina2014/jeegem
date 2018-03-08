package com.jeegem.common.utils.weixin.core;

import com.jeegem.common.utils.weixin.vo.event.BasicEvent;
import com.jeegem.common.utils.weixin.vo.event.LocationEvent;
import com.jeegem.common.utils.weixin.vo.event.MenuEvent;
import com.jeegem.common.utils.weixin.vo.event.ScanCodeEvent;
import com.jeegem.common.utils.weixin.vo.event.ScanEvent;
import com.jeegem.common.utils.weixin.vo.event.SendLocationInfoEvent;
import com.jeegem.common.utils.weixin.vo.event.SendPhotosEvent;
import com.jeegem.common.utils.weixin.vo.message.BasicMsg;
import com.jeegem.common.utils.weixin.vo.message.ImageMsg;
import com.jeegem.common.utils.weixin.vo.message.LinkMsg;
import com.jeegem.common.utils.weixin.vo.message.LocationMsg;
import com.jeegem.common.utils.weixin.vo.message.TextMsg;
import com.jeegem.common.utils.weixin.vo.message.VideoMsg;
import com.jeegem.common.utils.weixin.vo.message.VoiceMsg;
import com.jeegem.common.utils.weixin.vo.push.SentAllJobEvent;
import com.jeegem.common.utils.weixin.vo.push.SentTmlJobEvent;



/**
 * 微信消息处理集合
 * 
 * @author 
 * @since 2.0
 */
public interface WechatHandler {

    /**
     * 新消息类型
     * 
     * @param bm
     *            新消息
     * @return 回复消息
     */
    BasicMsg defMsg(BasicMsg bm);

    /**
     * 新事件类型
     * 
     * @param be
     *            新类型
     * @return 回复消息
     */
    BasicMsg defEvent(BasicEvent be);

    /**
     * 处理文本消息
     *
     * @param tm
     *            文本消息
     * @return 回复消息
     */
    BasicMsg text(TextMsg tm);

    /**
     * 处理图像消息
     *
     * @param im
     *            图像消息
     * @return 回复消息
     */
    BasicMsg image(ImageMsg im);

    /**
     * 处理音频消息
     *
     * @param vom
     *            音频消息
     * @return 回复消息
     */
    BasicMsg voice(VoiceMsg vom);

    /**
     * 处理视频消息
     *
     * @param vim
     *            视频消息
     * @return 回复消息
     */
    BasicMsg video(VideoMsg vim);

    /**
     * 处理短视频消息
     *
     * @param vim
     *            短视频消息
     * @return 回复消息
     */
    BasicMsg shortVideo(VideoMsg vim);

    /**
     * 处理地理位置消息
     * 
     * @param lm
     *            地理位置
     * @return 回复消息
     */
    BasicMsg location(LocationMsg lm);

    /**
     * 处理链接消息
     *
     * @param lm
     *            链接消息
     * @return 回复消息
     */
    BasicMsg link(LinkMsg lm);

    /**
     * 处理菜单点击事件消息
     *
     * @param me
     *            菜单事件
     * @return 回复消息
     */
//    BasicMsg eClick(MenuEvent me);

    /**
     * 处理菜单视图事件消息
     *
     * @param me
     *            菜单事件
     */
    void eView(MenuEvent me);

    /**
     * 处理订阅事件消息
     *
     * @param be
     *            事件消息
     * @return 回复消息
     */
    BasicMsg eSub(BasicEvent be);

    /**
     * 处理退订事件消息
     *
     * @param be
     *            事件消息
     */
    void eUnSub(BasicEvent be);

    /**
     * 处理扫描事件消息
     *
     * @param se
     *            事件消息
     * @return 回复消息
     */
    BasicMsg eScan(ScanEvent se);

    /**
     * 处理自动上传地理事件消息
     *
     * @param le
     *            地理事件事件
     */
    void eLocation(LocationEvent le);

    /**
     * 处理二维码扫描事件消息
     *
     * @param sce
     *            扫码事件
     * @return 回复消息
     */
    BasicMsg eScanCodePush(ScanCodeEvent sce);

    /**
     * 扫码推事件且弹出“消息接收中”提示框
     *
     * @param sce
     *            扫码事件
     * @return 回复消息
     */
    BasicMsg eScanCodeWait(ScanCodeEvent sce);

    /**
     * 处理弹出系统拍照发图的事件推送
     *
     * @param spe
     *            发图事件
     * @return 回复消息
     */
    BasicMsg ePicSysPhoto(SendPhotosEvent spe);

    /**
     * 处理弹出拍照或者相册发图的事件推送
     *
     * @param spe
     *            发图事件
     * @return 回复消息
     */
    BasicMsg ePicPhotoOrAlbum(SendPhotosEvent spe);

    /**
     * 处理弹出微信相册发图器的事件推送
     *
     * @param spe
     *            发图事件
     * @return 回复消息
     */
    BasicMsg ePicWeixin(SendPhotosEvent spe);

    /**
     * 处理弹出地理位置选择器的事件推送消息
     *
     * @param slie
     *            地理位置选取事件
     * @return 回复消息
     */
    BasicMsg eLocationSelect(SendLocationInfoEvent slie);

    /**
     * 处理模板发送事件消息
     *
     * @param stje
     *            发送模板消息结果事件
     */
    void eSentTmplJobFinish(SentTmlJobEvent stje);

    /**
     * 处理群发消息事件消息
     *
     * @param saje
     *            群发消息结果事件
     */
    void eSentAllJobFinish(SentAllJobEvent saje);

}
