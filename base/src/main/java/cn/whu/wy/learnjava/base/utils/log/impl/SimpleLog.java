package cn.whu.wy.learnjava.base.utils.log.impl;

import cn.whu.wy.learnjava.base.utils.log.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 简单的log工具类
 * 存在的问题：由于打印是IO操作，耗时，当有多个线程同时向控制台输出时，可能导致打印的日志交错在一起，显示混乱
 * <p>
 * Author WangYong
 * Date 2020/08/28
 * Time 15:55
 */
public class SimpleLog implements Log {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void info(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatter.format(LocalDateTime.now()));
        sb.append(" --- ");
        sb.append(Thread.currentThread().getName());
        sb.append(":    ");
        sb.append(s);
        System.out.println(sb.toString());
    }
}
