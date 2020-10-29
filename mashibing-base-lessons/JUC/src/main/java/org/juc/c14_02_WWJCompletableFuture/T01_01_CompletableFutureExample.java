package org.juc.c14_02_WWJCompletableFuture;

import java.util.concurrent.*;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/23 10:37
 * @version: 1.0
 ***********************/
public class T01_01_CompletableFutureExample {

    //runAsync supplyAsync
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //1.supplyAsync
//        supplyAsync();

        //2.runAsync
//        runAsync();

        //3.completedFuture
//        final Future<?> future = completed("Hello");
//        System.out.println(future.isDone());

        //4.allOf   返回值是void
//        allOf();

        //5.anyOf    有返回值
        final Future<?> future = anyOf();
        System.out.println(">>>>>>"+future.get());


        Thread.currentThread().join();
    }

    /**
     * Description: 1.supplyAsync <BR>
     * post ->{
     * basic
     * details
     * }
     * <p>
     * insert basic
     * insert details
     * <p>
     * insert basic
     * [submit]                            ========>Finished
     * insert details
     *
     * @param :
     * @return
     * @author zhao.song    2020/10/23 13:35
     */
    private static void supplyAsync() throws InterruptedException {
        CompletableFuture.supplyAsync(Object::new).thenAcceptAsync(obj -> {
            try {
                System.out.println("obj====start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("obj====end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> "hello").thenAcceptAsync(s -> {
            try {
                System.out.println("obj====start " + s);
                TimeUnit.SECONDS.sleep(5);
                System.out.println("obj====end " + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }), () -> System.out.println("=========Finished======="));


    }


    /**
     * Description: 2.runAsync <BR>
     *
     * @author zhao.song    2020/10/23 14:22
     * @param :
     * @return
     */
    private static void runAsync() {
        CompletableFuture.runAsync(() -> {
            try {
                System.out.println("obj====start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("obj====end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenCompleteAsync((v, t) -> System.out.println("obj finished.")).runAfterBoth(CompletableFuture.runAsync(() -> {
            try {
                System.out.println("hello====start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("hello====end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenCompleteAsync((v, t) -> System.out.println("hello finish.")), () -> System.out.println("DONE"));
    }


    /**
     * Description: 3.completedFuture <BR>
     *
     * @author zhao.song    2020/10/23 14:24
     * @param data:
     * @return {@link java.util.concurrent.Future<?>}
     */
    private static Future<?> completed(String data) {
        return CompletableFuture.completedFuture(data).thenAccept(System.out::println);
    }

    /**
     * Description: 4.allOf <BR>
     *
     * @author zhao.song    2020/10/23 14:30
     * @param :
     * @return {@link java.util.concurrent.Future<?>}
     */
    private static Future<?> allOf(){
        return CompletableFuture.allOf(CompletableFuture.supplyAsync(()->"a").thenAcceptAsync(System.out::println)
                ,CompletableFuture.supplyAsync(()->1).thenAcceptAsync(System.out::println));
    }

    /**
     * Description: 4.anyOf <BR>
     *
     * @author zhao.song    2020/10/23 14:30
     * @param :
     * @return {@link java.util.concurrent.Future<?>}
     */
    private static Future<?> anyOf(){
        return CompletableFuture.anyOf(CompletableFuture.supplyAsync(()->"a").thenAcceptAsync(System.out::println)
                ,CompletableFuture.supplyAsync(()->1).thenAcceptAsync(System.out::println));
    }
}
