package org.juc.c007_interview03;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/20 0:00
 * @version: 1.0
 ***********************/
public class T02_TestTransferQueue {

    public static void main(String[] args) {
        final LinkedTransferQueue<Object> numberTransfer = new LinkedTransferQueue<>();
        final LinkedTransferQueue<Object> elementTransfer = new LinkedTransferQueue<>();

        new Thread(() -> {
            IntStream.range(65, 91).forEach(code -> {
                try {
                    char ele = (char) code;
                    elementTransfer.transfer(ele);
                    System.out.print(numberTransfer.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }).start();


        new Thread(() -> {
            IntStream.range(1, 27).forEach(num -> {
                try {
                    System.out.print(elementTransfer.take());
                    numberTransfer.transfer(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }).start();
    }
}
