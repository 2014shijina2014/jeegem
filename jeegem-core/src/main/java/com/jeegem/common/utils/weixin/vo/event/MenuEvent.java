package com.jeegem.common.utils.weixin.vo.event;

import java.util.Map;

/**
 * 菜单事件
 * 
 * @author 
 * @since 2.0
 */
public class MenuEvent extends BasicEvent {

    public MenuEvent() {
        super();
    }

    public MenuEvent(Map<String, String> values) {
        super(values);
    }

    @ Override
    public String toString() {
        return "MenuEvent [toUserName="
               + toUserName
               + ", fromUserName="
               + fromUserName
               + ", createTime="
               + createTime
               + ", msgType="
               + msgType
               + ", event="
               + event
               + ", eventKey="
               + eventKey
               + "]";
    }

}
