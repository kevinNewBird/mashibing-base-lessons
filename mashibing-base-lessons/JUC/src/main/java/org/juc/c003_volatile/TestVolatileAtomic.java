package org.juc.c003_volatile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: volatile并不能保证多个线程共同修改running变量时所带来的不一致问题 ,
 * 也就是说volatile不能替代synchronized运行下面的程序,并分析结果<BR>
 * @author: zhao.song
 * @date: 2020/9/24 16:30
 * @version: 1.0
 ***********************/
public class TestVolatileAtomic {

    volatile int count = 0;

    public /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++) {
            //不是原子性的操作
            count++;
        }
    }


    public static void main(String[] args) {
        final TestVolatileAtomic runnable = new TestVolatileAtomic();

        List<Thread> threadList = new ArrayList<>();

        IntStream.range(0, 10).forEach(i -> {
            threadList.add(new Thread(runnable::m, String.valueOf(i)));
        });

        //1.方式1
        threadList.forEach(thread -> thread.start());

        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //2.方式2
        /*threadList.forEach((thread) -> {
            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            thread.start();
        });*/

        System.out.println(runnable.count);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(runnable.count);

    }
}
