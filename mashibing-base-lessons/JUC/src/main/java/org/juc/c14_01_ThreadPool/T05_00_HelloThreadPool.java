package org.juc.c14_01_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/22 14:54
 * @version: 1.0
 ***********************/
public class T05_00_HelloThreadPool {

    static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i);

            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
//        useCustomThreadFactory();

        useDefaultThreadFactory();
    }


    private static void useDefaultThreadFactory() {

        //总共能处理8个任务(最大线程数+任务队列数)
        final ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS
                , new ArrayBlockingQueue<Runnable>(4)
                , Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.CallerRunsPolicy());
        IntStream.range(0, 8).forEach(num -> tpe.execute(new Task(num)));

        System.out.println(tpe.getQueue());

        tpe.execute(new Task(100));

        System.out.println(tpe.getQueue());

        tpe.shutdown();
    }

    private static void useCustomThreadFactory() {
        final ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS
                , new ArrayBlockingQueue<Runnable>(4)
                , new CustomThreadFactory()
                , new ThreadPoolExecutor.CallerRunsPolicy());
        IntStream.range(0, 8).forEach(num -> tpe.execute(new Task(num)));

        System.out.println(tpe.getQueue());

//        tpe.shutdown();
    }
}
