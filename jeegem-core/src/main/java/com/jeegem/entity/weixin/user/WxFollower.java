package com.jeegem.entity.weixin.user;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.jeegem.entity.base.BaseEntity;

@Alias("WxFollower")
public class WxFollower extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	 /**
     * 关注公众号唯一标识
     */
    private String openid;
	 /**
     * 是否订阅(0 退订, 1 订阅)
     */
    private Integer subscribe;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 性别(1 男, 2 女, 0 未知)
     */
    private Integer sex;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 用户所在国家
     */
    private String country;
    /**
     * 用户所在省份
     */
    private String province;
    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;
    /**
     * 用户头像，最后一个数值代表正方形头像大小<br/>
     * （有0、46、64、96、132数值可选，0代表640*640正方<br/>
     * 形头像），用户没有头像时该项为空
     */
    private String headimgurl;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    private Date subscribeTime;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     */
    private String unionid;
    /**
     * 粉丝的备注
     */
    private String remark;
    /**
     * 用户所在的分组ID
     */
    private String groupid;
    
	private String keyWord;
    	
	public WxFollower() {
	}
	public WxFollower(String openid, Integer subscribe, String nickname, Integer sex, String city, String country,
			String province, String language, String headimgurl, Date subscribeTime, String unionid, String remark,
			String groupid) {
		this.openid = openid;
		this.subscribe = subscribe;
		this.nickname = nickname;
		this.sex = sex;
		this.city = city;
		this.country = country;
		this.province = province;
		this.language = language;
		this.headimgurl = headimgurl;
		this.subscribeTime = subscribeTime;
		this.unionid = unionid;
		this.remark = remark;
		this.groupid = groupid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public Date getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
   
	
}
