package org.juc.c009_RefTypeAndThreadLocal;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/5 22:44
 * @version: 1.0
 ***********************/
public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
