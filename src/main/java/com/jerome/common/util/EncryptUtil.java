package com.jerome.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jerome
 * @ClassName: EncryptUtil
 * @Description: 加密解密工具类
 * @date 2015年9月8日 下午2:02:50
 */
public class EncryptUtil {

    public static void main(String[] args) {
        System.out.println(SHA1("jerome"));
    }

    /**
     * SHA1加密
     *
     * @param str 传入加密字符串
     * @return 加密完的字符串
     */
    public static String SHA1(String str) {
        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = mDigest.digest(str.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }


}
