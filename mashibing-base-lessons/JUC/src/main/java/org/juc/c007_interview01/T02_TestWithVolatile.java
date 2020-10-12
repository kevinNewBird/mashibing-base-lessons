package org.juc.c007_interview01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***********************
 * Description: 面试题1 <BR>
 *     1. 实现一个容器 , 提供两个方法 , add/size ; 写两个线程 ,线程1添加10个元素到容器中
 *     , 线程2实现监控元素的个数 , 当个数到5个时 , 线程2给出提示并结束
 * @author: zhao.song
 * @date: 2020/10/4 13:50
 * @version: 1.0
 ***********************/
public class T02_TestWithVolatile {

    //    volatile List lists = new ArrayList<>();
    volatile List lists = Collections.synchronizedList(new ArrayList<>());//使用同步容器

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    /**
     * 存在问题点:
     * 1.add操作和size操作存在,add添加完成, 但是size的大小还没有更新
     * 2.volatile如果修饰的是一个复杂的引用对象 , 引用对象内部的值改变了,
     * 别的线程是监测不到的 , 所以volatile尽量修饰简单的变量
     *
     * @param args
     */
    public static void main(String[] args) {
        final T02_TestWithVolatile container = new T02_TestWithVolatile();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                System.out.println("add " + i);
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (container.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束!");
        }, "t2").start();
    }
}
