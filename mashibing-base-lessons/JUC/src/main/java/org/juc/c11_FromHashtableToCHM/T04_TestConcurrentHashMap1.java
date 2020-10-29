package org.juc.c11_FromHashtableToCHM;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/13 23:39
 * @version: 1.0
 ***********************/
public class T04_TestConcurrentHashMap1 {

    static int count = Constants.COUNT;
    static Map<UUID, UUID> m = new ConcurrentHashMap<>();

    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];


    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        private int start;
        static int gap = count / Constants.THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final long pStart = System.currentTimeMillis();
        Thread[] threads = new Thread[Constants.THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i * (count / Constants.THREAD_COUNT));
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        final long pEnd = System.currentTimeMillis();
        System.out.println(pEnd - pStart);
        System.out.println(m.size());

//-----------------------------------------------------

        final long rStart = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        final long rEnd = System.currentTimeMillis();
        System.out.println(rEnd - rStart);
        System.out.println(m.size());

    }
}
