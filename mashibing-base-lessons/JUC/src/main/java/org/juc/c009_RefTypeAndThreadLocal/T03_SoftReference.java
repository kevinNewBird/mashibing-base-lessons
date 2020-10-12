package org.juc.c009_RefTypeAndThreadLocal;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/5 22:52
 * @version: 1.0
 ***********************/
public class T03_SoftReference {

    //软引用非常适合缓存使用
    public static void main(String[] args) throws InterruptedException {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(m.get());
        System.gc();

        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println(m.get());

        //再分配一个数据 , heap讲装不下就会把之前的软引用干掉(在idea设置堆内存参数)
        byte[] b = new byte[1024 * 1024 * 12];//过大会导致抛出OutOfMemoryError
        System.out.println(m.get());
    }
}
