package org.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Vector<Integer> source = new Vector<>();
        source.add(1);
        source.add(2);

        List<Integer> target = new ArrayList<>();
        target.add(1);
        target.add(2);
        target.add(3);
        target.removeAll(source);

        System.out.println(target.toString());
    }
}
