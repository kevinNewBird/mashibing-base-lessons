package org.juc.c007_caslock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/3 21:18
 * @version: 1.0
 ***********************/
public class T09_TestReentrantReadWriteLock1 {

    private static ReentrantLock lock = new ReentrantLock();
    private static int value = 0;

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read over! the value is [" + value + "]");
            //模拟数据读取
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            value = v;
            System.out.println("write over! the value is [" + value + "]");
            //模拟写操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Random r = new Random();
//        Runnable readR = () -> read(lock);
//        Runnable writeR = () -> write(lock, r.nextInt(1000));
        Runnable readR = () -> read(readLock);
        Runnable writeR = () -> write(writeLock, r.nextInt(1000));
        IntStream.range(0, 18).forEach(num -> new Thread(readR).start());
        IntStream.range(0, 2).forEach(num -> new Thread(writeR).start());

    }
}
