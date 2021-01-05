package threadtest;

/**
 * Created by shuipingyue@uxin.com on 2020/10/29.
 */
class DaemonThreadTest extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i <= 100; i++) {
            System.out.println("i ：" + i);
            if (i == 100) {
                System.out.println("线程运行完 i:" + i);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DaemonThreadTest daemon = new DaemonThreadTest();
        // 设置为守护线程，必须在start方法之前
        daemon.setDaemon(true);
        daemon.start();
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":"+i);
        }

    }
}
