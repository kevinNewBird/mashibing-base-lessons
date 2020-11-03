package com.jmh.test;

import org.openjdk.jmh.annotations.*;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/11/3 23:52
 * @version: 1.0
 ***********************/
public class PSTest {

    @Benchmark
    @Warmup(iterations = 1, time = 3)
    @Fork(5)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 2, time = 3)
    public void testForEach() {
        PS.forEach();
    }

    @Benchmark
    @Warmup(iterations = 1, time = 3)
    @Fork(5)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 2, time = 3)
    public void testParallel() {
        PS.parallel();
    }
}
