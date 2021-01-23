package com.jerome.mail;

public class SendEmailTest {

	private static String mailServerHost = "smtp.yeah.net";
	private static String mailServerPort = "25";
	private static String userName = "jerome_test@yeah.net";
	private static String fromName = "昵称";
	private static String password = "jerometest2016";
	private static String toAddress = "jerome";

	public static void main(String[] args) {
		SendEmailTest javaMailTest = new SendEmailTest();
		javaMailTest.sendTextMail();
		// javaMailTest.sendHtmlMail();
	}

	private void sendTextMail() {
		MailSenderInfo mailInfo = new MailSenderInfo(mailServerHost, mailServerPort, userName, password, userName,
				fromName, toAddress, "邮箱标题", "邮件内容");
		SimpleMailSender sms = new SimpleMailSender();
		// 发送文体格式
		if (sms.sendTextMail(mailInfo)) {
			System.out.println("邮件发送成功");
		}
	}

	@SuppressWarnings("unused")
	private void sendHtmlMail() {
		MailSenderInfo mailInfo = new MailSenderInfo(mailServerHost, mailServerPort, userName, password, userName,
				fromName, toAddress, "邮箱标题", "邮件内容");
		String[] fileNames = new String[2];
		fileNames[0] = "d:/1.txt";
		fileNames[1] = "d:/1.txt";
		mailInfo.setAttachFileNames(fileNames);
		SimpleMailSender sms = new SimpleMailSender();
		if (sms.sendHtmlMail(mailInfo)) {
			System.out.println("邮件发送成功");
		}
	}

}
