package com.jeegem.common.utils.email;
/** 邮箱配置类 */
public class MailConfig {
	
	/** 邮件传输协议地址 */
	private String smtp;
	/** 端口 */
	private String port;
	/** 邮箱地址 */
	private String email;
	/** 邮箱地址别名 */
	private String emailName;
	/** 邮箱登陆用户名 */
	private String userName;
	/** 邮箱登陆密码 */
	private String password;
	

	public MailConfig(){}

	public MailConfig(String smtp,String port,String email, String emailName, String userName, String password) {
		this.smtp = smtp;
		this.port = port;
		this.email = email;
		this.emailName = emailName;
		this.userName = userName;
		this.password = password;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
}
