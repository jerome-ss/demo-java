package com.jerome.json;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {
	public static void main(String[] args) {

		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("legend", Arrays.asList(new String[] { "销售认购金额", "销售认购金额2" }));
//		map.put("category", Arrays.asList(new String[] { "年度认购金额", "年度认购指数", "全案认购金额", "全案认购指数" }));
//		map.put("series", Arrays.asList(new ArrayList<Integer>(Arrays.asList(21, 23, 28, 26)),
//				new ArrayList<Integer>(Arrays.asList(25, 22, 21, 21))));
		map.put("number", 2);
		List<Long> list = new ArrayList<Long>();
		list.add(2L);
		list.add(5L);
		map.put("community", list);
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json.toString());
	}
}
