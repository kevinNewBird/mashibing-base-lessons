package org.juc.c007_caslock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/3 22:16
 * @version: 1.0
 ***********************/
public class T10_TestSemaphore {

    public static void main(String[] args) {
        final Semaphore sem = new Semaphore(1);
        //公平锁
//        final Semaphore sem = new Semaphore(1,true);

        new Thread(() -> {
            try {
                sem.acquire();
                System.out.println("T1 is running...");
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("T1 end!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                sem.acquire();
                System.out.println("T2 is running...");
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("T2 end!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release();
            }
        }, "T2").start();
    }
}
