package com.disruptor.simpledemo;

import com.lmax.disruptor.EventFactory;

/***********************
 * Description: 事件工厂 <BR>
 * @author: zhao.song
 * @date: 2020/11/4 0:44
 * @version: 1.0
 ***********************/

public class LongEventFactory implements EventFactory<LongEvent> {


    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }

}
