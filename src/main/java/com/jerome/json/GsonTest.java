package com.jerome.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: TestJson
 * @Description:
 * @author jerome
 * @date 2015年9月30日 下午4:52:45
 * 
 */
public class GsonTest {
	public static void main(String[] args) {
		list2Json();
	}
	
	//序列化
	public static void list2Json() {
		Gson gson = new Gson();
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 5; i++) {
			Person p = new Person();
			p.setName("name" + i);
			p.setAge(i * 5);
			persons.add(p);
		}
		String str = gson.toJson(persons);
		System.out.println(str);
	}
	
	//反序列化
	public static void json2Model() {
		String str = "{\"name\":\"name0\",\"age\":10}";
		Gson gson = new Gson();
		Person person = gson.fromJson(str, Person.class); 
		System.out.println(person.toString());
	}
	
	//反序列化
	public static void json2List() {
		String str = "[{\"name\":\"name0\",\"age\":0},{\"name\":\"name1\",\"age\":5},{\"name\":\"name2\",\"age\":10}]";
		Gson gson = new Gson();
		List<Person> ps = gson.fromJson(str, new TypeToken<List<Person>>() {
		}.getType());
		for (int i = 0; i < ps.size(); i++) {
			Person p = ps.get(i);
			System.out.println(p.toString());
		}
	}
}
