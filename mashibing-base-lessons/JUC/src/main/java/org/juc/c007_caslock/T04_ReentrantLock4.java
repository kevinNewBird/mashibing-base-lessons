package org.juc.c007_caslock;

import java.util.concurrent.locks.ReentrantLock;

/***********************
 * Description: 公平锁 <BR>
 * @author: zhao.song
 * @date: 2020/10/2 22:10
 * @version: 1.0
 ***********************/
public class T04_ReentrantLock4 {

    private ReentrantLock lock = new ReentrantLock(true);//参数为true, 表示为公平锁

    void m1(){
        for (int i = 0; i < 100; i++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " is running. the value is " + i);
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final T04_ReentrantLock4 instance = new T04_ReentrantLock4();
        new Thread(()->instance.m1(),"t1").start();
        new Thread(()->instance.m1(),"t2").start();
    }

}
