package threadtest;


import android.util.Log;

/**
 * Created by shuipingyue@uxin.com on 2020/10/21.
 */
public class ThreadTest1 extends Thread{


    private Object object = new Object();

    private LockObject lockObject ;

    public ThreadTest1() {
    }

    public ThreadTest1(Runnable target, String name) {
        super(target, name);
    }

    public ThreadTest1(LockObject lockObject) {
        this.lockObject = lockObject;
    }

    @Override
    public void run() {
        super.run();
        Log.i("cyp", "run: thread1");
//        Log.i("cyp", "thread1:"+this.getState());
//        Log.i("cyp", "thread1:"+this.getThreadGroup().getName());
//        Log.i("cyp", "thread1: "+this.getThreadGroup().activeCount());
        synchronized (lockObject) {
            try {
//                Log.i("cyp", "Thread1: synchronized");
                Log.i("cyp", "Thread1   wait 方法执行前"+this.getState());
                lockObject.wait(10000);
                Log.i("cyp", "Thread1   wait 方法执行后"+this.getState());
                sleep(10000);
                Log.i("cyp", "Thread1   sleep 方法结束"+this.getState());
                Log.i("cyp","thread:"+Thread.currentThread());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        synchronized (ThreadTest1.class){
            try {
                ThreadTest1.class.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        try {
//            Log.i("cyp", "Thread1   sleep 方法kaishi"+this.getState());
//            sleep(20000);
//            Log.i("cyp", "Thread1   sleep 方法jieshu"+this.getState());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

////        // 这个线程是否被中断
//        Log.i("cyp", "run: "+this.isInterrupted());
//        if (isInterrupted()) {
//            // 当前线程是否被中断返回为true，并清除当前线程的终端标识。
//            Log.i("cyp", "run: "+this.interrupted());
//            return;
//        }
//        // 终端标识被清除，返回为false
//        Log.i("cyp", "run: "+this.isInterrupted());
////       再次清除中断标识
//        this.interrupted();
//        // 这个线程是否被中断，返回false
//        Log.i("cyp", "run: "+this.isInterrupted());

        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
