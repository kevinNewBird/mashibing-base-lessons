package org.juc.c14_01_ThreadPool;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/2 23:37
 * @version: 1.0
 ***********************/
public class T11_ScheduledThreadPool {

    public static void main(String[] args) {

        final ScheduledExecutorService service = Executors.newScheduledThreadPool(10,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%s").build());


/*        IntStream.range(0, 10).forEach(num -> {
            service.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " is running.");
            });

        });*/


        service.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + " is running.");
        }, 0, 500, TimeUnit.MILLISECONDS);


    }
}
