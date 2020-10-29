package org.juc.c14_00_interviewA1B2C3;

import java.util.concurrent.Exchanger;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/22 11:35
 * @version: 1.0
 ***********************/
public class T11_00_Exchanger {

    private static volatile boolean t1Start=false;
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(()->{
            for (char c : aI) {
                try {
                    exchanger.exchange("T1");//确保打印顺序
                    System.out.print(c);
                    exchanger.exchange("T3");//确保打印后的执行顺序
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            for (char c : aC) {
                try {
                    System.out.print(c);
                    exchanger.exchange("T2");
                    exchanger.exchange("T4");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
