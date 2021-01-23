package com.jerome.tcpupd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端Socket
 * 
 * @author JeromeThinkPad
 * @see 《Java开发实战经典》
 *
 */
public class SocketShortServer {
	public static void main(String[] args) throws IOException {
		// 多线程
		ServerSocket server = null; // 定义ServerSocket类
		Socket client = null; // 表示客 户端
		server = new ServerSocket(1920); // 服务器在8888端口上监听
		boolean f = true; // 定义个标记位
		while (f) {
			System.out.println("服务器运行，等待客户端连接。");
			client = server.accept(); // 得到连接，程序进入到阻塞状态
			new Thread(new EchoThread(client)).start(); // 每一个客户端表示一个线程
		}
		server.close();
	}

	/**
	 * 回应程序
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void echo() throws Exception {
		ServerSocket server = null; // 定义ServerSocket类
		Socket client = null; // 表示客 户端
		BufferedReader buf = null; // 接收输入流
		PrintStream out = null; // 打印流输出最方便
		server = new ServerSocket(8888); // 服务器在8888端口上监听
		boolean f = true; // 定义个标记位
		while (f) {
			System.out.println("服务器运行，等待客户端连接。");
			client = server.accept(); // 得到连接，程序进入到阻塞状态
			out = new PrintStream(client.getOutputStream());
			// 准备接收客户端的输入信息
			buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
			boolean flag = true; // 标志位，表示可以一直接收并回应信息
			while (flag) {
				String str = buf.readLine(); // 接收客户端发送的内容
				if (str == null || "".equals(str)) { // 表示没有内容
					flag = false; // 退出循环
				} else {
					if ("bye".equals(str)) { // 如果输入的内容为bye表示结束
						flag = false;
					} else {
						out.println("ECHO : " + str); // 回应信息
					}
				}
			}
			client.close();
		}
		server.close();
	}

	/**
	 * 简单Socket连接
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void simple() throws IOException {
		ServerSocket server = null;
		Socket client = null; // 一个Socket表示一个客户端
		PrintStream out = null; // 向客户端输出
		server = new ServerSocket(8888);
		System.out.println("服务器运行,等待客户端连接...");

		client = server.accept();// 程序阻塞,等待客户端连接
		out = new PrintStream(client.getOutputStream());
		out.print("hello world!");
		client.close();
		server.close();
	}
}

class EchoThread implements Runnable {
	private Socket client = null;

	public EchoThread(Socket client) {
		this.client = client;
	}

	public void run() {
		BufferedReader buf = null; // 接收输入流
		PrintStream out = null; // 打印流输出最方便
		try {
			out = new PrintStream(client.getOutputStream());
			// 准备接收客户端的输入信息
			buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
			boolean flag = true; // 标志位，表示可以一直接收并回应信息
			while (flag) {
				String str = buf.readLine(); // 接收客户端发送的内容
				if (str == null || "".equals(str)) { // 表示没有内容
					flag = false; // 退出循环
				} else {
					if ("bye".equals(str)) { // 如果输入的内容为bye表示结束
						flag = false;
					} else {
						out.println("ECHO : " + str); // 回应信息
					}
				}
			}
			client.close();
		} catch (Exception e) {
		}

	}
}