package cn.whu.wy.learnjava.base.thread;

import cn.whu.wy.learnjava.base.utils.log.Logger;
import cn.whu.wy.learnjava.base.utils.log.impl.SimpleLogger;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author WangYong
 * Date 2022/06/20
 */
public class InterruptTest {

    static final Logger log = new SimpleLogger();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    log.info(String.valueOf(i++));
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.info("interrupted");
                    break;
                }
            }
            log.info("done");
        });
        t.start();

        log.info("started");
        TimeUnit.SECONDS.sleep(3);
        log.info(t.getName());
        log.info(String.valueOf(t.getId()));
        log.info(String.valueOf(t.getState()));
        log.info(String.valueOf(t.getThreadGroup()));
        log.info(Arrays.toString(t.getStackTrace()));
        log.info(String.valueOf(t.getUncaughtExceptionHandler()));

        t.interrupt();
        log.info(String.valueOf(t.getState()));
    }

}
