package org.juc.c14_00_interviewA1B2C3;

import java.util.concurrent.TimeUnit;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/21 22:58
 * @version: 1.0
 ***********************/
public class T02_00_sync_wait_notify {

    public static void main(String[] args) {
        final Object MONITOR = new Object();
        char[] aC = "ABCDEFG".toCharArray();
        char[] aI = "1234567".toCharArray();

        new Thread(()->{
            synchronized (MONITOR) {
                for (char c : aI) {
                    try {
                        System.out.print(c);
                        MONITOR.notify();
                        MONITOR.wait();//让出锁

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                MONITOR.notify();//必须,否则无法停止程序
            }
        }).start();

        new Thread(()->{
            synchronized (MONITOR) {
                for (char c : aC) {
                    try {
                        System.out.print(c);
                        MONITOR.notify();
                        MONITOR.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                MONITOR.notify();
            }
        }).start();


    }
}
