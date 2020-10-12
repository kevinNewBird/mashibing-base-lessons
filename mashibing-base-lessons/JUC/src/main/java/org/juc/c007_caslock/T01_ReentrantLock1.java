package org.juc.c007_caslock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/9/30 0:49
 * @version: 1.0
 ***********************/
public class T01_ReentrantLock1 {

    private ReentrantLock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1:" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        lock.lock();
        System.out.println("m2 is running.");
//        lock.unlock();
    }


    public static void main(String[] args) {
        final T01_ReentrantLock1 instance = new T01_ReentrantLock1();

        new Thread(() -> instance.m2()).start();
        new Thread(() -> instance.m1()).start();

    }

}
