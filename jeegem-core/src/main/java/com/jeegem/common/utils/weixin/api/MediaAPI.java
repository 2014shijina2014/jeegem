package com.jeegem.common.utils.weixin.api;

import java.io.File;

import com.jeegem.common.utils.weixin.vo.api.Media;



/**
 * 微信多媒体数据接口
 * 
 * @author 
 * @since 2.0
 */
public interface MediaAPI {

    // 上传多媒体
    static String upload_media = "/media/upload?access_token=%s&type=%s";

    // 下载多媒体
    static String get_media = "/media/get?access_token=%s&media_id=%s";

    /**
     * 上传多媒体文件
     * 
     * <pre/>
     * 上传的临时多媒体文件有格式和大小限制,如下:
     * <li/>
     * 图片(image): 1M,支持JPG格式
     * <li/>
     * 语音(voice):2M,播放长度不超过60s,支持AMR\MP3格式
     * <li/>
     * 视频(video):10MB,支持MP4格式
     * <li/>
     * 缩略图(thumb):64KB,支持JPG格式
     * 
     * <pre/>
     * 媒体文件在后台保存时间为3天,即3天后media_id失效。
     * 
     * @param type
     *            多媒体类型 {@link io.github.elkan1788.mpsdk4j.common.MediaType}
     * @param media
     *            多媒体文件
     * @return 实体{@link Media}
     */
    Media upMedia(String type, File media);

    /**
     * 下载多媒体文件
     * 
     * @param mediaId
     *            媒体文件ID
     * @return {@link File}
     */
    File dlMedia(String mediaId);
}
