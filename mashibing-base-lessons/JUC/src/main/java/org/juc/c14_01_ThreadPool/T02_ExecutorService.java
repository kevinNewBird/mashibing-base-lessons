package org.juc.c14_01_ThreadPool;

import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/22 14:45
 * @version: 1.0
 ***********************/
public class T02_ExecutorService {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
    }

}
