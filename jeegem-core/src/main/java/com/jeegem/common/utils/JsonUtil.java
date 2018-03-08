package com.jeegem.common.utils;

import com.google.gson.Gson;
import java.lang.reflect.Type;
/** 
 * 实现对象和json字符串之间的转换
 */  
public class JsonUtil {  	
	  private static Gson gson = new Gson();  
	
	   public static Gson getGsonInstance() {  
		    if(gson==null)gson= new Gson();
	        return gson;  
	    }  
	    /** 
	     * 对象转换成json字符串 
	     * @param obj  
	     * @return  
	     */  
	    public static String toJson(Object obj) {  
	        return getGsonInstance().toJson(obj);  
	    }  
	  
	    /** 
	     * json字符串转成对象 
	     * @param str   
	     * @param type 
	     * @return  
	     */  
	    public static <T> T fromJson(String str, Type type) {  
	        return getGsonInstance().fromJson(str, type);  
	    }  
	  
	    /** 
	     * json字符串转成对象 
	     * @param str   
	     * @param type  
	     * @return  
	     */  
	    public static <T> T fromJson(String str, Class<T> type) {  
	        return getGsonInstance().fromJson(str, type);  
	    }  
	    
	    
}
