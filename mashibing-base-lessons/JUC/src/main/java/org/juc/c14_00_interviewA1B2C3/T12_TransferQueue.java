package org.juc.c14_00_interviewA1B2C3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/20 0:00
 * @version: 1.0
 ***********************/
public class T12_TransferQueue {

    public static void main(String[] args) {
        final LinkedTransferQueue<Object> transferQueue = new LinkedTransferQueue<>();

        new Thread(() -> {
            IntStream.range(65, 91).forEach(code -> {
                try {
                    char ele = (char) code;
                    transferQueue.transfer(ele);
                    System.out.print(transferQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }).start();


        new Thread(() -> {
            IntStream.range(1, 27).forEach(num -> {
                try {
                    System.out.print(transferQueue.take());
                    transferQueue.transfer(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }).start();
    }
}
