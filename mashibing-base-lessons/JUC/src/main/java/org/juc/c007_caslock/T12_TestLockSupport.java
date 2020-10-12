package org.juc.c007_caslock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/4 11:13
 * @version: 1.0
 ***********************/
public class T12_TestLockSupport {

    public static void main(String[] args) {
        final Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    if (i == 5) {
                        LockSupport.park();
//                        final Object blocker = LockSupport.getBlocker(Thread.currentThread());
//                        LockSupport.park(LockSupport.getBlocker(Thread.currentThread()));
                    }
                    System.out.println(i);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 8 seconds!");
        LockSupport.unpark(t);
    }
}
