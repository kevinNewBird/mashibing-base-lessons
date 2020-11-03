package org.juc.c14_01_ThreadPool;

import java.util.Arrays;
import java.util.Random;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/3 22:34
 * @version: 1.0
 ***********************/
public class T13_ParallelStreamAPI {

    public static void main(String[] args) {
        int[] nums = new int[10000];
        Random r = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(1000000) + 1000000;
        }

        final long start = System.currentTimeMillis();
        Arrays.stream(nums).forEach(num -> isPrime(num));
        final long end = System.currentTimeMillis();
        System.out.println(">> serial:" + (end - start));

        final long start1 = System.currentTimeMillis();
        Arrays.stream(nums).parallel().forEach(num -> isPrime(num));
        final long end1 = System.currentTimeMillis();
        System.out.println(">> parallel:" + (end1 - start1));
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i==0) return false;
        }

        return true;
    }
}
