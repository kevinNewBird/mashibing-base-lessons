package org.juc.c002_synchronized;

import java.util.concurrent.TimeUnit;

/***********************
 * Description: 锁细化和粗化 <BR>
 * @author: zhao.song
 * @date: 2020/9/24 17:25
 * @version: 1.0
 ***********************/
public class TestSyncLockToFineCoarse {


    int count = 0;

    synchronized void testCoarseLock() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑不需要上锁
        count++;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void testFineLock() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //细化锁
        synchronized (this) {
            count++;
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
