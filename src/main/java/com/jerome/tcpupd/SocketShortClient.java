package com.jerome.tcpupd;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端Socket
 * 
 * @author JeromeThinkPad
 * @see 参考 1. 《Java开发实战经典》
 * @see 参考 2. 网络
 *
 */
public class SocketShortClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//new SocketShortClient().simple();
	}

	/**
	 * 回应程序
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void echo() throws UnknownHostException, IOException {
		Socket client = null; // 表示客 户端
		client = new Socket("localhost", 8888);
		BufferedReader buf = null; // 一次性接收完成
		PrintStream out = null; // 发送数据
		BufferedReader input = null; // 接收键盘数据
		input = new BufferedReader(new InputStreamReader(System.in));
		buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintStream(client.getOutputStream());
		boolean flag = true; // 定义标志位
		while (flag) {
			System.out.print("输入信息：");
			String str = input.readLine(); // 接收键盘的输入信息
			out.println(str);
			if ("bye".equals(str)) {
				flag = false;
			} else {
				String echo = buf.readLine(); // 接收返回结果
				System.out.println(echo); // 输出回应信息
			}
		}
		buf.close();
		client.close();
	}

	/**
	 * 简单Socket访问
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void simple() throws UnknownHostException, IOException {
		Socket client = null;
		client = new Socket("siponets.-.com",1920);
		BufferedReader buf = null; // 声明BufferedReader对象接收信息
		buf = new BufferedReader(new InputStreamReader(client.getInputStream())); // 取得客户端的输入流
		System.out.println("访问服务器获返回的内容为：" + buf.readLine());
		client.close();
		buf.close();
	}
	
	/**
	 * 非阻塞模式
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void unAccept() throws UnknownHostException, IOException {
		Socket client = null;
		client = new Socket("siponets.-.com", 1920);
		InputStream in = null; // 接收流
		// 接收服务器端响应(非阻塞)
		byte[] buf = new byte[2048];
		in = client.getInputStream();
		int i = in.read(buf);
		client.setSoTimeout(5000); // 超时时间五秒
		String response = new String(buf, 12, i);
		in.close();
		client.close();
	}
	
}
