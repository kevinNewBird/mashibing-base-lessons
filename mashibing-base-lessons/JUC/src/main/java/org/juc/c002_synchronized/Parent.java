package org.juc.c002_synchronized;

import java.util.concurrent.TimeUnit;

/***********************
 * Description: synchronized <BR>
 *     1.是可重入锁:一个线程已经拥有某个对象的锁, 再次申请的时候仍然会得到该对象的锁 , 也就是说synchronized获得锁是可重入的
 *     2.异常的锁 : 程序在执行过程中 ,如果出现异常 , 默认情况下锁会被释放 ; 所以在并发处理的过程中, 有异常要多加小心
 *     , 不然可能会发生不一致的情况 . 比如 , 在一个web app处理过程中 , 多个servlet 线程共同访问同一个资源
 *     , 这时如果异常处理不合适 , 在第一个线程中抛出异常, 其他线程就会进入同步代码区 , 有可能会访问到异常产生时的数据
 *     , 因此要非常小心的处理同步业务逻辑中的异常 .
 * @author: zhao.song
 * @date: 2020/9/15 22:11
 * @version: 1.0
 ***********************/
public class Parent {


    int count = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start...");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                int i = 1 / 0; // 此处抛出异常, 锁将被释放; 如果不想被释放, 可以在这里进行catch
                System.out.println(i);
            }
        }
    }

    synchronized void m2() {
        System.out.println("父类:" + this.getClass());
    }

    public static void main(String[] args) {
        final Parent parent = new Parent();
        Runnable r = () -> parent.m();
        new Thread(r, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r, "t2").start();
    }
}
