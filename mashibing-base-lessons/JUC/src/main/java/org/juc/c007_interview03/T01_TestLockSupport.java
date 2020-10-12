package org.juc.c007_interview03;

import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

/***********************
 * Description: 面试题三:
 * 要求用线程顺序打印A1B2...Z26<BR>
 * @author: zhao.song
 * @date: 2020/10/4 21:47
 * @version: 1.0
 ***********************/
public class T01_TestLockSupport {

    static Thread t1, t2;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            IntStream.range(65, 91).forEach(code -> {
                char ele = (char) code;
                System.out.print(ele);
                LockSupport.unpark(t2);
                LockSupport.park();
            });
        });


        t2 = new Thread(() -> {
            IntStream.range(1, 27).forEach(num -> {
                LockSupport.park();
                System.out.print(num);
                LockSupport.unpark(t1);
            });
        });
        t1.start();
        t2.start();

    }
}
