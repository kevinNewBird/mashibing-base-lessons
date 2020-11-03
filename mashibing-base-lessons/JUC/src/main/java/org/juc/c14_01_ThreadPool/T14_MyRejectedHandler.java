package org.juc.c14_01_ThreadPool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/3 0:44
 * @version: 1.0
 ***********************/
public class T14_MyRejectedHandler {


    public static void main(String[] args) {
        final ThreadPoolExecutor service = new ThreadPoolExecutor(10, 20
                , 10, TimeUnit.MILLISECONDS
                , new ArrayBlockingQueue<>(6)
                , new BasicThreadFactory.Builder().namingPattern("custom-rejected-handler-pool-%s").build()
                , new MyHandler());
    }

    static class MyHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //log("r rejected")
            //save r kafka mysql redis
            //try 3 times
            if (executor.getQueue().size() < 10000) {
                //try put again
            }
        }
    }
}
