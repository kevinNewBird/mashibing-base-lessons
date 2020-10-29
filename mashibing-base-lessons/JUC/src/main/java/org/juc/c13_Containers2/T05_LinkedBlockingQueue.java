package org.juc.c13_Containers2;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: 无界阻塞队列 <BR>
 * @author: zhao.song
 * @date: 2020/10/15 18:13
 * @version: 1.0
 ***********************/
public class T05_LinkedBlockingQueue {

    //链表实现的阻塞队列(BlockingQueue)
    static LinkedBlockingQueue<String> oLinkQueue = new LinkedBlockingQueue<>();

    static Random r = new Random();

    public static void main(String[] args) {

        //写线程
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {

                try {
                    oLinkQueue.put("a" + i);//如果满了,就会等待
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //读线程
        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                while (true) {
                    try {
                        //如果空了,就会等待
                        System.out.println(Thread.currentThread().getName() + " take - " + oLinkQueue.take());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        });


    }
}
