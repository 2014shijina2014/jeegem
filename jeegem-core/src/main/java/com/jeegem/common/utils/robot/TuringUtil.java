package com.jeegem.common.utils.robot;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 调用图灵机器人平台接口 需要导入的包：commons-logging-1.0.4.jar、 httpclient-4.3.1.jar、httpcore-4.3.jar
 */
public class TuringUtil {
	/**
	 * 调用图灵机器人接口地址
	 */
	static final String URL="http://www.tuling123.com/openapi/api";
	/**
	 * 最好还是去网站申请key，每天调用次数有限：http://www.tuling123.com/html/doc/virtual_robot.html
	 */
	static final String KEY="4473b356943e0dc7c2c6b4d296927bea";
	
	public static String get(String content){
		return get(content,null);
	}
	
	public static String get(String content,String userid){
		String result ="";
		try {
			String INFO = URLEncoder.encode(content, "utf-8");
			String requesturl = URL+"?key="+KEY+"&info=" + INFO;
			//此userid针对开发者自己的每一个用户
			if(StringUtils.isNotBlank(userid))requesturl+="&userid="+userid;
			HttpGet request = new HttpGet(requesturl);
			HttpResponse response = HttpClients.createDefault().execute(request);
			// 200即正确的返回码
			if (response.getStatusLine().getStatusCode() == 200) {
				String responseRes = EntityUtils.toString(response.getEntity());
				JSONObject jsStr = JSONObject.fromObject(responseRes);
				result=jsStr.getString("text");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(TuringUtil.get("你好"));
	}
}
