package org.juc.c004_singleton;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/9/28 0:24
 * @version: 1.0
 ***********************/
public class SingletonInstanceTest {


    public static void main(String[] args) {
        System.out.println(Mgr6.SingletonInstance.INSTANCE.hashCode());
        System.out.println(Mgr6.SingletonInstance.INSTANCE.hashCode());
    }
}
