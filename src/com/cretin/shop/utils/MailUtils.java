package com.cretin.shop.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送的工具类
 * 
 * @author cretin
 *
 */
public class MailUtils {
	/**
	 * 发送邮件的方法
	 * 
	 * @param to
	 *            收件人
	 * @param code
	 *            激活码
	 */
	public static void sendMail(String to, String code) {
		/**
		 * 1、获得一个session连接 2、创建一个代表邮件的对象message 3、发送邮件Transport
		 */
		try {
			Properties prop = new Properties();
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.host", "smtp.163.com");
			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.debug", "true");
			Session session = Session.getInstance(prop);

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("mxnzp_life@163.com"));
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
					to));
			msg.setSubject("来自购物天堂的激活邮件");
			msg.setContent(
					"<h1>购物天堂官方激活邮件！如果不能点击请复制到浏览器地址栏访问</h1><h3><a href='http://localhost:8080/Shop/user_active.action?code="
							+ code
							+ "'>http://localhost:8080/Shop/user_active.action?code="
							+ code + "</a></h3>", "text/html;charset=utf-8");

			Transport trans = session.getTransport();
			trans.connect("mxnzp_life@163.com", "cretin273846");
			trans.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		sendMail("792075058@qq.com","111");
	}
}
