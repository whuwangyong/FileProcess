package cn.whu.wy.learnjava.base.utils.log.impl;

import cn.whu.wy.learnjava.base.utils.log.AbsLogger;
import lombok.SneakyThrows;

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
public class BetterLogger extends AbsLogger {

    private final ArrayBlockingQueue<String> logs;

    public BetterLogger() {
        logs = new ArrayBlockingQueue<>(1000_000);
        startPrintService();
    }

    @SneakyThrows
    @Override
    public void info(String s) {
        logs.put(format(s));
    }

    private void startPrintService() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
                    try {
                        System.out.println(logs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                1000, 10, TimeUnit.MILLISECONDS);
    }
}
