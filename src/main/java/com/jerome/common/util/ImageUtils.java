package com.jerome.common.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author jerome
 * @ClassName: ImageUtil
 * @Description: 图片相关操作
 * @date 2016年1月6日 下午2:02:08
 */
public class ImageUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);

    public static void main(String[] args) {
        CompressionByQuality(new File("D:\\1.png"), 0.5f, "D:\\12.png");
        CompressionByQuality(new File("D:\\2.png"), 0.5f, "D:\\122.png");
        CompressionByQuality(new File("D:\\3.jpg"), 0.5f, "D:\\123.jpg");
        CompressionByQuality(new File("D:\\4.jpg"), 0.5f, "D:\\124.jpg");
        CompressionByQuality(new File("D:\\5.jpg"), 0.5f, "D:\\125.jpg");
        CompressionByQuality(new File("D:\\6.jpg"), 0.5f, "D:\\126.jpg");
//		CompressionByQuality(new File("D:\\1.jpg"), 0.5f, "D:\\22.jpg");
//		CompressionBySize(new File("D:\\temp.jpg"), 124, 46, "D:\\todo1.jpg");
    }

    /**
     * 压缩图片(牺牲图片质量)
     *
     * @param file               要压缩的文件
     * @param compressionQuality 压缩程度(0-10)越高质量越好,容量也越大
     * @param savePath           保存的路径
     * @return 成功/失败
     */
    public static boolean CompressionByQuality(File file, float compressionQuality, String savePath) {

        ImageWriteParam imgWriteParams = new JPEGImageWriteParam(null);
        imgWriteParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT); // 指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionQuality(compressionQuality); // 压缩程度
        imgWriteParams.setProgressiveMode(JPEGImageWriteParam.MODE_DISABLED);

        FileOutputStream fileOutputStream = null;
        try {
            // 指定压缩时使用的色彩模式
            ColorModel colorModel = ImageIO.read(file).getColorModel();
            imgWriteParams.setDestinationType(
                    new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

            ImageWriter imgWrier = ImageIO.getImageWritersByFormatName("jpg").next(); // 写的方式为jpg
            imgWrier.reset(); // 将ImageWriter恢复到其初始状态。
            fileOutputStream = new FileOutputStream(savePath);
            imgWrier.setOutput(ImageIO.createImageOutputStream(fileOutputStream));
            BufferedImage bufferedImage = ImageIO.read(file);
            imgWrier.write(null, new IIOImage(bufferedImage, null, null), imgWriteParams);
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("图片压缩成功！savePath=" + savePath);
        return true;
    }

    /**
     * 压缩图片(牺牲大小)
     *
     * @param file     文件
     * @param width    宽度
     * @param height   高度
     * @param savePath 保存的路径
     * @return 成功/失败
     */
    public static boolean CompressionBySize(File file, int width, int height, String savePath) {
        BufferedImage src = null;
        FileOutputStream out = null;
        try {
            src = ImageIO.read(file);
            // 参数可以参考: http://www.apihome.cn/api/java/BufferedImage.html
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null); // 绘制缩小后的图
            out = new FileOutputStream(savePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag); // JPEG编码
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("图片压缩成功！savePath=" + savePath);
        return true;
    }
}
