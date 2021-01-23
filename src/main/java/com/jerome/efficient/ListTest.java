package com.jerome.efficient;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		List<String> arrayList = new ArrayList<>(10); // 尽可能的给List或者Map分
		// 配一个初始容量因为List初始化，有足够的容量，所有这样可以减少内部数组在运行
		// 时不必要的分配和释放。如果你不知道确定的大小，最好估算一下这个值的平均值，
		// 添加一些缓冲，防止意外溢出。
		
	}
}
