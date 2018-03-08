package com.jeegem.common.utils.email;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;

import com.jeegem.common.utils.PropertyUtil;
/**
 * 邮件操作工具类
 */
public class MailUtil {
	  
    /**常见的邮件发送协议地址**/
    public static final String SMTP_QQ = "smtp.qq.com";
    public static final String SMTP_163 = "smtp.163.com";
    public static final String SMTP_126 = "smtp.126.com";
    public static final String SMTP_SINA = "smtp.sina.com";
    public static final String SMTP_GMAIL = "smtp.gmail.com";
    
    
    /**
     * 设置邮箱配置获取邮箱配置类
     * @param smtp 邮件传输协议地址
     * @param port 端口
     * @param email 邮箱地址
     * @param emailName 邮箱地址别名
     * @param userName 邮箱登陆用户名
     * @param password 邮箱登陆密码 
     * @return
     */
    public static MailConfig setConfig(String smtp,String port,String email,String emailName,String userName,String password){
    	MailConfig mc=new MailConfig(smtp,port,email,emailName,userName,password);
    	return mc;
    }
    /**
     * 设置邮箱配置获取邮箱配置类
     * @param mailProp 邮箱配置位置
     * @return
     */
    public static MailConfig setConfig(String mailProp){
    	Map<String,String> emailMap=PropertyUtil.getPropertyMap(mailProp);
    	String smtp=emailMap.get("Smtp");
    	String port=emailMap.get("Port");
    	String email=emailMap.get("Email");
    	String emailName=emailMap.get("EmailName");
    	String userName=emailMap.get("UserName");
    	String password=emailMap.get("Password");
    	MailConfig mc=new MailConfig(smtp,port,email,emailName,userName,password);
    	return mc;
    }
    
    /**
     * 根据模板发送
     * @param mailTemplate
     * @param subject
     * @return
     */
    public static boolean send(MailConfig mc,MailTemplate mailTemplate, String subject){
        if(null != mailTemplate && StringUtils.isNotBlank(subject)){
            return sendProcess(mc,mailTemplate.getToMail(), mailTemplate.getCcMail(),mailTemplate.getBccMail(),  
                    subject, mailTemplate.toString(), mailTemplate.getFileList());
        }
        return false;
    }
     
    /**
     * 发送邮件
     * @param toMail        收件人地址
     * @param subject       发送主题
     * @param content       发送内容
     * @throws Exception
     * @return              成功返回true，失败返回false
     */
    public static boolean send(MailConfig mc,String toMail,String subject,String content){
        return sendProcess(mc,toMail,null,null,subject, content,null);
    }
 
     
    /**
     * 发送邮件并发送附件
     * @param toMail        收件人地址
     * @param subject       发送主题
     * @param content       发送内容
     * @param files         附件列表
     * @throws Exception
     * @return              成功返回true，失败返回false
     */
	public static boolean send(MailConfig mc,String toMail, String subject, String content, String... files) {
		return sendProcess(mc,toMail, null, null, subject, content, Arrays.asList(files));
	}
     
    /**
     * 发送邮件并发送附件
     * @param toMail        收件人地址
     * @param subject       发送主题
     * @param content       发送内容
     * @param files         附件列表
     * @throws Exception
     * @return              成功返回true，失败返回false
     */
    public static boolean send(MailConfig mc,String toMail, String subject, String content, List<String> files){
        return sendProcess(mc,toMail,null,null,subject, content, files);
    }
     
    /**
     * 发送并抄送
     * @param toMail        收件人地址
     * @param ccMail        抄送地址
     * @param subject       发送主题
     * @param content       发送内容
     * @return              成功返回true，失败返回false
     */
    public static boolean sendAndCc(MailConfig mc,String toMail,String ccMail,String bccMail,String subject,String content){
        return sendProcess(mc,toMail,ccMail,bccMail,subject, content, null);
    }
     
    /**
     * 发送邮件并抄送，带附件
     * @param toMail
     * @param ccMail
     * @param subject
     * @param content
     * @param files
     * @return
     */
    public static boolean sendAndCc(MailConfig mc,String toMail, String ccMail,String bccMail,String subject,String content,String ...files){
        return sendProcess(mc,toMail,ccMail,bccMail,subject,content,Arrays.asList(files));
    }
     
    /**
     * 发送邮件并抄送，带附件
     * @param toMail
     * @param ccMail
     * @param subject
     * @param content
     * @param files
     * @return
     */
    public static boolean sendAndCc(MailConfig mc,String toMail,String ccMail,String bccMail,String subject,String content,List<String> files){
        return sendProcess(mc,toMail,ccMail,bccMail,subject,content,files);
    }
     
