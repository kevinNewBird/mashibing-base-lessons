package org.juc.c14_01_ThreadPool;

import java.util.concurrent.*;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/22 14:49
 * @version: 1.0
 ***********************/
public class T03_callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> "hello callable";

        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<String> future = service.submit(callable);

        System.out.println(future.get());

        service.shutdown();
    }
}
