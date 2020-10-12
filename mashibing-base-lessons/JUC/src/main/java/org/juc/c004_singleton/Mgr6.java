package org.juc.c004_singleton;

import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/9/23 0:05
 * @version: 1.0
 ***********************/
public class Mgr6 {

    private static volatile Mgr6 INSTANCE;//JIT

    private Mgr6() {
        super();
    }

    public static Mgr6 getInstance() {
        //业务逻辑代码省略
        //double check
        if (INSTANCE == null) {
            synchronized (Mgr6.class) {
                if (INSTANCE == null) {
                    //JVM步骤:1.申请内存;2.成员变量赋值;3.成员变量赋值instance实例(即指向实例地址)
                    //如果出现指令重排 , 上述步骤2,3可能发生位置的调换,从而导致对象出现空指针等问题
                    //所以double check的单列必须使用volatile修饰
                    INSTANCE = new Mgr6();
                }
            }
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) throws InterruptedException {
        //验证单例
        IntStream.range(0, 10).forEach(i -> {
            new Thread(() -> {
                System.out.println("hasCode:" + Mgr6.getInstance().hashCode());
            }, String.valueOf(i)).start();
        });


        //测试countDownLatch
        /*final CountDownLatch latch = new CountDownLatch(5);

        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "-" + i);
                latch.countDown();
            }).start();
        });
        System.out.println("----------------------------------------");
        latch.await();
        System.out.println("****************************************");*/
    }

    public static class SingletonInstance {
        public static final Mgr6 INSTANCE = new Mgr6();
    }
}


