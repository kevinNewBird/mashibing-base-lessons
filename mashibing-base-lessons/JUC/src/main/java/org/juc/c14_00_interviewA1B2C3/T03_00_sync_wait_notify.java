package org.juc.c14_00_interviewA1B2C3;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/21 22:58
 * @version: 1.0
 ***********************/
public class T03_00_sync_wait_notify {

    volatile static boolean cStart = true;

    public static void main(String[] args) {
        final Object MONITOR = new Object();
        char[] aC = "ABCDEFG".toCharArray();
        char[] aI = "1234567".toCharArray();


        new Thread(() -> {
            synchronized (MONITOR) {
                while (cStart) {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (char c : aI) {
                    try {
                        System.out.print(c);
                        cStart = true;
                        MONITOR.notify();
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                MONITOR.notify();
            }
        }).start();

        new Thread(() -> {
            synchronized (MONITOR) {
                while (!cStart) {
                    try {
                        MONITOR.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : aC) {
                    if (cStart) {
                        try {
                            System.out.print(c);
                            cStart = false;
                            MONITOR.notify();
                            MONITOR.wait();//让出锁

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                MONITOR.notify();
            }
        }).start();


    }
}
