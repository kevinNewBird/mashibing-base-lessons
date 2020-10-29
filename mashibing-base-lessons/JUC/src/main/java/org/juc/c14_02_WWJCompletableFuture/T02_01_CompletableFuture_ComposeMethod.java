package org.juc.c14_02_WWJCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/26 21:30
 * @version: 1.0
 ***********************/
public class T02_01_CompletableFuture_ComposeMethod {

    public static void main(String[] args) throws InterruptedException {
        // 1.thenAcceptBot
//        thenAcceptBoth();

        // 2.acceptEither
//        acceptEither();

        // 3.runAfterBoth
//        runAfterBoth();

        // 4.runAfterEither
//        runAfterEither();


        //5.thenCombine
//        combine();


        // 6.thenCompose
        compose();


        Thread.currentThread().join();
    }


    // 1.thenAcceptBoth
    private static void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the supplyAsync");
            sleep(5);
            System.out.println("end the supplyAsync");
            ;
            return "thenAcceptBoth";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenAcceptBot");
            sleep(5);
            System.out.println("end the thenAcceptBot");
            return 100;
        }), (s, i) -> System.out.println(s + "--" + i)).exceptionally((v) -> {
            if (v != null) {
                throw new RuntimeException(v.getMessage());
            }
            return null;
        });
    }

    // 2.acceptEither
    private static void acceptEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the acceptEither");
            sleep(5);
            System.out.println("end the acceptEither");
            ;
            return "acceptEither";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the acceptEither");
            sleep(5);
            System.out.println("end the acceptEither");
            ;
            return "acceptEither2";
        }), s -> System.out.println(s));
    }

    // 3.runAfterBoth
    private static void runAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the runAfterBoth");
            sleep(5);
            System.out.println("end the runAfterBoth");
            ;
            return "runAfterBoth";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the runAfterBoth");
            sleep(5);
            System.out.println("end the runAfterBoth");
            ;
            return 100;
        }), () -> System.out.println("end"));
    }


    // 4.runAfterEither
    private static void runAfterEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the runAfterEither");
            sleep(5);
            System.out.println("end the runAfterEither");
            ;
            return "runAfterEither";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the runAfterEither");
            sleep(5);
            System.out.println("end the runAfterEither");
            ;
            return 100;
        }), () -> System.out.println("DONE"));
    }


    // 5.thenCombine
    private static void combine() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenCombine");
            sleep(5);
            System.out.println("end the thenCombine");
            ;
            return "thenCombine";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenCombine");
            sleep(5);
            System.out.println("end the thenCombine");
            ;
            return 100;
        }), (s, n) -> s + "--" + n).whenComplete((s, t) -> System.out.println(s));
    }


    // 6.thenCompose
    private static void compose() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenCompose");
            sleep(5);
            System.out.println("end the thenCompose");
            return "thenCompose";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenCompose");
            sleep(5);
            System.out.println("end the thenCompose");
            return s.length();
        })).thenAccept(System.out::println);
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
