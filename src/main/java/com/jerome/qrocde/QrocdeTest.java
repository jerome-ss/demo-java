package com.jerome.qrocde;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * @ClassName: Test
 * @Description:Java 生成二维码 (zxing)
 * @author jerome
 * @date 2015年9月29日 下午6:01:38
 * @see http://www.stepday.com/topic/?877
 * @see http://blog.csdn.net/gaogaoshan/article/details/38037849
 *
 * 也可以直接使用google的api生成二维码：
 */
public class QrocdeTest {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10000; i++) {
			 writerTxt("D://bigp.txt","bigp"+UUID.randomUUID());
//			 writerTxt("D://pack.txt", "pack" + UUID.randomUUID());
//			writerTxt("D://back.txt", "back" + UUID.randomUUID());
			System.out.println(i);
		}
		// readTxt("D://pack.txt");
	}

	/**
	 * 写入txt
	 * 
	 * @param path
	 *            具体路径
	 * @param str
	 *            写入内容
	 */
	private static void writerTxt(String path, String str) {
		BufferedWriter fw = null;
		try {
			File file = new File(path);
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
			fw.append(str);
			fw.newLine();
			fw.flush(); // 全部写入缓存中的内容
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
	/**
	 * 读txt文件
	 * @param path 具体路径
	 */
	@SuppressWarnings("unused")
	private static void readTxt(String path) {
		String filePath = path; // 文件和该类在同个目录下
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
			String str = null;
			while ((str = reader.readLine()) != null) {
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
	/**
	 * 生成二维码
	 * 
	 * @param text
	 * @throws WriterException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private static void getQrcode(String text) throws Exception {
		int width = 147;
		int height = 147;
		// 二维码的图片格式
		String format = "png";
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		BitMatrix bitMatrix2 = MatrixToImageWriter.deleteWhite(bitMatrix);
		// 生成二维码
		File outputFile = new File("d:" + File.separator +"pack"+File.separator+ text + ".png");
		MatrixToImageWriter.writeToFile(bitMatrix2, format, outputFile);
	}

}
