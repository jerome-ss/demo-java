package com.jerome.tcpupd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端长连接Socket
 * 
 * @author JeromeThinkPad
 *
 */
public class SocketLongClient {
	private static Socket clientSocket; // 保持长连接
	public static void main(String[] args) throws UnknownHostException, IOException {
		new SocketLongClient().simple();
	}

	

	/**
	 * 简单Socket访问,长连接实现案例
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void simple() throws UnknownHostException, IOException {
		//int pushResultCode = 0;
		if (clientSocket == null || clientSocket.isClosed() == true || clientSocket.isConnected() == false) {
			// LOG.warn("push message to psx, create a new socket !");
			clientSocket = new Socket("localhost", 1920);
		}
		// 具体交互,记得不要关闭socket 和 input out 等流
		BufferedReader buf = null; // 声明BufferedReader对象接收信息
		buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // 取得客户端的输入流
		System.out.println("访问服务器获返回的内容为：" + buf.readLine());
	}
}
