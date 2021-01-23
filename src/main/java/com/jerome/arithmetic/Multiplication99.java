package com.jerome.arithmetic;

public class Multiplication99 {

	public static void main(String[] args) {
		function1();
		function2();
		function3();
	}

	public static void function1() {
		// 九九乘法表
		for (int i = 1; i <= 9; i++) {
			System.out.println();
			for (int j = 1; j <= i; j++) {
				System.out.print(j + "*" + i + "=" + i * j + "\t");
			}
		}
	}

	public static void function2() {
		// 九九乘法表
		for (int i = 1; i <= 9; i++) {
			System.out.println();
			for (int j = 1; j <= i; j++) {
				System.out.print(j + "*" + i + "=" + i * j + "\t");
			}
		}
	}

	public static void function3() {
		// 九九乘法表3
		for (int i = 1; i <= 9; i++) {
			System.out.println();
			for (int k = 0; k < 9 - i; k++) {
				System.out.print("      \t");
			}
			for (int j = i; j >= 1; j--) {
				System.out.print(j + "*" + i + "=" + i * j + "\t");
			}
		}
	}
}
