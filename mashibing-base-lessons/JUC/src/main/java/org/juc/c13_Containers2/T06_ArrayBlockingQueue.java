package org.juc.c13_Containers2;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: 有界阻塞队列 <BR>
 * @author: zhao.song
 * @date: 2020/10/18 22:32
 * @version: 1.0
 ***********************/
public class T06_ArrayBlockingQueue {

    static ArrayBlockingQueue oArrayQueue = new ArrayBlockingQueue<String>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0,10).forEach(num->{
            try {
                oArrayQueue.put("a"+num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        oArrayQueue.put("aaa");//满了就会等待,程序阻塞
//        oArrayQueue.add("aaa");
//        oArrayQueue.offer("aaa");
//        oArrayQueue.offer("aaa", 1, TimeUnit.SECONDS);

        System.out.println(oArrayQueue);

    }


}
