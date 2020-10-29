package org.juc.c14_01_ThreadPool;

import java.util.concurrent.*;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/22 15:14
 * @version: 1.0
 ***********************/
public class T06_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Future
//        ExecutorService service = Executors.newFixedThreadPool(5);
//        final Future<Integer> future = service.submit(() -> {
//            TimeUnit.MILLISECONDS.sleep(500);
//            return 1;
//        });
//
//        System.out.println(future.get());//阻塞
//        System.out.println(future.isDone());
//        System.out.println(future.isCancelled());
//        System.out.println(future.isCancelled());


        //FutureTask:既是一个任务对象 , 且执行结果也会存放在本对象里
        final FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });//new Callable() {Integer call();}

        new Thread(task).start();
        System.out.println(task.get());
    }
}
