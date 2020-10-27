package threadtest;

import android.util.Log;

/**
 * Created by shuipingyue@uxin.com on 2020/10/26.
 */
class Test2Runnable implements Runnable{
    private static final String TAG = "cyp";
    private LockObject lockObject;

    public Test2Runnable(LockObject lockObject) {
        this.lockObject = lockObject;
    }

    @Override
    public void run() {
        synchronized (lockObject) {
            lockObject.notifyAll();
            Log.i(TAG, "run: Test2Runnable 还在运行");
        }
    }
}
