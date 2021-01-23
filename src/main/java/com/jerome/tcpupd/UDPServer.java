package com.jerome.tcpupd;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 服务端
 * 接收端，先启动
 *
 * @author JeromeThinkPad
 * @see 《Java开发实战经典》
 */
public class UDPServer {
    public static void main(String args[]) throws Exception {
        // 定义接收数据报的对象，客户端在9000端口上等待服务器发送信息
        DatagramSocket ds = new DatagramSocket(9000);
        // 开辟空间，以接收数据
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, 1024);
        // 接收数据
        ds.receive(dp);
        String str = new String(dp.getData(), 0, dp.getLength());
        System.out.println(str + "from " + dp.getAddress().getHostAddress() + "：" + dp.getPort());
        ds.close();
    }
}