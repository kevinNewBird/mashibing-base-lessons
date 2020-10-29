package org.juc.c12_FromVectorToQueue;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/14 1:02
 * @version: 1.0
 ***********************/
public class TicketSeller4 {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();



    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号:" + i);
        }
    }

    public static void main(String[] args) {

        IntStream.range(0, 10).forEach(num -> {
            new Thread(() -> {
                while (true) {
                    //解决方法二:效率快
                    String oTicket = tickets.poll();
                    if (oTicket == null) {
                        break;
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了" + oTicket);
                }
            }).start();
        });

    }
}
