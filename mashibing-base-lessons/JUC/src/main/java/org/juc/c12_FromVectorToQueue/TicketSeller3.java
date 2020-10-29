package org.juc.c12_FromVectorToQueue;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/14 1:02
 * @version: 1.0
 ***********************/
public class TicketSeller3 {

    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号:" + i);
        }
    }

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(num -> {
            new Thread(() -> {
                while (true) {
                    //解决方法一:加锁(效率低到离谱)
                    synchronized (tickets) {
                        if (tickets.size() <= 0) {
                            break;
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("销售了" + tickets.remove(0));
                    }

                }
            }).start();
        });

    }
}
