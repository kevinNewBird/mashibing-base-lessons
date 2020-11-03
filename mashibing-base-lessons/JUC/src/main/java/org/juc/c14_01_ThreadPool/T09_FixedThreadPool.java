package org.juc.c14_01_ThreadPool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/2 23:37
 * @version: 1.0
 ***********************/
public class T09_FixedThreadPool {

    public static void main(String[] args) {

        final ExecutorService service = Executors.newFixedThreadPool(10
                , new BasicThreadFactory.Builder().namingPattern("fixed-pool-%s").build());


        IntStream.range(0, 10).forEach(num -> {
            service.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " is running.");
            });
        });


    }
}
