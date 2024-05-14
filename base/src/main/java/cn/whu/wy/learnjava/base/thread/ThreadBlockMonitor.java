package cn.whu.wy.learnjava.base.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * Java如何查看一个线程等待/阻塞了多少时间
 */
public class ThreadBlockMonitor {
    private static final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    private static final Map<Long, Long> blockedTimeMap = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {

        // 启用线程争用监视
        if (!threadMXBean.isThreadContentionMonitoringEnabled()) {
            threadMXBean.setThreadContentionMonitoringEnabled(true);
        }


        Thread testThread = new Thread(() -> {
            try {
                synchronized (ThreadBlockMonitor.class) {
                    ThreadBlockMonitor.class.wait(1000*5);  // Simulate a block for 5 seconds
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        testThread.start();

        // Start monitoring the thread
        long threadId = testThread.threadId();
        while (testThread.isAlive()) {
            long blockedTime = threadMXBean.getThreadInfo(threadId).getWaitedTime();
            if (blockedTime != -1) {
                blockedTimeMap.put(threadId, blockedTime);
            }
            Thread.sleep(100);  // Adjust the sleep time as needed
        }

        System.out.println("Blocked time: " + blockedTimeMap.get(threadId) + " ms");
    }
}
