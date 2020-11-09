package com.disruptor.simpledemo;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/4 23:23
 * @version: 1.0
 ***********************/
public class Main7_ExceptionHandler {

    public static void main(String[] args) throws InterruptedException {
/*        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, 1 << 10
                , Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());*/

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, 1 << 10
                , Executors.defaultThreadFactory(), ProducerType.MULTI, new SleepingWaitStrategy());

        //指定多个消费者(也就是多个线程)
        EventHandler h1 = (event, sequence, endOfBatch) -> {
            System.out.println(event);
            throw new Exception("消费者发生异常!");
        };
        disruptor.handleEventsWith(h1);
        disruptor.handleExceptionsFor(h1).with(new ExceptionHandler<LongEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, LongEvent event) {
                ex.printStackTrace();
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                System.out.println("Exception Start to Handle!");
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                System.out.println("Exception Handled!");
            }
        });

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        int threadNum = 1;
        CyclicBarrier barrier = new CyclicBarrier(threadNum);
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < threadNum; i++) {
            final int currThreadNum = i;
            executor.submit(() -> {
                System.out.printf("thread %s ready to start!\n", currThreadNum);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                IntStream.range(0, 1).forEach(num -> {
                    ringBuffer.publishEvent(((event, sequence, arg0) -> {
                        event.set(num);
                        System.out.println("生产了" + num);
                    }), num);
                });
            });
        }

        executor.shutdown();
        TimeUnit.SECONDS.sleep(3);

        System.out.println(LongEventHandler.count);

    }
}
