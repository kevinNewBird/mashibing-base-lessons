package com.disruptor.simpledemo;


import com.lmax.disruptor.EventHandler;

/***********************
 * Description: 事件消费者 <BR>
 * @author: zhao.song
 * @date: 2020/11/4 0:50
 * @version: 1.0
 ***********************/
public class LongEventHandler implements EventHandler<LongEvent> {

    public static long count = 0;

    /**
     * Description: TODO <BR>
     *
     * @author zhao.song    2020/11/4 0:57
     * @param event:
     * @param sequence:RingBuffer的序列号
     * @param endOfBatch:  是否为最后一个元素
     * @return
     */
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        count++;
        System.out.println("[" + Thread.currentThread().getName() + "]" + event + "序列号:" + sequence);

    }
}
