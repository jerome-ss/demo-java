package com.jerome.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 密码生成工具类
 * 
 * @author jerome
 */
public class PwdUtils {
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			System.out.println(getPwd(6));
		}
	}

	/**
	 * 生成密码 （至少包含一个数字、一个小写字母、一个大写字母）
	 * 
	 * @param pwdLength
	 *            密码长度
	 * @return 具体生成的密码
	 */
	public static String getPwd(int pwdLength) {
		String numStrArr[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String lowWordStrArr[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z" };
		String uppWordStrArr[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
				"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		List<String> pwdList = new ArrayList<>();
		Random rand = new Random();
		int idnex = 0;
		for (int i = 0; i < pwdLength; i++) {
			if (i % 3 == 0) {
				idnex = rand.nextInt(numStrArr.length - 1);
				pwdList.add(numStrArr[idnex]);
			} else if (i % 3 == 1) {
				idnex = rand.nextInt(lowWordStrArr.length - 1);
				pwdList.add(lowWordStrArr[idnex]);
			} else {
				idnex = rand.nextInt(uppWordStrArr.length - 1);
				pwdList.add(uppWordStrArr[idnex]);
			}
		}

		Collections.shuffle(pwdList); // 随机排序
		StringBuffer sbPwd = new StringBuffer();
		for (String str : pwdList) {
			sbPwd.append(str);
		}
		return sbPwd.toString();
	}

}