package com.jerome.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 简单线程池例子
 *
 * @author jerome
 * @date 2017/2/23 15:42
 */
public class SimpleThreadPool {

    public static void main(String[] args) {

        // http://www.cnblogs.com/SKILL0825/p/5971539.html
        // Java通过Executors提供四种线程池，分别为：
        // 1. newCachedThreadPool：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        //      线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
        // 2. newFixedThreadPool：创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。（超过太多可能OOM了）
        // 3. newScheduledThreadPool：创建一个定长线程池，支持定时及周期性任务执行。
        // 4. newSingleThreadExecutor：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

        // 也可以通过 ThreadPoolExecutor 灵活创建线程池 可以指定池子最大值等参数，避免如下方式创建过多线程导致OOM
        ExecutorService executorPool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread(String.valueOf(i));
            executorPool.execute(worker);

            // 返回此执行程序的任务队列.当前会从第5个线程才会开始堆积任务等待处理.堆积的数量可以达到Integer.MAX_VALUE,有OOM风险
            // 可用作于监控线程任务队列情况,如果任务太多处理不来可触发阀值并Thread.sleep(sleepTimes);
            System.out.println(((ThreadPoolExecutor) executorPool).getQueue().size());
        }

        executorPool.shutdown();
        //wait for all tasks to finish
        while (!executorPool.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}


