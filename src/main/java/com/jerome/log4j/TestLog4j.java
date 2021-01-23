package com.jerome.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: TestLog4j 
 * @Description: log4j例子 
 * @author jerome
 * @date 2015年11月3日 下午2:23:31 
 * 详细说明参考 链接：http://pan.baidu.com/s/1gdnWhYN 密码：nb9s
 * 需要添加两个jar 和 log4j.properties
 *	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>slf4j-api</artifactId>
		<version>1.7.12</version>
	</dependency>
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>slf4j-log4j12</artifactId>
		<version>1.7.12</version>
	</dependency>
 */
public class TestLog4j {
	public static void main(String[] args) {
//		 private Logger logger = LoggerFactory.getLogger(this.getClass()); // 日志记录
		Logger logger = LoggerFactory.getLogger(TestLog4j.class); // 日志记录
		logger.debug("测试debug级别日志");
		logger.info("测试info级别日志");
		logger.warn("测试warn级别日志");
		logger.error("测试error级别日志");
		System.out.println("测试结束");
	}
	
}
