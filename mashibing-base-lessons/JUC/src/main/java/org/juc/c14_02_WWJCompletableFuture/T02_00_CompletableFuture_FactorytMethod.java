package org.juc.c14_02_WWJCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/***********************
 * Description: 工厂方法 <BR>
 * @author: zhao.song
 * @date: 2020/10/23 15:30
 * @version: 1.0
 ***********************/
public class T02_00_CompletableFuture_FactorytMethod {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1. 测试异步
        Object v0 = testSupplyAsync().get();
        print(v0);
    }


    //1. 工厂方法:supplyAsync(创建Future实例)
    private static Future<?> testSupplyAsync() {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        return future;
    }


    //2. 工厂方法:runAsync (创建Future实例)(将结果转换为另一个值)
    private static Future<?> testRunAsync() {
        CompletableFuture<?> future = CompletableFuture.runAsync(() -> System.out.println("Hello"));
        return future;
    }

    //3. 构造方法(创建Future实例)
    private static Future<?> testConstructor() {
        return new CompletableFuture<>();
    }

    private static void print(Object value) {
        System.out.printf(">>>>> the value is [ %s ]\n", value);
    }
}
