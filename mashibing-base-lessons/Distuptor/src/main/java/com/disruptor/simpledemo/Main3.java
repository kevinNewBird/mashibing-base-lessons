package com.disruptor.simpledemo;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Arrays;
import java.util.concurrent.Executors;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/4 22:00
 * @version: 1.0
 ***********************/
public class Main3 {

    public static void main(String[] args) {
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new
                , 1 << 10, Executors.defaultThreadFactory());

        disruptor.handleEventsWith((event, sequence, endOfBatch)
                -> System.out.println("[" + Thread.currentThread().getName() + "]" + event + "序列号:" + sequence));

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ringBuffer.publishEvent((event, sequence, longarray) -> event.set(Arrays.stream(longarray)
                        .map(ele -> Integer.parseInt(ele.toString())).count()),
                1, 2, 3);
    }
}
