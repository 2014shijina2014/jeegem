package com.jeegem.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang.StringUtils;

public class HttpUtil {
	 private static final String DEFAULT_CHARSET = "UTF-8";
	 /**
	     * 发送Get请求
	     * @param url
	     * @return
	     * @throws NoSuchProviderException 
	     * @throws NoSuchAlgorithmException 
	     * @throws IOException 
	     * @throws KeyManagementException 
	     */
	    private static String get(String url) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException {
	        StringBuffer bufferRes = null;
	        TrustManager[] tm = { new MyX509TrustManager() };  
	        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
	        sslContext.init(null, tm, new java.security.SecureRandom());  
	        // 从上述SSLContext对象中得到SSLSocketFactory对象  
	        SSLSocketFactory ssf = sslContext.getSocketFactory();
	        
	        URL urlGet = new URL(url);
	        HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection();
	        // 连接超时
	        http.setConnectTimeout(25000);
	        // 读取超时 --服务器响应比较慢，增大时间
	        http.setReadTimeout(25000);
	        http.setRequestMethod("GET");
	        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        http.setSSLSocketFactory(ssf);
	        http.setDoOutput(true);
	        http.setDoInput(true);
	        http.connect();
	        
	        InputStream in = http.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null){
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (http != null) {
	            // 关闭连接
	            http.disconnect();
	        }
	        return bufferRes.toString();
	    }
	    
	    /**
	     * 发送Get请求
	     * @param url
	     * @return
	     * @throws NoSuchProviderException 
	     * @throws NoSuchAlgorithmException 
	     * @throws IOException 
	     * @throws KeyManagementException 
	     */
	    public static String get(String url,Boolean https) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException {
	     if(https != null && https){
	    	 return get(url);
	     }else{
	    	 	StringBuffer bufferRes = null;
	            URL urlGet = new URL(url);
	            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	            // 连接超时
	            http.setConnectTimeout(25000);
	            // 读取超时 --服务器响应比较慢，增大时间
	            http.setReadTimeout(25000);
	            http.setRequestMethod("GET");
	            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	            http.setDoOutput(true);
	            http.setDoInput(true);
	            http.connect();
	            
	            InputStream in = http.getInputStream();
	            BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	            String valueString = null;
	            bufferRes = new StringBuffer();
	            while ((valueString = read.readLine()) != null){
	                bufferRes.append(valueString);
	            }
	            in.close();
	            if (http != null) {
	                // 关闭连接
	                http.disconnect();
	            }
	            return bufferRes.toString();
	     }
	    }
	    
	    /**
	     *  发送Get请求
	     * @param url
	     * @param params
	     * @return
	     * @throws IOException 
	     * @throws NoSuchProviderException 
	     * @throws NoSuchAlgorithmException 
	     * @throws KeyManagementException 
	     */
	    public static String get(String url, Map<String, String> params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
	        return get(initParams(url, params));
	    }
	    /**
	     *  发送Post请求
	     * @param url
	     * @param params
	     * @return
	     * @throws IOException 
	     * @throws NoSuchProviderException 
	     * @throws NoSuchAlgorithmException 
	     * @throws KeyManagementException 
	     */
	    private static String post(String url, String params) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {  	
	    	StringBuffer bufferRes = null;
	        TrustManager[] tm = { new MyX509TrustManager() };
	        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	        sslContext.init(null, tm, new java.security.SecureRandom());
	        // 从上述SSLContext对象中得到SSLSocketFactory对象  
	        SSLSocketFactory ssf = sslContext.getSocketFactory();
	        URL urlGet = new URL(url);
	        HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection();
	        // 连接超时
	        http.setConnectTimeout(25000);
	        // 读取超时 --服务器响应比较慢，增大时间
	        http.setReadTimeout(25000);
	        http.setRequestMethod("POST");
	        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        http.setSSLSocketFactory(ssf);
	        http.setDoOutput(true);
	        http.setDoInput(true);
	        http.connect();
	        OutputStream out = http.getOutputStream();
	        out.write(params.getBytes("UTF-8"));
	        out.flush();
	        out.close();
	        InputStream in = http.getInputStream();
	        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
	        String valueString = null;
	        bufferRes = new StringBuffer();
	        while ((valueString = read.readLine()) != null){
	            bufferRes.append(valueString);
	        }
	        in.close();
	        if (http != null) {
	            // 关闭连接
	            http.disconnect();
	        }
	        return bufferRes.toString();
	    }
	    
	    /**
	     *  发送Post请求
	     * @param url
	     * @param params
	     * @return
	     * @throws IOException 
	     * @throws NoSuchProviderException 
	     * @throws NoSuchAlgorithmException 
	     * @throws KeyManagementException 
	     */
	    public static String post(String url, String params,Boolean https) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
	    	 if(https != null && https){
	 	    	 return post(url,params);
	 	     }else{
	 	    	StringBuffer bufferRes = null;
		        URL urlPost = new URL(url);
		        HttpURLConnection http = (HttpURLConnection) urlPost.openConnection();
		        // 连接超时
		        http.setConnectTimeout(25000);
		        // 读取超时 --服务器响应比较慢，增大时间
		        http.setReadTimeout(25000);
		        http.setRequestMethod("POST");
		        http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		        http.setDoOutput(true);
		        http.setDoInput(true);
		        http.connect();
		        OutputStream out = http.getOutputStream();
		        out.write(params.getBytes("UTF-8"));
		        out.flush();
		        out.close();
		        InputStream in = http.getInputStream();
		        BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
		        String valueString = null;
		        bufferRes = new StringBuffer();
		        while ((valueString = read.readLine()) != null){
		            bufferRes.append(valueString);
		        }
		        in.close();
		        if (http != null) {
		            // 关闭连接
		            http.disconnect();
		        }
		        return bufferRes.toString();
	 	     }
	    	
	    }
	    
	    /**
	     * 构造url
	     * @param url
	     * @param params
	     * @return
	     */
	    private static String initParams(String url, Map<String, String> params){
	        if (null == params || params.isEmpty()) {
	            return url;
	        }
	        StringBuilder sb = new StringBuilder(url);
	        if (url.indexOf("?") == -1) {
	            sb.append("?");
	        } else {
	            sb.append("&");
	        }
	        boolean first = true;
	        for (Entry<String, String> entry : params.entrySet()) {
	            if (first) {
	                first = false;
	            } else {
	                sb.append("&");
	            }
	            String key = entry.getKey();
	            String value = entry.getValue();
	            sb.append(key).append("=");
	            if (StringUtils.isNotEmpty(value)) {
	                try {
	                    sb.append(URLEncoder.encode(value, DEFAULT_CHARSET));
	                } catch (UnsupportedEncodingException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return sb.toString();
	    }
}
