package com.jerome.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件操作工具类
 *
 * @author jerome
 * @date 2017/3/24 8:54
 */
public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    public static void main(String[] args) {
        String url = "http://img.blog.csdn.net/20160910094536677?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center";
        writeFileToDiskFromNetURL(url, "d:/temp.jpg");
    }

    /**
     * 将网络文件写入到磁盘
     *
     * @param netUrl   网络URL
     * @param fileName 文件保存时的名称(d:/img.jpg)
     */
    public static File writeFileToDiskFromNetURL(String netUrl, String fileName) {
        byte[] btImg = getFileFromNetByUrl(netUrl);
        if (null != btImg && btImg.length > 0) {
            LOGGER.info("读取到：" + btImg.length + " 字节");
            return writeFileToDisk(btImg, fileName);
        } else {
            LOGGER.warn("没有从该连接获得内容, url = {}", netUrl);
        }
        return null;

    }

    /**
     * 将文件写入到磁盘
     *
     * @param bytes    图片数据流
     * @param fileName 文件保存时的名称(d:/img.jpg)
     */
    public static File writeFileToDisk(byte[] bytes, String fileName) {

        try {
            File file = new File(fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(bytes);
            fops.flush();
            fops.close();
            LOGGER.info("文件已经写入到" + file.getAbsolutePath());
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warn("文件写入有误, fileName = {}, bytes.length = ", fileName, bytes.length);
        }
        return null;
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param netUrl 网络连接地址
     * @return 二进制数据
     */
    public static byte[] getFileFromNetByUrl(String netUrl) {
        try {
            URL url = new URL(netUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取数据
            InputStream inStream = conn.getInputStream();
            //得到二进制数据
            return readInputStream(inStream);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warn("文件不存在, url = {}, e = {} ", netUrl, e.toString());
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return 二进制数组
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
