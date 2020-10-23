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
                Log.i("cyp", "Thread1: synchronized");
                Log.i("cyp", "Thread1   wait 方法执行前");
                lockObject.wait(10000);
                Log.i("cyp", "Thread1   wait 方法执行后");
                sleep(10000);
                Log.i("cyp", "Thread1   sleep方法结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
