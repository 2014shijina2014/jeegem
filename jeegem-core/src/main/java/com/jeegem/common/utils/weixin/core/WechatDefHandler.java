package com.jeegem.common.utils.weixin.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.nutz.lang.Strings;
import org.springframework.context.ApplicationContext;

import com.jeegem.common.utils.SpringWebContextUtil;
import com.jeegem.common.utils.weixin.vo.event.BasicEvent;
import com.jeegem.common.utils.weixin.vo.event.LocationEvent;
import com.jeegem.common.utils.weixin.vo.event.MenuEvent;
import com.jeegem.common.utils.weixin.vo.event.ScanCodeEvent;
import com.jeegem.common.utils.weixin.vo.event.ScanEvent;
import com.jeegem.common.utils.weixin.vo.event.SendLocationInfoEvent;
import com.jeegem.common.utils.weixin.vo.event.SendPhotosEvent;
import com.jeegem.common.utils.weixin.vo.message.Article;
import com.jeegem.common.utils.weixin.vo.message.BasicMsg;
import com.jeegem.common.utils.weixin.vo.message.ImageMsg;
import com.jeegem.common.utils.weixin.vo.message.LinkMsg;
import com.jeegem.common.utils.weixin.vo.message.LocationMsg;
import com.jeegem.common.utils.weixin.vo.message.NewsMsg;
import com.jeegem.common.utils.weixin.vo.message.TextMsg;
import com.jeegem.common.utils.weixin.vo.message.VideoMsg;
import com.jeegem.common.utils.weixin.vo.message.VoiceMsg;
import com.jeegem.common.utils.weixin.vo.push.SentAllJobEvent;
import com.jeegem.common.utils.weixin.vo.push.SentTmlJobEvent;
//import com.jeegem.entity.weixin.event.WxEventClick;
//import com.jeegem.entity.weixin.menu.WxMenu;
//import com.jeegem.service.weixin.menu.WxMenuService;



/**
 * 微信消息,事件处理器(实际生产中需要重写)
 * 
 * @author 
 * @since 2.0
 */
public class WechatDefHandler implements WechatHandler {

    @Override
    public BasicMsg defMsg(BasicMsg bm) {
        TextMsg tm = new TextMsg(bm);
        tm.setContent(bm.getMsgType());
        return tm;
    }

    @Override
    public BasicMsg defEvent(BasicEvent be) {
        TextMsg tm = new TextMsg(be);
        tm.setContent(Strings.join("\n", be.getEvent(), be.getEventKey()));
        return tm;
    }

    @Override
    public BasicMsg text(TextMsg tm) {
        return tm;
    }

    @Override
    public BasicMsg image(ImageMsg im) {
        return im;
    }

    @Override
    public BasicMsg voice(VoiceMsg vm) {
        TextMsg tm = new TextMsg(vm);
        tm.setContent(Strings.join("\n", vm.getMediaId(), vm.getFormat(), vm.getRecognition()));
        return tm;
    }

    @Override
    public BasicMsg video(VideoMsg vim) {
        TextMsg tm = new TextMsg(vim);
        tm.setContent(Strings.join("\n", vim.getMsgType(), vim.getMediaId(), vim.getThumbMediaId()));
        return tm;
    }

    @Override
    public BasicMsg shortVideo(VideoMsg vim) {
        TextMsg tm = new TextMsg(vim);
        tm.setContent(Strings.join("\n", vim.getMsgType(), vim.getMediaId(), vim.getThumbMediaId()));
        return tm;
    }

    @Override
    public BasicMsg location(LocationMsg lm) {
        TextMsg tm = new TextMsg(lm);
        tm.setContent(Strings.join("\n",
                                   lm.getX(),
                                   lm.getY(),
                                   String.valueOf(lm.getScale()),
                                   lm.getLabel()));
        return tm;
    }

