package com.jerome.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * 阿里巴巴的fastJson
 * 
 * @see 文档：https://github.com/alibaba/fastjson/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%
 *      E9%A2%98
 * @author JeromeThinkPad
 *
 */
public class FastJsonTest {
	public static void main(String[] args) {
		json2List();
	}

	/**
	 * 序列化
	 */
	public static void list2Json() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 5; i++) {
			Person p = new Person();
			p.setName("name" + i);
			p.setAge(i * 5);
			persons.add(p);
		}
		String jsonString = JSON.toJSONString(persons);
		System.out.println(jsonString);
	}

	/**
	 * 反序列化
	 */
	public static void json2Model() {
		String jsonStr = "{\"name\":\"name0\",\"age\":10}";
		Person person =  JSON.parseObject(jsonStr, Person.class);
		System.out.println(person.toString());
	}

	// 反序列化
	public static void json2List() {
		String jsonStr = "[{\"name\":\"name0\",\"age\":0},{\"name\":\"name1\",\"age\":5},{\"name\":\"name2\",\"age\":10}]";
		List<Person> ps = JSON.parseObject(jsonStr, new TypeReference<List<Person>>() {});
		for (int i = 0; i < ps.size(); i++) {
			Person p = ps.get(i);
			System.out.println(p.toString());
		}
	}
}
