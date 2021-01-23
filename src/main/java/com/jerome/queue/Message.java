package com.jerome.queue;

/**
 * 消息
 *
 * @author jerome
 * @date 2017/2/23 15:07
 */
public class Message {
    private String msg;

    public Message(String str) {
        this.msg = str;
    }

    public String getMsg() {
        return msg;
    }
}
