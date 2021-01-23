package com.jerome.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 读取classpath下的配置文件"config.properties"
 */
public class GetCfgUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetCfgUtils.class);

    private static Properties pro = null;

    static {
        pro = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = GetCfgUtils.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream == null){
                inputStream = GetCfgUtils.class.getClassLoader().getResourceAsStream("application.properties");
            }

            if (inputStream != null) {
                pro.load(inputStream);
            }
            LOGGER.info("pro = {}", pro);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("config.properties is not found");
            throw new IllegalArgumentException("[config.properties] is not found!");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过key取值
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        String val = pro.getProperty(key.trim());
        if (val == null) {
            return "";
        }

        String str = null;
        try {
            str = new String(val.trim().getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        str = str == null ? "" : str.trim();
        return str;
    }

    /**
     * 通过key取值（如果通过此键key取不到值，那么返回默认值defaultStr）
     *
     * @param key
     * @param defaultStr
     * @return
     */
    public static String getDefaultValue(String key, String defaultStr) {
        // 如果通过此键key取不到值，那么返回默认值defaultStr
        String val = pro.getProperty(key.trim(), defaultStr).trim();
        String str = null;

        try {
            str = new String(val.trim().getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        str = str == null ? "" : str.trim();
        return str;
    }
}
