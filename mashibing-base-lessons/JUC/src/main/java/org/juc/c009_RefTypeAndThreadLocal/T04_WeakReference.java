package org.juc.c009_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;

/***********************
 * Description: 弱引用遭到gc就会回收 <BR>
 * @author: zhao.song
 * @date: 2020/10/5 23:09
 * @version: 1.0
 ***********************/
public class T04_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();

        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}
