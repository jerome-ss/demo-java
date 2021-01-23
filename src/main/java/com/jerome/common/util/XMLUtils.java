package com.jerome.common.util;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xml转换工具类
 *
 * @author jerome
 * @date 2015年8月22日 上午9:35:13
 */
public class XMLUtils {

    public static void main(String[] args) {
        // xmlToMap Test
        /*
        <xml>
            <ToUserName><![CDATA[toUser]]></ToUserName>
            <FromUserName><![CDATA[fromUser]]></FromUserName>
            <CreateTime>1348831860</CreateTime>
        </xml>
        */
        String str = "<xml>\n" +
                "    <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "    <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                "    <CreateTime>1348831860</CreateTime>\n" +
                "</xml>";
        Map<String, String> xmlToMap = xmlToMap(str);
//        xmlToMap.forEach((key, value) -> System.out.println("key = " + key + ", value = " + value));

        // objectToXml Test
        class Student{
            private String name;
            private Integer age;

            public Student(String name, Integer age) {
                this.name = name;
                this.age = age;
            }
        }
        String objectToXml = objectToXml(new Student("jerome", 25));
        System.out.println(objectToXml);
    }

    /**
     * xml转为map集合
     *
     * @param str 字符串
     * @return Map集合
     */
    public static Map<String, String> xmlToMap(String str) {

        if (StringUtils.isBlank(str)) {
            return null;
        }

        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();

        try {
            InputStream ins = new ByteArrayInputStream(str.getBytes());
            Document doc = reader.read(ins);
            Element root = doc.getRootElement();

            List<Element> list = root.elements();
            for (Element element : list) {
                map.put(element.getName(), element.getText());
            }
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 对象转为xml
     *
     * @param obj 对象
     * @return xml字符串
     */
    public static String objectToXml(Object obj) {
        XStream xstream = new XStream();
        xstream.alias("xml", obj.getClass()); // 默认根元素为类名，这里改成xml
        return xstream.toXML(obj);
    }

}
