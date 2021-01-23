package com.jerome.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者
 *
 * @author jerome
 * @date 2017/2/23 15:07
 */
public class Producer implements Runnable {

    private BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        //produce messages
        for (int i = 0; i < 100; i++) {
            Message msg = new Message(String.valueOf(i));
            try {
                Thread.sleep(i);
                queue.put(msg);
                System.out.println("Produced " + msg.getMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //adding exit message
        Message msg = new Message("exit");
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
