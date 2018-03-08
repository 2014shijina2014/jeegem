package com.jeegem.common.utils.weixin.api;



import java.util.Collection;
import java.util.List;

import com.jeegem.common.utils.weixin.vo.api.FollowList;
import com.jeegem.common.utils.weixin.vo.api.Follower;
import com.jeegem.common.utils.weixin.vo.api.Follower2;

/**
 * 微信用户信息接口
 * 
 * @author 
 * @since 2.0
 */
public interface UserAPI {

    /**
     * 设置备注名地址
     */
    static String user_remark = "/user/info/updateremark?access_token=";

    /**
     * 用户列表地址
     */
    static String user_list = "/user/get?access_token=%s&next_openid=%s";

    /**
     * 用户基本信息地址
     */
    static String user_info = "/user/info?access_token=%s&openid=%s&lang=%s";

    /**
     * 批量用户基本信息地址
     */
    static String batch_user_info = "/user/info/batchget?access_token=";

    /**
     * 设置用户备注名
     * 
     * @param openId
     *            用户标识
     * @param remark
     *            新的备注名,长度必须小于30字符
     * @return true 或 false
     */
    boolean updateRemark(String openId, String remark);

    /**
     * 获取关注用户列表
     * 
     * @param nextOpenId
     *            第一个拉取的OPENID,不填默认从头开始拉取
     * @return 关注列表{@link FollowList}
     */
    FollowList getFollowerList(String nextOpenId);

    /**
     * 获取用户基本信息(包括UnionID机制)
     * 
     * @param openId
     *            用户的标识
     * @param lang
     *            国家地区语言版本,zh_CN 简体,zh_TW 繁体,en 英语
     * @return 关注用户{@link Follower}
     */
    Follower getFollower(String openId, String lang);

    /**
     * 批量获取用户基本信息[最多拉取100条]
     * 
     * @param users
     *            批量用户集合{@link Follower2}
     * @return 关注用户{@link Follower}
     */
    List<Follower> getFollowers(Collection<Follower2> users);
}
