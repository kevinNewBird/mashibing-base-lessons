package org.juc.c13_Containers2;

import java.util.concurrent.LinkedTransferQueue;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/19 20:20
 * @version: 1.0
 ***********************/
public class T09_TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        queue.transfer("aaa");

    }
}
