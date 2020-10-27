package threadtest;

import android.util.Log;

/**
 * Created by shuipingyue@uxin.com on 2020/10/23.
 */
class Test1Runnable implements Runnable{

    private LockObject lockObject;

    public Test1Runnable(LockObject lockObject) {
        this.lockObject = lockObject;
    }

    @Override
    public void run() {

       // synchronized 使用范围
        synchronized (lockObject) {
            try {
                Log.i("cyp", "test1Runnable:synchronized ");
                Log.i("cyp","test1Runnable wait 方法执行前"+Thread.currentThread().getName()+",state:"+Thread.currentThread().getState());
                lockObject.wait(10000);
                Log.i("cyp", "test1Runnable wait 方法执行后"+Thread.currentThread().getName()+",state:"+Thread.currentThread().getState());
                Thread.sleep(10000);
//                lockObject.notify();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
