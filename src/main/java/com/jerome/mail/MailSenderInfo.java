package com.jerome.mail;

/** 
 * 发送邮件需要使用的基本信息 
 */
import java.util.Properties;

public class MailSenderInfo {
	// 发送邮件的服务器的IP和端口
	private String mailServerHost;
	private String mailServerPort = "25";
	// 邮件发送者的地址
	private String fromAddress;
	// 邮件发送者的昵称
	private String fromName;
	// 邮件接收者的地址
	private String toAddress;
	// 登陆邮件发送服务器的用户名和密码
	private String userName;
	private String password;
	// 是否需要身份验证
	private boolean validate = true;
	// 邮件主题
	private String subject;
	// 邮件的文本内容
	private String content;
	// 邮件附件的文件名
	private String[] attachFileNames;

	public MailSenderInfo() {

	}

	/**
	 * 
	 * @param mailServerHost
	 *            发送邮件的服务器的IP
	 * @param mailServerPort
	 *            发送邮件的服务器的端口
	 * @param userName
	 *            登陆邮件发送服务器的用户名
	 * @param password
	 *            登陆邮件发送服务器的密码
	 * @param fromAddress
	 *            邮件发送者的地址
	 * @param fromName
	 *            邮件发送者的昵称
	 * @param toAddress
	 *            邮件接收者的地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件的文本内容
	 */
	public MailSenderInfo(String mailServerHost, String mailServerPort, String userName, String password,
			String fromAddress, String fromName, String toAddress, String subject, String content) {
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.userName = userName;
		this.password = password;
		this.fromAddress = fromAddress;
		this.fromName = fromName;
		this.toAddress = toAddress;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");

		return p;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
}