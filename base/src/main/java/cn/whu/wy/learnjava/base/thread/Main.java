package cn.whu.wy.learnjava.base.thread;

import cn.whu.wy.learnjava.base.utils.log.Logger;
import cn.whu.wy.learnjava.base.utils.log.impl.BetterLogger;

/**
 * Author WangYong
 * Date 2020/08/31
 * Time 10:34
 */
public class Main {
    private static Logger log = new BetterLogger();

    public static void main(String[] args) {
        DataHolder dataHolder = new DataHolder();
        ServiceRegistry serviceRegistry = new ServiceRegistry();

        AvgService avgService = new AvgService();


        log.info("main thread is done");
    }

    public static void init() {
        IocContainer iocContainer = new IocContainer();
        DataHolder dataHolder = new DataHolder();

    }
}