    /**
     * 发送邮件
     * @param smtp        邮件协议
     * @param fromAddress 发送人地址
     * @param fromPass    发送人密码
     * @param toAddress   收件人地址
     * @param ccAdress    抄送人地址
     * @param subject     发送主题
     * @param content     发送内容
     * @throws Exception
     */
    public static boolean sendProcess(MailConfig mc,String toMailList,String ccAdress,String bccAdress,String subject, String content,List<String> fileList){
        try{
            EmailHandle emailHandle = new EmailHandle(mc.getSmtp(),mc.getPort());
            emailHandle.setFrom(mc.getEmail(),mc.getEmailName());
            emailHandle.setNeedAuth(true);
            emailHandle.setSubject(subject);
            emailHandle.setBody(content);
            emailHandle.setToList(toMailList);          
            /**添加抄送**/
            if(StringUtils.isNotEmpty(ccAdress)){
                emailHandle.setCopyToList(ccAdress);
            }            
            /**添加暗送**/
            if(StringUtils.isNotEmpty(bccAdress)){
                emailHandle.setBlindCopyToList(bccAdress);
            }           
            emailHandle.setNamePass(mc.getUserName(),mc.getPassword());            
            if(null != fileList && fileList.size() > 0){
                /** 附件文件路径 **/
                for(String file : fileList){
                    emailHandle.addFileAffix(file);
                }
            }
            return emailHandle.sendEmail();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
     
     
     
    /*********************************************异步发送:S*******************************************/
     
    /**
     * 根据模板发送
     * @param mailTemplate
     * @param subject
     * @return
     */
    public static void asynSend(final MailConfig mc,MailTemplate mailTemplate, String subject){
        if(null != mailTemplate && StringUtils.isNotBlank(subject)){
            asynSend(mc,mailTemplate.getToMail(), mailTemplate.getCcMail(),mailTemplate.getBccMail(),
                    subject, mailTemplate.toString(), mailTemplate.getFileList());
        }
    }
     
    /**
     * 异步发送邮件
     * @param toMail
     * @param subject
     * @param content
     * @return
     */
    public static void asynSend(final MailConfig mc,final String toMail, final String subject, final  String content){
        asynSend(mc,toMail, null,null, subject, content, null);
    }
     
    /**
     * 异步发送并抄送
     * @param toMail
     * @param ccMail
     * @param subject
     * @param content
     */
    public static void asynSendAndCc(final MailConfig mc,final String toMail, final String ccMail, final String subject, final String content){
        asynSend(mc,toMail, ccMail,null, subject, content, null);
    }
     
    /**
     * 异步发送邮件并发送附件
     * @param toMail
     * @param subject
     * @param content
     * @return
     */
    public static void asynSend(final MailConfig mc,final String toMail, final String subject, final  String content, final String ...files){
        asynSend(mc,toMail, null, null, subject, content, Arrays.asList(files));
    }
     
    /**
     * 异步发送邮件并发送附件
     * @param toMail
     * @param subject
     * @param content
     * @return
     */
    public static void asynSend(final MailConfig mc,final String toMail, final String subject, final  String content, final List<String> files){
        asynSend(mc,toMail, null, null, subject, content, files);
    }
     
     
    /**
     * 异步发送邮件并抄送，带附件
     * @param toMail  发送
     * @param ccMail  抄送
     * @param bccMail 暗送
     * @param subject
     * @param content
     * @param files
     * @return
     */
    public static void asynSendAndCc(final MailConfig mc,final String toMail, final String ccMail, final String bccMail, final String subject, final String content, final String ...files){
        asynSend(mc,toMail, ccMail, bccMail, subject, content, Arrays.asList(files));
    }
     
    /**
     * 异步发送邮件并抄送，带附件
     * @param toMail
     * @param ccMail
     * @param subject
     * @param content
     * @param files
     * @return
     */
    public static void asynSendAndCc(final MailConfig mc,final String toMail, final String ccMail, final String bccMail, final String subject, final String content, final List<String> files){
        asynSend(mc,toMail, ccMail, bccMail, subject, content, files);
    }
     
    /**
     * 发送邮件
     * @param smtp        邮件协议
     * @param fromAddress 发送人地址
     * @param fromPass    发送人密码
     * @param toAddress   收件人地址
     * @param ccAdress    抄送人地址
     * @param subject     发送主题
     * @param content     发送内容
     * @throws Exception
     */
    public static boolean asynSend(final MailConfig mc,final String toAddress,
            final String ccAdress,final String bccAdress,final String subject, final String content,final List<String> fileList){
        Boolean flag = Boolean.FALSE;
        FutureTask<Boolean> futureTask = null;
        ExecutorService excutorService = Executors.newCachedThreadPool();
        // 执行任务
        futureTask = new FutureTask<Boolean>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {     	       	
                EmailHandle emailHandle = new EmailHandle(mc.getSmtp(),mc.getPort());
                emailHandle.setFrom(mc.getEmail(),mc.getEmailName());
                emailHandle.setNeedAuth(true);
                emailHandle.setSubject(subject);
                emailHandle.setBody(content);
                emailHandle.setToList(toAddress);
                /**添加抄送**/
                if(StringUtils.isNotEmpty(ccAdress)){
                    emailHandle.setCopyToList(ccAdress);
                }
                /**添加暗送**/
                if(StringUtils.isNotEmpty(bccAdress)){
                    emailHandle.setBlindCopyToList(bccAdress);
                }   
                emailHandle.setNamePass(mc.getUserName(),mc.getPassword());
                if(null != fileList && fileList.size() > 0){
                    /** 附件文件路径 **/
                    for(String file : fileList){
                        emailHandle.addFileAffix(file);
                    }
                }
                return emailHandle.sendEmail();
            }
        });
        excutorService.submit(futureTask); 
        try {
            // 任务没超时说明发送成功
            flag = futureTask.get(5L, TimeUnit.SECONDS); 
        } catch (InterruptedException e) {
            futureTask.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            futureTask.cancel(true);
            e.printStackTrace();
        } catch (TimeoutException e) {
            futureTask.cancel(true);
            e.printStackTrace();
        } finally {
            excutorService.shutdown();
        }
        return flag;
    }
}
