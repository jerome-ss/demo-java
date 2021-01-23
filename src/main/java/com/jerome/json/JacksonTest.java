package com.jerome.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * @author jerome
 * @date 2017/3/21 15:05
 */
public class JacksonTest {
	public static void main(String[] args) throws IOException {
		json2Model();
	}

	/**
	 * 序列化
	 */
	public static void list2Json() throws IOException {
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Person p = new Person();
			p.setName("name" + i);
			p.setAge(i * 5);
			persons.add(p);
		}

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(persons);
		System.out.println(jsonString);
	}

	/**
	 * 反序列化
	 */
	public static void json2Model() throws IOException {
		String jsonStr = "{\"name\":\"name0\",\"age\":10}";
		ObjectMapper mapper = new ObjectMapper();
		Person person = mapper.readValue(jsonStr, Person.class);
		System.out.println(person.toString());
	}

}
