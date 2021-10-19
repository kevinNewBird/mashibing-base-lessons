package com.disruptor.simpledemo;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/4 0:56
 * @version: 1.0
 ***********************/
public class Main1 {

    public static void main(String[] args) {
        // 1.The factory for the event
        final LongEventFactory factory = new LongEventFactory();

        // 2.Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1 << 10;

        // 3.Construct the Disruptor
        // reg.生产者往ringbuffer 扔event, event会被线程工厂里的某一线程获取出来并调用longEventHandler的方法处理event
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, Executors.defaultThreadFactory());

        // 4.Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // 5.Start the Disruptor, Starts all threads running.
        disruptor.start();

        // 6.Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();


        // 6.官方例程
        // Grab the next sequence
        Random r = new Random();
        IntStream.range(0,100).forEach(num->{
            long sequence = ringBuffer.next();
            try {
                // Get the entry in the Disruptor
                LongEvent event = ringBuffer.get(sequence);
                // for the sequence , Fill with data.
                event.set(r.nextInt(1000_000));
            } finally {
                ringBuffer.publish(sequence);
            }
        });



    }
}
