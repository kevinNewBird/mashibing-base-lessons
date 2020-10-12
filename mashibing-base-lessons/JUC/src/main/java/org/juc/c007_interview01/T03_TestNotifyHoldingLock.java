package org.juc.c007_interview01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/4 15:17
 * @version: 1.0
 ***********************/
public class T03_TestNotifyHoldingLock {

    //    volatile List lists = new ArrayList<>();
    volatile List lists = Collections.synchronizedList(new ArrayList<>());//使用同步容器

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        final T03_TestNotifyHoldingLock container = new T03_TestNotifyHoldingLock();
        final Object MONITOR = new Object();
        //1.先开启观察线程
        new Thread(() -> {
            synchronized (MONITOR) {
                System.out.println(String.format("观察者线程[%s]启动!", Thread.currentThread().getName()));
                if (container.size() != 5) {
                    try {
                        MONITOR.wait();//必须要获取到锁才会继续往下执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(String.format("观察者线程[%s]结束!监测结束时的容器大小:%s"
                        , Thread.currentThread().getName(), container.size()));
                //通知t1线程继续执行
                MONITOR.notifyAll();
            }

        }, "t2").start();

        //2.启动工作线程
        //2.1 启动工作线程方式一(这种类似于容器的大小等于5时,notify后,通知到t2线程后线程t1的锁就被释放了
        // ,t2继续往下执行,同时t1也会快速的往下执行)
        //同方式二相比 , 方式一 t1不会等待t2线程的监测结果输出后再往下执行
        //这种方式存在的问题,跨线程size无法实时的保证为需要的5
        new Thread(() -> {
            System.out.println(String.format("工作线程[%s]启动!", Thread.currentThread().getName()));
            for (int i = 0; i < 10; i++) {
                synchronized (MONITOR) {
                    container.add(new Object());
                    System.out.println("add " + i);
                    if (container.size() == 5) {
                        MONITOR.notifyAll();//这个方法不会释放锁
                    }
                }

            }
        }, "t1").start();
        //2.2 启动工作线程方式二
        /*new Thread(() -> {
            System.out.println(String.format("工作线程[%s]启动!", Thread.currentThread().getName()));
            synchronized (MONITOR) {
                for (int i = 0; i < 10; i++) {
                    container.add(new Object());
                    System.out.println("add " + i);
                    if (container.size() == 5) {
                        MONITOR.notifyAll();//这个方法不会释放锁
                        //释放锁
                        try {
                            MONITOR.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }, "t1").start();*/
    }
}
