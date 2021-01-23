package com.jerome.tcpupd;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * TCP 例子服务端
 * 先运行服务端在运行客户端TCPClient.java
 *
 * @author jerome
 * @date 2017/3/2 15:30
 */
public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        // 开始监听来自客户端的请求，将阻塞等待客户端请求
        Socket socket = serverSocket.accept();
        // 获得输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        // 向输出流中输出当前时间
        printWriter.print("now time = " + new Date());
        printWriter.flush();
        printWriter.close();
        socket.close();
        serverSocket.close();
    }
}
