package org.juc.c12_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/14 1:02
 * @version: 1.0
 ***********************/
public class TicketSeller2 {

    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号:" + i);
        }
    }

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(num -> {
            new Thread(() -> {
                //还是会存在锁的问题:vector的size方法和remove方法虽然是原子性的,但是中间sleep部分仍是并发性的
                //就会存在多个线程进入这段代码,从而导致在进入remove方法时,tickets已经为空!
                while (tickets.size() > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了" + tickets.remove(0));
                }
            }).start();
        });

    }
}
