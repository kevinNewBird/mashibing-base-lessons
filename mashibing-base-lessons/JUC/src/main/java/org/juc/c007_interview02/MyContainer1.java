package org.juc.c007_interview02;

import java.util.*;
import java.util.stream.IntStream;

/***********************
 * Description: 面试题二 <BR>
 *     写一个固定容量同步容器 , 拥有put 和 get 方法, 以及getCount 方法
 *     , 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * @author: zhao.song
 * @date: 2020/10/4 19:45
 * @version: 1.0
 ***********************/
public class MyContainer1<T> {
    //固定容量同步容器
    volatile LinkedList<T> lists = new LinkedList<T>();

    private static final int MAX = 10; //最多10个
    volatile int INDEX = 0;

    public synchronized void put(T o) {
        //考虑为什么是使用while,而不是if?
        //这是为了确保线程被唤醒后,再次进行判断;用if会直接往下执行
        while (lists.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(o);
        INDEX++;
        this.notifyAll();//通知所有的消费者进行消费
    }

    public synchronized T get() {
        while (lists.size() <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = lists.removeFirst();
        INDEX--;
        //通知所有的生产者生产:瑕疵,会叫醒等待的所有线程(包括生产者和消费者)
        //可以使用Condition , 参考MyContainer3
        this.notifyAll();
        return t;
    }

    public int getCount() {
        return INDEX;
    }

    public static void main(String[] args) {
        final MyContainer1<String> container = new MyContainer1<>();

        // 1.开启10个消费者
        IntStream.range(0, 10).forEach(num -> {
            new Thread(() -> {
                for (int i = 0; i < 5; i++)
                    System.out.println(Thread.currentThread().getName() + " get the value[" + container.get() + "]");
            }, "c" + num).start();
        });

        // 2.开启2个生产者
        IntStream.range(0, 2).forEach(num -> {
            new Thread(() -> {
                for (int i = 0; i < 25; i++) container.put(Thread.currentThread().getName() + "-" + i);
            }, "p" + num).start();
        });

    }
}
