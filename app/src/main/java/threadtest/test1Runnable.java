package threadtest;

import android.util.Log;

/**
 * Created by shuipingyue@uxin.com on 2020/10/23.
 */
class test1Runnable implements Runnable{

    private LockObject lockObject;

    public test1Runnable( LockObject lockObject) {
        this.lockObject = lockObject;
    }

    @Override
    public void run() {
        synchronized (lockObject) {
            try {
                Log.i("cyp", "test1Runnable:synchronized ");
                Log.i("cyp","test1Runnable wait 方法执行前");
                lockObject.wait(10000);
                Log.i("cyp", "test1Runnable wait 方法执行后");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
