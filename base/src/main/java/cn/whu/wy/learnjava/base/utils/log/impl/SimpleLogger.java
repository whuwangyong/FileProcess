package cn.whu.wy.learnjava.base.utils.log.impl;

import cn.whu.wy.learnjava.base.utils.log.AbsLogger;

/**
 * 简单的log工具类
 * 存在的问题：由于打印是IO操作，耗时，当有多个线程同时向控制台输出时，可能导致打印的日志交错在一起，显示混乱
 * <p>
 * Author WangYong
 * Date 2020/08/28
 * Time 15:55
 */
public class SimpleLogger extends AbsLogger {

    @Override
    public void info(String s) {
        System.out.println(format(s));
    }
}
