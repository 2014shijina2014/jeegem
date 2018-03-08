package com.jeegem.common.utils.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.jeegem.common.utils.FileUtil;
import com.jeegem.common.utils.PatternUtil;

/**
 * 邮件发送模板
 */

public class MailTemplate {
	 	private String templateBody;
	    private List<String> ccMails = new ArrayList<String>();
	    private List<String> bccMails = new ArrayList<String>();
	    private List<String> toMails = new ArrayList<String>();
	    private List<String> fileList = new ArrayList<String>();
	     
	    public MailTemplate() {
	    }
	     
	    public MailTemplate(String templetPath, String toMail, String ccMail,String bccMail) throws IOException {
	         
	        loadTemplet(templetPath);
	         
	        if(PatternUtil.isEmail(toMail)){
	            this.toMails.add(toMail);
	        }
	         
	        if(PatternUtil.isEmail(ccMail)){
	            this.ccMails.add(ccMail);
	        }
	        if(PatternUtil.isEmail(bccMail)){
	            this.bccMails.add(bccMail);
	        }
	    }
	     
	    /**
	     * 添加附件
	     * @param filePath
	     * @return
	     */
	    public MailTemplate addFile(String filePath){
	        if(FileUtil.isFile(filePath)){
	            this.fileList.add(filePath);
	        }
	        return this;
	    }
	     
	    /**
	     * 添加附件列表
	     * @param filePath
	     * @return
	     */
	    public MailTemplate addFiles(List<String> files){
	        if(null != files && files.size() > 0){
	            this.fileList.addAll(files);
	        }
	        return this;
	    }
	     
	    /**
	     * 发送给谁
	     * @param toMail
	     * @return
	     */
	    public MailTemplate toMail(String ... toMails){
	        if(null != toMails && toMails.length > 0){
	            for(String toMail : toMails){
	                if(PatternUtil.isEmail(toMail)){
	                    this.toMails.add(toMail);
	                }
	            }
	        }
	        return this;
	    }
	     
	    public MailTemplate ccMail(String... ccMails){
	        if(null != ccMails && ccMails.length > 0){
	            for(String ccMail : ccMails){
	                if(PatternUtil.isEmail(ccMail)){
	                    this.ccMails.add(ccMail);
	                }
	            }
	        }
	        return this;
	    }
	    
	    public MailTemplate bccMail(String... bccMails){
	        if(null != bccMails && bccMails.length > 0){
	            for(String bccMail : bccMails){
	                if(PatternUtil.isEmail(bccMail)){
	                    this.bccMails.add(bccMail);
	                }
	            }
	        }
	        return this;
	    }
	     
	    /**
	     * 加载模板
	     * @param templetPath
	     * @return
	     * @throws IOException
	     */
	    public MailTemplate loadTemplet(String templetPath) throws IOException {
	        InputStream input = null;
	        InputStreamReader read = null;
	        BufferedReader reader = null;
	 
	        if (!new File(templetPath).exists()) {
	            templateBody = "";
	        }
	        try {
	            input = new FileInputStream(templetPath);
	            read = new InputStreamReader(input, "UTF-8");
	            reader = new BufferedReader(read);
	            String line;
	            String result = "";
	            while ((line = reader.readLine()) != null) {
	                result += line + "\n";
	            }
	            templateBody = result.substring(result.indexOf("<html>"));
	        } catch (Exception e) {
	            e.printStackTrace();
	            templateBody = "";
	        } finally {
	            reader.close();
	            read.close();
	            input.close();
	        }
	         
	        return this;
	    }
	 
	    @Override
	    public String toString() {
	        return this.templateBody;
	    }
	 
	    public String getToMail() {
	        if(null != toMails && toMails.size() > 0){
	            join(toMails, ",").substring(1);
	        }
	        return null;
	    }
	 
	    public String getCcMail() {
	        if(null != ccMails && ccMails.size() > 0){
	            join(ccMails, ",").substring(1);
	        }
	        return null;
	    }
	    
	    public String getBccMail() {
	        if(null != bccMails && bccMails.size() > 0){
	            join(bccMails, ",").substring(1);
	        }
	        return null;
	    }
	     
	    public List<String> getFileList() {
	        return fileList;
	    }
	     
	    public static <T> String join(final List<T> array, final String separator) {
	        if (array == null) {
	            return null;
	        }
	        final int noOfItems = array.size();
	        if (noOfItems <= 0) {
	            return null;
	        }
	        if (noOfItems == 1) {
	            return array.get(0).toString();
	        }
	        final StringBuilder buf = new StringBuilder(noOfItems * 16);
	        for (int i = 0; i < noOfItems; i++) {
	            buf.append(separator);
	            if (array.get(i) != null) {
	                buf.append(array.get(i));
	            }
	        }
	        return buf.toString();
	    }
	}

