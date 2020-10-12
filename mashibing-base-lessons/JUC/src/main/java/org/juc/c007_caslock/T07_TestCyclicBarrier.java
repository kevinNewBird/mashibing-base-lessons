package org.juc.c007_caslock;

import java.util.Optional;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/3 11:28
 * @version: 1.0
 ***********************/
public class T07_TestCyclicBarrier {

    public static void main(String[] args) {
        final CyclicBarrier oBarrier = new CyclicBarrier(5, ()->{
            Optional.of("the thread ["+Thread.currentThread().getName()+"],满足5个放行!")
                    .ifPresent(System.out::println);
        });

        for (int i = 0; i < 20; i++) {
            final int num = i;
            new Thread(()->{
                try {
                    Optional.of("the thread [" + Thread.currentThread().getName() + "]" +
                            ",output the value " +num).ifPresent(System.out::println);
                    oBarrier.await();
                } catch (InterruptedException|BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
