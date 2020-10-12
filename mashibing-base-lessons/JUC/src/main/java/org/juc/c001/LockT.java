package org.juc.c001;

import java.util.Optional;

/***********************
 * Description: synchronized关键字对某个对象加锁 <BR>
 * @author: zhao.song
 * @date: 2020/9/15 0:11
 * @version: 1.0
 ***********************/
public class LockT {

    private static int count = 10;

    private Object MONITOR = new Object();

    public synchronized void n() { // 等同于synchronized(this)

    }

    public static synchronized void nn() { // 等同于synchronized(LockT.class)

    }

    //synchronized锁
    public  void m() {
        synchronized (MONITOR) {
            count--;
            Optional.of(Thread.currentThread().getName() + " count = " + count).ifPresent(System.out::println);
        }
    }


    public static void mm(){
        synchronized (LockT.class) {//考虑一下这里写synchronized(this)是否可以? 肯定不行
            count--;
        }
    }

}
