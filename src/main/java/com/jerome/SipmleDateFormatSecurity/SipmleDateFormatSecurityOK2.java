package com.jerome.SipmleDateFormatSecurity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * ThreadLocal能使多线程绑定到指定的对象，使用该类可以解决多线程环境下SimpleDateFormat类处理错误的问题
 * 
 * @author jerome
 */
public class SipmleDateFormatSecurityOK2 {
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

		MyThreadOK2[] threadArr = new MyThreadOK2[9];
		for (int i = 0; i < threadArr.length; i++) {
			threadArr[i] = new MyThreadOK2(sdf, dateStringArr[i]);
		}
		for (int i = 0; i < threadArr.length; i++) {
			threadArr[i].start();
		}
	}
}

class MyThreadOK2 extends Thread {
	private String dateString;

	public MyThreadOK2(SimpleDateFormat sdf, String dateString) {
		super();
		this.dateString = dateString;
	}

	@Override
	public void run() {
		try {
			// Date dateRef = sdf.parse(dateString);
			Date dateRef = DateTools2.getSimpleDateFormat("yyyy-MM-dd").parse(dateString);
			String newDateString = DateTools2.getSimpleDateFormat("yyyy-MM-dd").format(dateRef).toString();
			if (!newDateString.equals(dateString)) {
				System.out.println(
						"ThreadName=" + this.getName() + "报错了 日期字符串：" + dateString + "转化成的日期为：" + newDateString);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

class DateTools2 {
	private static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>();

	public static SimpleDateFormat getSimpleDateFormat(String datePattern) {
		SimpleDateFormat sdf = null;
		sdf = tl.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(datePattern);
			tl.set(sdf);
		}
		return sdf;
	}
}