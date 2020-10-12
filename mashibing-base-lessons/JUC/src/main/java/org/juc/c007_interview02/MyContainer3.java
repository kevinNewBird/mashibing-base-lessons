package org.juc.c007_interview02;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
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
public class MyContainer3<T> {
    volatile LinkedList lists = new LinkedList<T>();
    private static final int MAX = 10;
    private int INDEX = 0;

    Lock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    public void put(T o) {
        try {
            lock.lock();
            while (lists.size() == MAX) {
                producer.await();
            }
            lists.add(o);
            INDEX++;
            consumer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }
            t = (T) lists.removeFirst();
            INDEX--;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
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
