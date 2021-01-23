package com.jerome.enum1;

/**
 * 
 * @ClassName: EquipmentSize
 * @Description: 设备尺寸枚举
 * @author jerome
 * @date 2015年11月9日 下午6:22:35
 * 一般在Hibernate或者MyBatis只能存序列(0开始)或者名字如：TEN。
 * 而我们数据库一般都是直接存数字的，而且数字偶尔不从0开始，所以可以存这里的getKey()
 * 但是以后一般最好数据库直接存名字或者 从0开始按顺序的下标比较好
 */
public enum EquipmentSize {

	TEM(1, "10寸"), TWENTYTWO(2, "22寸");

	private int key;
	private String value;

	private EquipmentSize(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
