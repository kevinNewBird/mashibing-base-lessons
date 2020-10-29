package org.juc.c13_Containers2;

import java.util.PriorityQueue;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/19 10:35
 * @version: 1.0
 ***********************/
public class T07_01_PriorityQueue {

    public static void main(String[] args) {
        final PriorityQueue<String> queue = new PriorityQueue<>();

        queue.add("b");
        queue.add("a");
        queue.add("z");
        queue.add("c");

        while (true) {
            String ele = queue.poll();
            if (ele == null) {
                break;
            }
            System.out.println(ele);
        }

    }
}
