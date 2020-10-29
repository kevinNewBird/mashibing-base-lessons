package org.juc.c14_02_WWJCompletableFuture;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/23 10:37
 * @version: 1.0
 ***********************/
public class T01_00_CompletableFutureExample {

    public static void main(String[] args) throws InterruptedException {
        // 1.简单的exctorservice线程池使用
//        runCommonExecutor();

        // 2.简单的completablefuture回调使用
        // CompletableFuture内部自己维护了一个线程池 , 这个线程池里的所有线程都是守护线程;
        // 这就意味着主线程一结束 , CompletableFuture的结果可能获取不到
        // whenComplete方法就是回调
//        runCompletableFuture();


        // 3.使用executorservice完成多个任务的执行,并打印执行结果
//        runAllTasksByExecutors();


        // 4.使用completablefuture完成多个任务的执行
        runAllByCompletableFuture();

    }

    // 1.简单的exctorservice线程池使用
    private static void runCommonExecutor() {
        ExecutorService service = Executors.newFixedThreadPool(10);

        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //
        while (!future.isDone()) {

        }

        System.out.println("DONE");
    }

    // 2.简单的completablefuture回调使用
    private static void runCompletableFuture() throws InterruptedException {
        //CompletableFuture内部自己维护了一个线程池 , 这个线程池里的所有线程都是守护线程;
        // 这就意味着主线程一结束 , CompletableFuture的结果可能获取不到
        // whenComplete方法就是回调
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((v, t) -> System.out.println("DONE"));

        System.out.println("=========i am not blocked======");

        Thread.currentThread().join();
    }

    // 3.使用executorservice完成多个任务的执行,并打印执行结果
    private static void runAllTasksByExecutors() throws InterruptedException {
        //无法保证get方法执行完了立即执行display,原因就在于future.get
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> tasks = IntStream.range(0, 10).boxed()
                .map(i -> (Callable<Integer>) () -> get())
                .collect(Collectors.toList());

        service.invokeAll(tasks).stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).parallel().forEach(i -> display(i));
    }


    //4.使用completablefuture完成多个任务的执行
    private static void runAllByCompletableFuture() throws InterruptedException {
        //执行10个future
        IntStream.range(0, 10).boxed()
                .forEach(i -> CompletableFuture.supplyAsync(T01_00_CompletableFutureExample::get)
                        .thenAccept(T01_00_CompletableFutureExample::display)
                        .whenComplete((v, t) -> System.out.println(i + " DONE")));

        Thread.currentThread().join();
    }


    //展示
    private static void display(int data) {
        int value = ThreadLocalRandom.current().nextInt(20);

        try {
            System.out.printf(Thread.currentThread().getName() + " display will be sleep %s s\n", value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " display execute done! the value: " + data);
    }

    //获取
    private static int get() {
        int value = ThreadLocalRandom.current().nextInt(20);

        try {
            System.out.printf(Thread.currentThread().getName() + " get will be sleep %s s\n", value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " get execute done!");
        return value;
    }
}
