package org.juc.c007_caslock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/***********************
 * Description: 阶段器:模拟婚礼 <BR>
 * @author: zhao.song
 * @date: 2020/10/3 18:25
 * @version: 1.0
 ***********************/
public class T08_TestPhaser1 {


    /**
     * 婚礼阶段:
     * 1.到场
     * 2.吃饭
     * 3.离开
     * 4.拥抱(新娘新郎)
     */
    private static Phaser phaser = new MarriagePhaser();

    private static Random r = new Random();

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        IntStream.range(0,5).forEach(num->{
            new Thread(new Person("p"+num)).start();
        });

        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class MarriagePhaser extends Phaser {

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    //所有人均已到齐
                    System.out.println("所有人抵达婚礼现场![" + registeredParties + "]");
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完饭![" + registeredParties + "]");
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人客人离开了![" + registeredParties + "]");
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("新娘新郎进入洞房![" + registeredParties + "]");
                    System.out.println();
                    return true;
                default:
                    return true;
            }
        }
    }

    public static void millsSleep(long mills) {
        try {
            TimeUnit.MICROSECONDS.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Person implements Runnable {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        // 1.到达现场
        private void doArrive() {
            millsSleep(r.nextInt(1000));
            System.out.println(name + "到达现场!");
            phaser.arriveAndAwaitAdvance();
        }

        // 2.吃饭
        private void doEat() {
            millsSleep(r.nextInt(1000));
            System.out.println(name + "开始吃饭!");
            phaser.arriveAndAwaitAdvance();
        }

        // 3.离开
        private void doLeave() {
            millsSleep(r.nextInt(1000));
            System.out.println(name + "离开!");
            phaser.arriveAndAwaitAdvance();
        }

        // 4.拥抱
        private void doHug() {
            if (name.equals("新郎") || name.equals("新娘")) {
                millsSleep(r.nextInt(1000));
                System.out.println(name + "进入洞房!");
                phaser.arriveAndAwaitAdvance();
            }else{
                phaser.arriveAndDeregister();
//                phaser.register();
            }
        }

        @Override
        public void run() {
            doArrive();

            doEat();

            doLeave();

            doHug();
        }
    }
}
