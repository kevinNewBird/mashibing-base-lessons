package org.juc.c007_interview01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/4 17:14
 * @version: 1.0
 ***********************/
public class T05_TestLockSupport {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    static Thread t1, t2;

    public static void main(String[] args) {
        final T05_TestLockSupport containter = new T05_TestLockSupport();
        //1.开启工作线程
        t1 = new Thread(() -> {
            System.out.println("t1 开启");
            for (int i = 0; i < 10; i++) {
                containter.add(new Object());
                System.out.println("add " + i);
                if (containter.size() == 5) {
                    LockSupport.unpark(t2);//t2线程释放,一定要在park之前,否则会被阻塞
                    LockSupport.park();//t1线程阻塞
                }
            }
        });

        //2.开启监听线程
        t2 = new Thread(() -> {
            System.out.println("t2 开启");
            if (containter.size() != 5) {
                //t2线程阻塞
                LockSupport.park();
            }
            System.out.println("t2 结束");
            LockSupport.unpark(t1);

        }, "t2");

        t1.start();
        t2.start();
    }
}
