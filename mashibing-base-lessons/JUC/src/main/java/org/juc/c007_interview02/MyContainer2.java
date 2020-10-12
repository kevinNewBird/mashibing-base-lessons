package org.juc.c007_interview02;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/4 20:19
 * @version: 1.0
 ***********************/
public class MyContainer2<T> {
    volatile LinkedList lists = new LinkedList<T>();
    private static final int MAX = 10;
    private int INDEX = 0;

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    public void put(T o) {
        try {
            writeLock.lock();
            while (lists.size() == MAX) {
                this.wait();
            }
            lists.add(o);
            INDEX++;
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            readLock.lock();
            while (lists.size() == 0) {
                this.wait();
            }
            t = (T) lists.removeFirst();
            INDEX--;
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return t;
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