    @Override
    public BasicMsg link(LinkMsg lm) {
        NewsMsg news = new NewsMsg(lm);
        Article art = new Article();
        art.setTitle(lm.getTitle());
        art.setDescription(lm.getDescription());
        //art.setPicUrl("http://j2ee.u.qiniudn.com/mpsdk4j-logo.png-aliassmall");
        art.setUrl(lm.getUrl());
        news.setArticles(Arrays.asList(art));
        return news;
    }
//
//    @Override
//    public BasicMsg eClick(MenuEvent me) {
//        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
//        WxMenuService wms = (WxMenuService) ac.getBean("WxMenuService");
//        WxMenu wxMenu=wms.getWxMenuByKeyId(me.getEventKey());
//        if(wxMenu!=null){
//        	String selectType=wxMenu.getSelectType();
//            List<WxEventClick> wxecs= wxMenu.getItems();
//            if(wxecs!=null &&wxecs.size()>0){
//            	if("text".equals(selectType)){
//                	TextMsg tm = new TextMsg(me);
//                    tm.setContent(wxecs.get(0).getContent());
//                    return tm;
//                }else if("imageText".equals(selectType)){
//                	NewsMsg news = new NewsMsg(me);
//                	List<Article> articles =new ArrayList<Article>();
//                	for(WxEventClick wxec:wxecs){
//                		Article art = new Article(wxec.getTitle(),wxec.getContent(),wxec.getPicUrl(),wxec.getUrl());
//                        articles.add(art);
//                	}
//                    news.setArticles(articles);
//                    return news;
//                }else if("image".equals(selectType)){
//                	NewsMsg news = new NewsMsg(me);
//                    Article art = new Article();
//                    art.setTitle("");
//                    art.setDescription("");
//                    art.setPicUrl(wxecs.get(0).getPicUrl());
//                    news.setArticles(Arrays.asList(art));
//                	return news;
//                }
//            }
//        }     
//        TextMsg tm = new TextMsg(me);
//    	tm.setContent("对不起,暂时不能提供服务");
//    	return tm;
//    }

    @Override
    public void eView(MenuEvent me) {}

    @Override
    public BasicMsg eSub(BasicEvent be) {
        TextMsg tm = new TextMsg(be);
        tm.setContent("欢迎关注Gem网络");
        return tm;
    }

    @Override
    public void eUnSub(BasicEvent be) {}

    @Override
    public BasicMsg eScan(ScanEvent se) {
        TextMsg tm = new TextMsg(se);
        tm.setContent(se.getEventKey() + se.getTicket());
        return tm;
    }

    @Override
    public void eLocation(LocationEvent le) {}

    @Override
    public BasicMsg eScanCodePush(ScanCodeEvent sce) {
        TextMsg tm = new TextMsg(sce);
        tm.setContent(Strings.join("\n", sce.getEventKey(), sce.getScanType(), sce.getScanResult()));
        return tm;
    }

    @Override
    public BasicMsg eScanCodeWait(ScanCodeEvent sce) {
        return this.eScanCodePush(sce);
    }

    @Override
    public BasicMsg ePicSysPhoto(SendPhotosEvent spe) {
        TextMsg tm = new TextMsg(spe);
        tm.setContent(Strings.join("\n",
                                   spe.getEventKey(),
                                   String.valueOf(spe.getSendPicsInfo().getCount()),
                                   String.valueOf(spe.getSendPicsInfo().getPicList()),
                                   String.valueOf(spe.getSendPicsInfo()
                                                     .getPicList()
                                                     .get(0)
                                                     .getPicMd5Sum())));
        return tm;
    }

    @Override
    public BasicMsg ePicPhotoOrAlbum(SendPhotosEvent spe) {
        return this.ePicSysPhoto(spe);
    }

    @Override
    public BasicMsg ePicWeixin(SendPhotosEvent spe) {
        return this.ePicSysPhoto(spe);
    }

    @Override
    public BasicMsg eLocationSelect(SendLocationInfoEvent slie) {
        TextMsg tm = new TextMsg(slie);
        tm.setContent(Strings.join("\n",
                                   slie.getLocationX(),
                                   slie.getLocationY(),
                                   slie.getLabel(),
                                   String.valueOf(slie.getScale()),
                                   slie.getPoiname()));
        return tm;
    }

    @Override
    public void eSentTmplJobFinish(SentTmlJobEvent stje) {}

    @Override
    public void eSentAllJobFinish(SentAllJobEvent saje) {}

}
