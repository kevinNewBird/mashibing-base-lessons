package org.juc.c007_caslock;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/2 22:47
 * @version: 1.0
 ***********************/
public class T05_ReentrantLock5 implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();


    public ReentrantLock getLock() {
        return lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " - " + i + " : get the lock.");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final T05_ReentrantLock5 runnable = new T05_ReentrantLock5();
        final Thread t1 = new Thread(runnable, "t1");
        final Thread t2 = new Thread(runnable, "t2");
        final Thread t3 = new Thread(runnable, "t3");
        t1.start();
        t2.start();
        t3.start();

    }
}
