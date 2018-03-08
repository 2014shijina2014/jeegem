package com.jeegem.common.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class MyX509TrustManager implements X509TrustManager{
	 public X509Certificate[] getAcceptedIssuers() {
	        return null;  
	    }
	    @Override
	    public void checkClientTrusted(X509Certificate[] chain, String authType)
	            throws CertificateException {
	    }
	    @Override
	    public void checkServerTrusted(X509Certificate[] chain, String authType)
	            throws CertificateException {
	    }
}
