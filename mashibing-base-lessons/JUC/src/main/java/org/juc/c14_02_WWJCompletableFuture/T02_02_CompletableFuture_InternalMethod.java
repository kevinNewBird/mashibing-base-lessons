package org.juc.c14_02_WWJCompletableFuture;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/***********************
 * Description: 工厂方法 <BR>
 * @author: zhao.song
 * @date: 2020/10/23 15:30
 * @version: 1.0
 ***********************/
public class T02_02_CompletableFuture_InternalMethod {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1. 测试whenComplete
//        Object v0 = testSupplyAsync().get();

        //2. 测试thenApply
//        Object v0 = testThenApply().get();

        //3. 测试handle
//        Object v0 = testHandle().get();


        //4. 测试exceptionally
        testExceptionally();
//        print(v0);
    }


    //1. whenComplete (消费者,相当于回调)
    private static Future<?> testSupplyAsync() {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        future.whenCompleteAsync((v, t) -> {
            try {
                System.out.println("=========================");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("===========over==========");
        });
        return future;
    }


    //2. thenApply (将结果转换为另一个值)
    private static Future<?> testThenApply() {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        future
//                .thenApply(String::length);
                .thenApplyAsync(s -> {
                    try {
                        System.out.println("=========================");
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("===========over==========");
                    return s.length();
                });
        return future;
    }


    //3. handle
    private static Future<?> testHandle() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync((Supplier<String>) () -> {
            throw new RuntimeException("not get data");
        });
        CompletableFuture<Integer> future01 = future.handle((v, t) -> {
            Optional.of(t).ifPresent(e -> System.out.println("ERROR"));
            return v == null ? 0 : v.length();
        });

        System.out.println(future01.get());

        return future;
    }


    //4.exceptionally
    private static void testExceptionally() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync((Supplier<String>) () -> {
//            throw new RuntimeException("not get data");
            return "get data";
        }).exceptionally(throwable -> throwable.getMessage());
        System.out.println(future.get());
    }


    private static void print(Object value) {
        System.out.printf(">>>>> the value is [ %s ]\n", value);
    }
}
