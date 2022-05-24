package cn.whu.wy.learnjava.base.utils.log.impl;

import cn.whu.wy.learnjava.base.utils.log.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 改进后的log工具类
 * 先将要打印的日志放进队列，然后另起一个线程慢慢打印
 * <p>
 * Author WangYong
 * Date 2020/08/28
 * Time 15:03
 */
public class BetterLog implements Log {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private Queue<String> logs;

    public BetterLog() {
        logs = new ArrayBlockingQueue<>(1000_000);
        startPrintService();
    }

    @Override
    public void info(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatter.format(LocalDateTime.now()));
        sb.append(" --- ");
        sb.append(Thread.currentThread().getName());
        sb.append(":    ");
        sb.append(s);
        logs.add(sb.toString());
    }

    private void startPrintService() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
                    String log = logs.poll();
                    if (log != null) {
                        System.out.println(log);
                    }
                }
                ,
                1000, 100, TimeUnit.MILLISECONDS);
    }
}
