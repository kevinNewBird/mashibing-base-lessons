package org.juc.c14_01_ThreadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/3 21:08
 * @version: 1.0
 ***********************/
public class T12_01_ForkJoinPool {

    static int[] nums = new int[1000_000];
    static int MAX_NUM = 500_000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(1000);
        }
        System.out.println(">>" + Arrays.stream(nums).sum());
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        final ForkJoinPool pool = new ForkJoinPool();
        MyTask myTask = new MyTask(0, nums.length);
        ForkJoinTask<Void> task = pool.submit(myTask);
        task.join();
        /*MyTaskRet myTaskRet = new MyTaskRet(0, nums.length);
        ForkJoinTask<Long> task = pool.submit(myTaskRet);
        Long result = task.join();
        System.out.println(result);*/
//        System.in.read();
    }


    public static class MyTask extends RecursiveAction {

        private final int start, end;

        public MyTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * The main computation performed by this task.
         */
        @Override
        protected void compute() {
            if ((end - start) <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from: " + start + " to: " + end + " = " + sum);
            } else {
                int middle = start + (end - start) / 2;
                MyTask subTask1 = new MyTask(start, middle);
                MyTask subTask2 = new MyTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }

    public static class MyTaskRet extends RecursiveTask<Long> {

        private final int start, end;

        public MyTaskRet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * The main computation performed by this task.
         */
        @Override
        protected Long compute() {
            if ((end - start) <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            } else {
                int middle = start + (end - start) / 2;
                MyTaskRet subTask1 = new MyTaskRet(start, middle);
                MyTaskRet subTask2 = new MyTaskRet(middle, end);
                subTask1.fork();
                subTask2.fork();
                return subTask1.join() + subTask2.join();
            }
        }
    }


}

