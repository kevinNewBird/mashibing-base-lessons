package org.juc.c13_Containers2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/15 17:28
 * @version: 1.0
 ***********************/
public class T02_CopyOnWriteList {

    public static void main(String[] args) throws InterruptedException {
        List<String> lists =
//                new ArrayList<>();//这个会出现并发问题!
//                new Vector<>();
                new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                IntStream.range(0, 1000).forEach(num -> lists.add("a" + r.nextInt(10000)));
            });
        }

        runAndComputeTime(threads);

        System.out.println(lists.size());
    }


    static void runAndComputeTime(Thread[] threads) throws InterruptedException {
        final long start = System.currentTimeMillis();
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        final long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
