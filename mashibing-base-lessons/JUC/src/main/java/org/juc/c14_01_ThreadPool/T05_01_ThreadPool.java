package org.juc.c14_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/22 15:04
 * @version: 1.0
 ***********************/
public class T05_01_ThreadPool {

    public static void main(String[] args) throws InterruptedException {

        boolean flag = true;

        ExecutorService service = Executors.newFixedThreadPool(5);
        IntStream.range(0,6).forEach(num->{
            service.execute(()->{
//                while (flag) { }
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());

            });
        });

        System.out.println(service);

        service.shutdownNow();
        System.out.println("main before sleep, is terminate:"+service.isTerminated());
        System.out.println("main before sleep, is shutdown:"+service.isShutdown());
        System.out.println("main before sleep, service:"+service);

        TimeUnit.SECONDS.sleep(5);
        System.out.println("main after sleep, is terminate:"+service.isTerminated());
        System.out.println("main after sleep, is shutdown:"+service.isShutdown());
        System.out.println("main after sleep, service:"+service);
    }
}
