package org.juc.c13_Containers2;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/15 16:53
 * @version: 1.0
 ***********************/
public class T01_ConcurrentMap {

    public static void main(String[] args) throws InterruptedException {

//        Map<String, String> map = new ConcurrentHashMap<>();
        Map<String, String> map = new ConcurrentSkipListMap<>();//数据结构:跳表; 高并发并且排序

        Random r = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        final long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                IntStream.range(0, 10000).forEach(num -> {
                    map.put("a" + r.nextInt(100_000), "a" + r.nextInt(100_000));
                    latch.countDown();
                });
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        latch.await();

        final long end = System.currentTimeMillis();

        System.out.println(end - start);
        System.out.println(map.size());
    }
}
