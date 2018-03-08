package com.jeegem.common.utils.weixin.enums;

/**
 * 被/主动消息类型
 * 
 * @author 
 * @since 2.0
 */
public enum MessageType {
    /**
     * 新功能
     */
    def, 
    /**
     * 文本消息
     */
    text, 
    /**
     * 图像消息
     */
    image, 
    /**
     * 语音消息
     */
    voice, 
    /**
     * 视频消息
     */
    video, 
    /**
     * 小视频消息
    */
    shortvideo, 
    /**
     * 地理位置消息
     */
    location, 
    /**
     * 链接消息
     */
    link, 
    /**
     * 音乐消息
     */
    music, 
    /**
     * 多图文消息
     */
    news, 
    /**
     * 群发消息中的图文消息
     */
    mpnews, 
    /**
     * 群发消息中的视频消息
     */
    mpvideo
}
