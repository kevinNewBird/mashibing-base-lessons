package org.juc.c007_caslock;

import java.util.concurrent.Exchanger;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/3 22:41
 * @version: 1.0
 ***********************/
public class T11_TestExchanger {

    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            String value = "T1";
            try {
                String newValue = exchanger.exchange(value);
                System.out.println("the thread["+Thread.currentThread().getName() + "] exchange " +
                        "the value [" + value + "] to " + "[" + newValue + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            String value = "T2";
            try {
                String newValue = exchanger.exchange(value);
                System.out.println("the thread["+Thread.currentThread().getName() + "] exchange " +
                        "the value [" + value + "] to " + "[" + newValue + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
