package org.juc.c007_caslock;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/2 23:20
 * @version: 1.0
 ***********************/
public class T06_TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        usingCountDownLatch();
    }

    private static void usingCountDownLatch() throws InterruptedException {
        Thread[] threads = new Thread[100];
        final CountDownLatch latch = new CountDownLatch(threads.length);
        IntStream.range(0, threads.length).forEach(num -> {
            threads[num] = new Thread(() -> {
                Optional.of("the thread ["+Thread.currentThread().getName() + "] is running.")
                        .ifPresent(System.out::println);
                latch.countDown();
            }, num + "");
        });

        for (Thread thread : threads) thread.start();

        latch.await();

        System.out.println("end latch.");
    }


}
