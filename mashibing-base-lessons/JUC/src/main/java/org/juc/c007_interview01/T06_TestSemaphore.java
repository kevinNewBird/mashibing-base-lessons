package org.juc.c007_interview01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/4 17:41
 * @version: 1.0
 ***********************/
public class T06_TestSemaphore {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    static Thread t1, t2;

    public static void main(String[] args) {
        final T06_TestSemaphore container = new T06_TestSemaphore();
        final Semaphore semaphore = new Semaphore(1);
        // 1.开启监听器
        t2 = new Thread(() -> {
            System.out.println("t2 启动");
            try {
                semaphore.acquire();
                System.out.println("t2 结束");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t2");

        //2.开启工作线程
        t1 = new Thread(() -> {
            System.out.println("t1 启动");
            try {
                semaphore.acquire();
                for (int i = 0; i < 5; i++) {
                    container.add(new Object());
                    System.out.println("add " + i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                T06_TestSemaphore.t2.start();
                T06_TestSemaphore.t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                semaphore.acquire();
                for (int i = 5; i < 10; i++) {
                    System.out.println(i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
    }
}
