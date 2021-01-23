package com.jerome.tcpupd;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * TCP 例子服务端，多线程模型
 * 先运行服务端在运行客户端TCPClient.java
 *
 * @author jerome
 * @date 2017/3/2 15:30
 */
public class TCPServerMultiThread {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        // 开始监听来自客户端的请求，将阻塞等待客户端请求
        Socket socket = null;
        // 把accept()方法作为循环条件，循环监听客户端请求
        while ((socket = serverSocket.accept()) != null) {
            System.out.println("开启新线程");
            new MyThread(socket).start();
        }
        serverSocket.close();
    }
}

class MyThread extends Thread {
    private Socket socket;

    public MyThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 获得输出流
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            // 向输出流中输出当前时间
            printWriter.print("now time = " + new Date());
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}