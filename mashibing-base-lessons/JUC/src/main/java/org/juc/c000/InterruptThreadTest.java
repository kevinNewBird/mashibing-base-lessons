package org.juc.c000;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/9/14 23:58
 * @version: 1.0
 ***********************/
public class InterruptThreadTest {

    public static void main(String[] args) {
        final Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1_000);
                while (true) {
                    System.out.println("11111");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                return;
            }
        });
        t1.start();
        try {
            Thread.sleep(1_100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("****************************************");

        //线程的中断只有在线程调用sleep/join/wait的时候会触发
        t1.interrupt();
        System.out.println(t1.getState());


    }
}
