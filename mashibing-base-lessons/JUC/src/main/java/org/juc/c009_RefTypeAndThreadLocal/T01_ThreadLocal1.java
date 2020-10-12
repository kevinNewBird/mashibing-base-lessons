package org.juc.c009_RefTypeAndThreadLocal;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/5 21:46
 * @version: 1.0
 ***********************/
public class T01_ThreadLocal1 {

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());

        }).start();

//        new WeakHashMap<>()
    }


}

class Person {
    String name = "zhangsan";
}