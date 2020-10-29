package org.juc.c13_Containers2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/15 18:00
 * @version: 1.0
 ***********************/
public class T03_SynchronizedList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> syncList = Collections.synchronizedList(list);
    }
}
