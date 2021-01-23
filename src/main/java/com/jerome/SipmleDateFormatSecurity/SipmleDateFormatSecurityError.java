package com.jerome.SipmleDateFormatSecurity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * SipmleDateFormat不是线程安全的，多线程下面可能出现问题
 * 
 * @author jerome
 */
public class SipmleDateFormatSecurityError {
	public static void main(String[] args) {
		// 单例的SimpleDateFormat类在多线程环境中容易出现日期转换错误
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
			@Override
			protected SimpleDateFormat initialValue() {
				return new SimpleDateFormat("yyyyMMdd HHmm");
			}
		};
		
		// 9个数组
		String[] dateStringArr = new String[]{
			"2016-05-16",
			"2016-05-17",
			"2016-05-18",
			"2016-05-19",
			"2016-05-20",
			"2016-05-21",
			"2016-05-22",
			"2016-05-23",
			"2016-05-24"
		};
		
		MyThread[] threadArr = new MyThread[9];
		for (int i = 0; i < threadArr.length; i++) {
			//threadArr[i] = new MyThread(sdf, dateStringArr[i]);
			threadArr[i] = new MyThread(sdf.get(), dateStringArr[i]);
		}
		
		for (int i = 0; i < threadArr.length; i++) {
			threadArr[i].start();
		}
	}
}

class MyThread extends Thread {
	private SimpleDateFormat sdf;
	private String dateString;

	public MyThread(SimpleDateFormat sdf, String dateString) {
		super();
		this.sdf = sdf;
		this.dateString = dateString;
	}

	@Override
	public void run() {
		try {
			Date dateRef = sdf.parse(dateString);
			String newDateString = sdf.format(dateRef).toString();
			if (!newDateString.equals(dateString)) {
				System.out.println(
						"ThreadName=" + this.getName() + "报错了 日期字符串：" + dateString + "转化成的日期为：" + newDateString);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}