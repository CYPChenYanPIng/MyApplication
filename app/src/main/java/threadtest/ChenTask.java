package threadtest;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * FutureTask<Integer>  Integer是做什么用的
 * Created by shuipingyue@uxin.com on 2020/11/30.
 */
public class ChenTask extends FutureTask<Integer> implements Comparable<ChenTask> {

    /**
     * 任务优先级：低
     */
    public static final int PRIORITY_LOW = -1;
    /**
     * 任务优先级：普通
     */
    public static final int PRIORITY_NORMAL = 0;
    /**
     * 任务优先级：高
     */
    public static final int PRIORITY_HIGH = 1;
    /**
     * 任务优先级：最高，立刻执行
     */
    public static final int PRIORITY_TOP = 100;


    /**
     * 初始空闲状态
     */
    public static final int IDEL = 1 << 0;
    /**
     * 准备执行状态（任务即将被线程执行）
     */
    public static final int PREPARED = 1 << 1;
    /**
     * 任务正在运行状态
     */
    public static final int RUNNING = 1 << 2;

    /**
     * 任务运行结束状态
     */
    public static final int FINISHED = 1 << 3;

    /**
     * 任务状态：idle-->prepared-->running-->finish
     * 在idel和prepared状态是可以停止，一旦进入running后设置停止不生效
     */
    private int currentState = IDEL;
    /**
     * 任务是否设置了停止状态，这里只是个标识符，并不会立即停止任务，只有在run时发现该标识设置为true了会直接return
     * 不再执行Runnable里的操作了。
     */
    private boolean stoped;

    private static final int RESULT_SUCCESS = 0;

    private final AtomicInteger integer = new AtomicInteger(0);

    private int seqNum ;

    private int priority;

   public ChenTask(Runnable runnable,int priority) {
       super(runnable,RESULT_SUCCESS);
       seqNum = integer.getAndIncrement();
   }

    @Override
    public int compareTo(ChenTask o) {
        return 0;
    }

    @Override
    public void run() {
       // 是否停止，
        if (stoped) {
            return;
        }
        // 更新任务状态
        currentState = RUNNING;
        super.run();

    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void stopTask() {
        if (currentState == IDEL || currentState == PREPARED) {
            stoped = true;
            currentState = FINISHED;
        }
    }

}
