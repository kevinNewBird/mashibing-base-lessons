package org.juc.c13_Containers2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/19 20:10
 * @version: 1.0
 ***********************/
public class T08_SynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<String> queue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                while (true)
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        queue.put("aaa");//没有被获取就会阻塞住
        queue.put("bbb");//没有被获取就会阻塞住

        System.out.println(queue.size());
    }
}
