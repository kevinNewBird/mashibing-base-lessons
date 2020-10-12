package org.juc.c007_caslock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/2 20:14
 * @version: 1.0
 ***********************/
public class T02_ReentrantLock2 {
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 使用tryLock进行尝试锁定 , 不管锁定与否 , 方法将继续执行;
     * 也可以根据tryLock的返回值来判定是否锁定 ;
     * 也可以指定tryLock的时间 , 由于tryLock(time)抛出异常 , 所以要注意unlock的处理, 必须放到finally中
     */
    void m1(){
        boolean isLock = false;
        try{
            isLock = lock.tryLock();
            System.out.println("the thread :"+Thread.currentThread().getName()+" ,m1 is lock: " + isLock);
        }finally {
            if (isLock) {
                lock.unlock();
            }

        }
    }

    void m2() {
        boolean isLock = false;
        try {
            isLock = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 is lock: " + isLock);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (isLock) {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        final T02_ReentrantLock2 instance = new T02_ReentrantLock2();
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        IntStream.range(0,2).forEach(num->new Thread(()->{
            instance.m1();
            countDownLatch.countDown();
        },String.valueOf(num)).start());

        countDownLatch.await();
    }

}
