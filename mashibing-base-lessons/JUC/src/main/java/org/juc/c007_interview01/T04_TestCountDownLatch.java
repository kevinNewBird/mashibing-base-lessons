package org.juc.c007_interview01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/4 16:14
 * @version: 1.0
 ***********************/
public class T04_TestCountDownLatch {

    volatile List lists = new ArrayList<>();
//    volatile List lists = Collections.synchronizedList(new ArrayList<>());//使用同步容器

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        //无法确保线程t2执行完成后在执行t1
//        test0();
        //
        test1();
    }

    public static void test0() {
        final T04_TestCountDownLatch container = new T04_TestCountDownLatch();
        final CountDownLatch latch = new CountDownLatch(1);
        //1.开启监测线程
        new Thread(() -> {
            System.out.println("t2 启动");
            if (container.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();

        //2.开启工作线程
        new Thread(()->{
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                System.out.println("add "+i);
                if (container.size() == 5) {
                    latch.countDown();
                }
            }
        }).start();
    }

    public static void test1() {
        final T04_TestCountDownLatch container = new T04_TestCountDownLatch();
        final CountDownLatch latch0 = new CountDownLatch(1);
        final CountDownLatch latch1 = new CountDownLatch(1);
        //1.开启监测线程
        new Thread(() -> {
            System.out.println("t2 启动");
            if (container.size() != 5) {
                try {
                    latch0.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
            latch1.countDown();
        }, "t2").start();

        //2.开启工作线程
        new Thread(()->{
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                System.out.println("add "+i);
                if (container.size() == 5) {
                    latch0.countDown();
                    try {
                        latch1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
