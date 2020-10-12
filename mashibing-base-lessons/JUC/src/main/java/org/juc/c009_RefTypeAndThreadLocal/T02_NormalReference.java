package org.juc.c009_RefTypeAndThreadLocal;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/***********************
 * Description: 引用: 正常引用 <BR>
 * @author: zhao.song
 * @date: 2020/10/5 22:36
 * @version: 1.0
 ***********************/
public class T02_NormalReference {

    public static void main(String[] args) throws InterruptedException, IOException {
        M m = new M();
        m = null;//为了取消引用
        System.gc();//是在别的线程中进行的,所以需在下方使用read进行阻塞

        System.in.read();//只是为了阻塞

    }
}
