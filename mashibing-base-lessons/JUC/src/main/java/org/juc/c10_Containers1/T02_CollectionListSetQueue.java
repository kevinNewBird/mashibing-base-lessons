package org.juc.c10_Containers1;

import java.util.*;

public class T02_CollectionListSetQueue {
    public static void main(String[] args) {
//        Collection<Integer> c1 = new ArrayList();
//        List<Integer> c1 = new ArrayList<>();
//        Set<Integer> c1 = new HashSet<>();
        Queue<Integer> c1 = new LinkedList<>();
        c1.add(1);
        c1.add(2);
        c1.add(3);
        c1.stream().forEach(System.out::println);



    }
}
