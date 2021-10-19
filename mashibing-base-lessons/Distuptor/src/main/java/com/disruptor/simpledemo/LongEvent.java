package com.disruptor.simpledemo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/***********************
 * Description:事件 <BR>
 * @author: zhao.song
 * @date: 2020/11/4 0:43
 * @version: 1.0
 ***********************/
public class LongEvent {

    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }

    public static void main(String[] args) {
        String[] strArray = "43,13".split(",");
        final int[] intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        boolean isBelongSiTuoSite = Arrays.stream(intArray)
                .boxed().distinct().anyMatch(siteIdFromDB -> siteIdFromDB == 13);
        System.out.println(isBelongSiTuoSite);
        /*System.out.println(LongEvent.class.getName());
        final Date date = new Date(1604567689842L);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));*/
    }
}
