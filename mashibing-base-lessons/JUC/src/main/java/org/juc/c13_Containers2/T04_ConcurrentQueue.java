package org.juc.c13_Containers2;

import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/15 18:02
 * @version: 1.0
 ***********************/
public class T04_ConcurrentQueue {

    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        //add 和 offer的区别: offer添加失败返回false , add则是抛异常
        IntStream.range(0, 10).forEach(num -> queue.offer("a" + num));

        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.size());


        System.out.println("---------------------------------");
        //双端队列Deque
        Deque<String> deque = new ConcurrentLinkedDeque<>();

        //add 和 offer的区别: offer添加失败返回false , add则是抛异常
        IntStream.range(0, 10).forEach(num -> deque.offer("a" + num));//线程安全的offer

        System.out.println(deque);
        System.out.println(deque.size());
        System.out.println(deque.poll());//取出并且移除,线程安全
        System.out.println(deque.size());
        System.out.println(deque.peek());
        System.out.println(deque.size());
        System.out.println(deque.pollLast());
        System.out.println(deque.size());
        System.out.println(deque.pop());
        System.out.println(deque.size());
    }
}
