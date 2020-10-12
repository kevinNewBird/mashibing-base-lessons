package org.juc.c003_volatile;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/***********************
 * Description: volatile关键字 <BR>
 * @author: zhao.song
 * @date: 2020/9/22 23:26
 * @version: 1.0
 ***********************/
public class TestVolatile {


    private /*volatile*/ boolean RUN_STATE = true;//对比一下有无volatile的情况下,整个程序运行结果的区别

    public void test0() {
        Optional.of("the application ready to run.").ifPresent(System.out::println);
        int count = 0;
        while (RUN_STATE) {//jdk1.8的JIT即时编译器优化: 对于while如果循环中没有任何操作,优化为while(true)
            /*try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Optional.of("the application running. output value:"+(++count)).ifPresent(System.out::println);*/
        }
        Optional.of("the application run over.").ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        TestVolatile runnable = new TestVolatile();
        new Thread(runnable::test0, "T1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runnable.RUN_STATE = false;
    }
}
