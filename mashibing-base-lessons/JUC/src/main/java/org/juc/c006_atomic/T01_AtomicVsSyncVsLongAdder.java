package org.juc.c006_atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/9/28 0:48
 * @version: 1.0
 ***********************/
public class T01_AtomicVsSyncVsLongAdder {

    static long count2 = 0;
    static AtomicLong count1 = new AtomicLong(0);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threadArr = new Thread[1000];

        for (int i = 0; i < threadArr.length; i++) {
            threadArr[i] = new Thread(() -> {
                for (int k = 0; k < 100_000; k++) {
                    count1.incrementAndGet();
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread thread : threadArr) thread.start();
        for (Thread thread : threadArr) thread.join();
        final long end = System.currentTimeMillis();


        System.out.println("Atomic: " + count1.get() + " time " + (end - start));
        //---------------

        final Object lock = new Object();
        for (int i = 0; i < threadArr.length; i++) {
            threadArr[i] = new Thread(()->{
                for (int k = 0; k < 100_000; k++) {
                    synchronized (lock) {
                        count2++;
                    }
                }
            });
        }
        long start00 = System.currentTimeMillis();
        for (Thread thread : threadArr) thread.start();
        for (Thread thread : threadArr) thread.join();
        final long end00 = System.currentTimeMillis();
        System.out.println("Sync: " + count1.get() + " time " + (end00 - start00));

        //------------------
        for (int i = 0; i < threadArr.length; i++) {
            threadArr[i] = new Thread(() -> {
                for (int k = 0; k < 100_000; k++) {
                    count3.increment();
                }
            });
        }
        long start000 = System.currentTimeMillis();
        for (Thread thread : threadArr) thread.start();
        for (Thread thread : threadArr) thread.join();
        final long end000 = System.currentTimeMillis();
        System.out.println("LongAdder: " + count1.get() + " time " + (end000 - start000));
    }
}
