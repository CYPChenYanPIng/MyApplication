package threadtest;

import android.util.Log;

/**
 * Created by shuipingyue@uxin.com on 2020/10/26.
 */
class ThreadTest2  extends Thread{
    private int i ;
    // 上一个线程
    private Thread previousThread;

    public ThreadTest2(int i, Thread previousThread) {
        this.i = i;
        this.previousThread = previousThread;
    }

    @Override
    public void run() {
        super.run();
        // 先注释掉join代码
        try {
            previousThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("num:"+i);
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 0;i < 10;i++){
            ThreadTest2 threadTest2 = new ThreadTest2(i,previousThread);
            threadTest2.start();
            previousThread = threadTest2;
        }

    }
}
