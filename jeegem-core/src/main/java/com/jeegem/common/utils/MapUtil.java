package com.jeegem.common.utils;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;

public class MapUtil {

	public static Map<String, Object> beanToMap(Object obj) { 
	    Map<String, Object> params = new HashMap<String, Object>(0); 
	    try { 
	      PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
	      PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj); 
	      for (int i = 0; i < descriptors.length; i++) { 
	        String name = descriptors[i].getName(); 
	        if (!StringUtils.equals(name, "class")) { 
	          params.put(name, propertyUtilsBean.getNestedProperty(obj, name)); 
	        } 
	      } 
	    } catch (Exception e) { 
	      e.printStackTrace(); 
	    } 
	    return params; 
	}
}
