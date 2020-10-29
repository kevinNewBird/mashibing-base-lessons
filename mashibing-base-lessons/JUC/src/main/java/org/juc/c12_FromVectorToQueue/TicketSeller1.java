package org.juc.c12_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/14 1:02
 * @version: 1.0
 ***********************/
public class TicketSeller1 {

    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号:" + i);
        }
    }

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(num -> {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("销售了" + tickets.remove(0));
                }
            }).start();
        });

    }
}
