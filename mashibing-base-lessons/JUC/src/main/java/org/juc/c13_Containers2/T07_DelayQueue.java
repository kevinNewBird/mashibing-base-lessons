package org.juc.c13_Containers2;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/18 23:40
 * @version: 1.0
 ***********************/
public class T07_DelayQueue {

    static BlockingQueue<MyTask> oDelayQueue = new DelayQueue();

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {

        MyTask task01 = new MyTask("t1", 5000 + System.currentTimeMillis());
        MyTask task02 = new MyTask("t2", 4000 + System.currentTimeMillis());
        MyTask task03 = new MyTask("t3", 2000 + System.currentTimeMillis());
        MyTask task04 = new MyTask("t4", 3000 + System.currentTimeMillis());
        oDelayQueue.put(task01);
        oDelayQueue.put(task02);
        oDelayQueue.put(task03);
        oDelayQueue.put(task04);
        IntStream.range(0, 4).forEach(num -> {
            MyTask task = null;
            try {
                task = oDelayQueue.take();
                System.out.println(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });


    }

    private static class MyTask implements Delayed {
        //任务名称
        private String taskName;
        //指定任务的执行时间
        private long taskExecuteTime;

        public MyTask(String taskName, long taskExecuteTime) {
            this.taskName = taskName;
            this.taskExecuteTime = taskExecuteTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(taskExecuteTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }


        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "taskName='" + taskName + '\'' +
                    ", taskExecuteTime=" + taskExecuteTime +
                    '}';
        }
    }
}
