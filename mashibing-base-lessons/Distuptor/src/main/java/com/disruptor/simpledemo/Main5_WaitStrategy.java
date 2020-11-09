package com.disruptor.simpledemo;

import com.lmax.disruptor.BlockingWaitStrategy;
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
public class Main5_WaitStrategy {

    public static void main(String[] args) throws InterruptedException {
/*        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, 1 << 10
                , Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());*/

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new, 1 << 10
                , Executors.defaultThreadFactory(), ProducerType.MULTI, new SleepingWaitStrategy());

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        int threadNum = 50;
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
                IntStream.range(0, 100).forEach(num -> {
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
