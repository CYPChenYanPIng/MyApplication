package threadtest;

import android.util.Log;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by shuipingyue@uxin.com on 2020/11/30.
 */
public class ChenThreadMannager {

    // volatile
    private static volatile ChenThreadMannager mannager;

    private ThreadPoolExecutor executor;

    private final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    // 为什么加5
    private final int MAX_POOL_SIZE = CORE_POOL_SIZE + 5;

    private PriorityBlockingQueue<Runnable> blockingQueue = new PriorityBlockingQueue<>();

    private ChenThreadMannager() {
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE,MAX_POOL_SIZE,1,TimeUnit.SECONDS,blockingQueue);
        Log.i("cyp", "ChenThreadMannager: core:"+CORE_POOL_SIZE);
    }

    public static ChenThreadMannager getInstance() {
        if (mannager == null) {
            synchronized (ChenThreadMannager.class) {
                if (null == mannager) {
                    mannager = new ChenThreadMannager();
                }
            }
        }
        return mannager;
    }

}
