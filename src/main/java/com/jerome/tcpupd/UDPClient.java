package com.jerome.tcpupd;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 客户端
 * 发送端
 *
 * @author JeromeThinkPad
 * @see 《Java开发实战经典》
 */
public class UDPClient {
    public static void main(String args[]) throws Exception {
        // 定义发送数据报的对象，服务端在3000端口上等待服务器发送信息
        DatagramSocket ds = new DatagramSocket(3000);
        String str = "hello World!!!";
        DatagramPacket dp = new DatagramPacket(
                str.getBytes(),
                str.length(),
                InetAddress.getByName("localhost"),
                9000);
        ds.send(dp);
        ds.close();
    }
}