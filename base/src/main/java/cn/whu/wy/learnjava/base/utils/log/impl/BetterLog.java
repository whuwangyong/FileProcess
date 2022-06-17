package cn.whu.wy.learnjava.base.utils.log.impl;

import cn.whu.wy.learnjava.base.utils.log.Log;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

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

    private final ArrayBlockingQueue<String> logs;

    public BetterLog() {
        logs = new ArrayBlockingQueue<>(1000_000);
        startPrintService();
    }

    @SneakyThrows
    @Override
    public void info(String s) {
        String threadName = Thread.currentThread().getName();
        String className = getCallerClassName();

        StringBuilder sb = new StringBuilder();
        sb.append(formatter.format(LocalDateTime.now()));
        sb.append(" ").append(ProcessHandle.current().pid()).append(" ");
        sb.append(" --- ");
        sb.append("[").append(getFixedString(threadName, 15)).append("]");
        sb.append(" ").append(getFixedString(className, 30));
        sb.append("\t: ");
        sb.append(s);
        logs.put(sb.toString());
    }

    private String getCallerClassName() {
        // way 1：
         String callerClassName = new Exception().getStackTrace()[2].getClassName();
        // String calleeClassName = new Exception().getStackTrace()[0].getClassName();
        return callerClassName;

        // way 2：
//        StackWalker walker = StackWalker.getInstance(RETAIN_CLASS_REFERENCE);
//        return walker.getCallerClass().getName();
    }

    /**
     * 获取定长的字符串，超过的部分截断左边；不足的部分左边补空格
     */
    private String getFixedString(String s, int size) {
        int len = s.length();
        if (len >= size) {
            return s.substring(len - size);
        } else {
            char[] cs = new char[size - len];
            for (int i = 0; i < size - len; i++) {
                cs[i] = ' ';
            }
            return new String(cs) + s;
        }
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
