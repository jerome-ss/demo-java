package com.jerome.tcpupd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * TCP 例子客户端
 *
 * @author jerome
 * @date 2017/3/2 15:30
 */
public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8888);
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        // 读取一行
        String str = bufferedReader.readLine();
        System.out.println(str);
    }
}
