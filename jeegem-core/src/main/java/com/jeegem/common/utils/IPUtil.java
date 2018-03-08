package com.jeegem.common.utils;

import javax.servlet.http.HttpServletRequest;

public class IPUtil {
	
	/**
	 * 客户端真实IP地址的方法一：
	 */
	public static String getRemortIP(HttpServletRequest request) {  
	    if (request.getHeader("x-forwarded-for") == null) {  
	        return request.getRemoteAddr();  
	    }  
	    return request.getHeader("x-forwarded-for");  
	}  
	/**
	 * 客户端真实IP地址的方法二：
	 */
	public static String getIpAddr(HttpServletRequest request) {  
		String ip="";
		if(request!=null){
			 ip = request.getHeader("x-forwarded-for");  
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			        ip = request.getHeader("Proxy-Client-IP");  
			    }  
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			        ip = request.getHeader("WL-Proxy-Client-IP");  
			    }  
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			        ip = request.getRemoteAddr();  
			    }  
		}
	    return ip;  
	}  
}
