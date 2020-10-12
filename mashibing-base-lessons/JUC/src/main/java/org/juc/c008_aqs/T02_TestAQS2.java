package org.juc.c008_aqs;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/10/5 13:03
 * @version: 1.0
 ***********************/
public class T02_TestAQS2 {

    public static void main(String[] args) {
        Object tail = new Object();
        System.out.println("tail:"+tail.hashCode());
        Object pred = tail;
        System.out.println("pred:"+pred.hashCode());
        final Object newTail = new Object();
        System.out.println("newTail:"+newTail.hashCode());
        tail = newTail;
        System.out.println("tail:"+tail.hashCode());
        System.out.println("pred:"+pred.hashCode());

    }
}
