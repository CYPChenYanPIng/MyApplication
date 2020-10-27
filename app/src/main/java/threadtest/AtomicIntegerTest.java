package threadtest;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shuipingyue@uxin.com on 2020/10/27.
 */
class AtomicIntegerTest {

    private static final int THREADS_COUNT = 20;
//    public static volatile int count = 0;
    public static AtomicInteger count = new AtomicInteger(0);

    public static void increase() {
//        count++;
        count.incrementAndGet();
    }


    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT ;i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        int i = Thread.currentThread().getThreadGroup().activeCount();
        int activeCount = Thread.activeCount();
        System.out.println("i:"+i+",activeCount:"+activeCount);


        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        int jieshu = Thread.currentThread().getThreadGroup().activeCount();
        System.out.println("jieshu:"+jieshu+",activeCount:"+Thread.activeCount());

        System.out.println(count);
    }
}
