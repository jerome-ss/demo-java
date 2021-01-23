package com.jerome.efficient;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

	/**
	 * 遍历 Map 中的 Key 和 Value 效率最高的方式是
	 *
	 * @author jerome
	 */
	@Test
	public void foreachMapKeyValue() {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("111", "222");
		hm.put("333", "444");

		// 遍历key value
		Set<Map.Entry<String, String>> entrySet = hm.entrySet();
		Iterator<Map.Entry<String, String>> iter = entrySet.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
	}

	/**
	 * 遍历 Map 中的 Key 效率最高的方式是
	 *
	 * @author jerome
	 */
	@Test
	public void foreachMapKey() {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("111", "222");
		hm.put("333", "444");

		// 只遍历key
		Set<String> keySet = hm.keySet();
		Iterator<String> iterKey = keySet.iterator();
		while (iterKey.hasNext()) {
			System.out.println(iterKey.next());
		}
	}
}
