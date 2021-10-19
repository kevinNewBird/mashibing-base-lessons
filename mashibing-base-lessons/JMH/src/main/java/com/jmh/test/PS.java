package com.jmh.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***********************
 * Description: 测试基类 <BR>
 * @author: zhao.song
 * @date: 2020/11/3 23:39
 * @version: 1.0
 ***********************/
public class PS {

    static List<Integer> nums = new ArrayList<>();

    static Random r = new Random();

    static {
        for (int i = 0; i < 1000; i++) {
            nums.add(1000_000 + r.nextInt(1000_000));
        }
    }

    public static void forEach() {
        nums.forEach(PS::isPrime);
    }

    public static void parallel() {
        nums.parallelStream().forEach(PS::isPrime);
    }

    /**
     * Description: 质数判断 <BR>
     *
     * @param num:
     * @return {@link boolean}
     * @author zhao.song    2020/11/3 23:40
     */
    private static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
