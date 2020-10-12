package org.juc.c007_caslock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/2 20:28
 * @version: 1.0
 ***********************/
public class T03_ReentrantLock3 {

    private ReentrantLock lock = new ReentrantLock();

    void m1(){
        try{
            lock.lock();
            System.out.println("m1 start...");
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            System.out.println("m2 end.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void m2(){
        try{
            lock.lockInterruptibly();//可以对interrupt()方法做出响应
            System.out.println("m2 start...");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("m2 end.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final T03_ReentrantLock3 instance = new T03_ReentrantLock3();

        new Thread(()->instance.m1(),"m1").start();
        TimeUnit.SECONDS.sleep(1);

        final Thread t2 = new Thread(() -> instance.m2(),"m2");
        t2.start();

        t2.interrupt();//打断线程2的等待
    }

}
