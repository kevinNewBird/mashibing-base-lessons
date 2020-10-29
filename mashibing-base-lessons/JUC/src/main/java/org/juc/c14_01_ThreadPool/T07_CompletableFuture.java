package org.juc.c14_01_ThreadPool;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/22 15:34
 * @version: 1.0
 ***********************/
public class T07_CompletableFuture {

    static Random r = new Random();

    /**
     * 假设你能提供一个服务
     * 这个服务查询各大电脑厂商网站同一类产品的价格并汇总展示
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> f =  future.thenCompose( i -> {
            return CompletableFuture.supplyAsync(() -> {
                return (i * 10) + "";
            });
        });
        System.out.println(f.get());

        //传统的单线程执行
//        singleThreadExecute();

        //使用compeletableFuture实现
//        multiThreadExecute();


//        asyncExecute();

//        asyncExecute2();


    }

    //传统的单线程执行
    private static void singleThreadExecute() {
        long start, end;
        start = System.currentTimeMillis();
        priceOfTM();
        priceOfTB();
        priceOfJD();
        end = System.currentTimeMillis();
        System.out.printf("use serial method call! spend time %s ms", (end - start));
    }

    //使用compeletableFuture实现
    private static void multiThreadExecute() {
        long start, end;
        start = System.currentTimeMillis();
        final CompletableFuture<Double> fb = CompletableFuture.supplyAsync(() -> priceOfTM());
        final CompletableFuture<Double> fc = CompletableFuture.supplyAsync(() -> priceOfTB());
        final CompletableFuture<Double> fd = CompletableFuture.supplyAsync(() -> priceOfJD());
//        CompletableFuture.allOf(fb, fc, fd).join();//所有任务执行结束向下执行
        CompletableFuture.anyOf(fb, fc, fd).join();//任何一个任务执行结束就向下执行
        end = System.currentTimeMillis();
        System.out.printf("use serial method call! spend time %s ms", (end - start));
    }

    private static void asyncExecute() throws IOException {
        CompletableFuture.supplyAsync(() -> priceOfTM())
                .thenApply(price -> String.valueOf(price))
                .thenApply(str -> "price " + str)
                .thenAccept(System.out::println);

        System.in.read();//必须阻塞住,否则上面的异步不会打印结果
    }

    /**
     * 假设有两个线程A和B，这两个线程都是异步执行的，但是不确定A和B何时执行完毕
     * ，但是需要在A和B都执行完毕后运行线程C。
     *
     * @throws IOException
     */
    private static void asyncExecute2() throws IOException {
        CompletableFuture.supplyAsync(() -> priceOfTM())
                .runAfterBothAsync(CompletableFuture.supplyAsync(() -> priceOfTB()), () -> priceOfJD());

        System.in.read();//必须阻塞住,否则上面的异步不会打印结果
    }



    //使用compeletableFuture实现
    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 3.00;
    }

    private static double priceOfJD() {
        delay();
        return 5.00;
    }


    private static void delay() {
        try {
            int time = r.nextInt(500);
            TimeUnit.MILLISECONDS.sleep(r.nextInt(500));
            System.out.printf("After %s ms sleep!\n", time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
