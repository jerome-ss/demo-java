package com.jerome.SipmleDateFormatSecurity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 创建多个SimpleDateFormat类的实例解决多线程错误的问题
 * 
 * @author jerome
 */
public class SipmleDateFormatSecurityOK {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

		MyThreadOK[] threadArr = new MyThreadOK[9];
		for (int i = 0; i < threadArr.length; i++) {
			threadArr[i] = new MyThreadOK(sdf, dateStringArr[i]);
		}

		for (int i = 0; i < threadArr.length; i++) {
			threadArr[i].start();
		}
	}
}

class MyThreadOK extends Thread {
	private String dateString;

	public MyThreadOK(SimpleDateFormat sdf, String dateString) {
		super();
		this.dateString = dateString;
	}

	@Override
	public void run() {
		try {
			// Date dateRef = sdf.parse(dateString);
			Date dateRef = DateTools.parse("yyyy-MM-dd", dateString);
			String newDateString = DateTools.format("yyyy-MM-dd", dateRef).toString();
			if (!newDateString.equals(dateString)) {
				System.out.println(
						"ThreadName=" + this.getName() + "报错了 日期字符串：" + dateString + "转化成的日期为：" + newDateString);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

class DateTools {
	public static Date parse(String formatPattern, String dateString) throws ParseException {
		return new SimpleDateFormat(formatPattern).parse(dateString);
	}

	public static String format(String formatPattern, Date date) {
		return new SimpleDateFormat(formatPattern).format(date).toString();
	}
}