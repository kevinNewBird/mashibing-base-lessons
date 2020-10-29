package org.juc.c14_02_WWJCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/26 22:10
 * @version: 1.0
 ***********************/
public class T02_03_CompletableFuture_InterminateMethod {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1.getNow
//        getNow();


        // 2.complete
//        complete();


        // 3.join
//        testJoin();

        // 4.completeExceptionally
//        completeExceptionally();


        // 5.
        final CompletableFuture<String> future = errorHandle();
        future.whenComplete((v, t) -> System.out.println(v));

        Thread.currentThread().join();

    }


    // 1.getNow
    private static void getNow() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("====== i will be still process...");
            return "HELLO";
        });
        String result = future.getNow("WORLD");

        System.out.println(result);
        System.out.println(future.get());
    }


    // 2.complete
    private static void complete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("====== i will be still process...");
            return "HELLO";
        });
        final boolean finished = future.complete("WORLD");
        System.out.println(finished);
        System.out.println(future.get());
    }

    // 3. join
    private static void testJoin() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("====== i will be still process...");
            return "HELLO";
        });
        String result = future.join();
        System.out.println(result);
        System.out.println(future.get());
    }

    // 4. completeExceptionally
    private static void completeExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            sleep(5);
            System.out.println("====== i will be still process...");
            return "HELLO";
        });

        sleep(1);
        future.completeExceptionally(new RuntimeException());
        System.out.println(future.get());
    }

    private static CompletableFuture<String> errorHandle() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("====== i will be still process...");
            return "HELLO";
        });

        future1.thenApply(s -> {
            sleep(10);
            System.out.println("=============keep move==========");
            return s + " WORLD";
        });
        return future1;
    }


    /**
     * Description: 休眠(unit:s) <BR>
     *
     * @param seconds:
     * @return
     * @author zhao.song    2020/10/26 21:31
     */
    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
