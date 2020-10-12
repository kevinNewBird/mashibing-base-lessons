package org.juc.c008_aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/5 10:45
 * @version: 1.0
 ***********************/
public class T01_TestAQS {
    static int i = 0;

    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();
        new Thread(()->{
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
                i++;
                System.out.println("AQS源码");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }).start();

        new Thread(()->{
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
                i++;
                System.out.println("AQS源码");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        new Thread(()->{
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
                i++;
                System.out.println("AQS源码");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

    }
}
