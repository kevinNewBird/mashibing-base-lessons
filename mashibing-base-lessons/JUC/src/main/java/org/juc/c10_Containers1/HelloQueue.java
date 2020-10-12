package org.juc.c10_Containers1;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/13 0:43
 * @version: 1.0
 ***********************/
public class HelloQueue {

    public static void main(String[] args) {

        final Queue<Integer> queue = new ArrayBlockingQueue<>(2);

        queue.add(0);
        queue.add(1);
//        queue.add(2);
//        queue.add(3);
        System.out.println(queue);
    }
}
