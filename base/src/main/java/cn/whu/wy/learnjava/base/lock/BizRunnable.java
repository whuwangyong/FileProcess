package cn.whu.wy.learnjava.base.lock;

import cn.whu.wy.learnjava.base.utils.log.Logger;
import cn.whu.wy.learnjava.base.utils.log.impl.SimpleLogger;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author WangYong
 * Date 2022/06/17
 */
public class BizRunnable implements Runnable {

    final Object pauseLock = new Object();
    volatile boolean pause = false;
    Logger log = new SimpleLogger();

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            if (pause) {
                synchronized (pauseLock) {
                    log.info("wait...");
                    pauseLock.wait();
                    log.info("resumed");
                }
            }
            log.info("do some biz");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void pause() {
        pause = true;
        log.info("pause");
    }

    public void resume() {
        pause = false;
        synchronized (pauseLock) {
            log.info("notify...");
            pauseLock.notify();
        }
    }
}
