package cn.whu.wy.learnjava.base.lock;

import cn.whu.wy.learnjava.base.utils.log.Log;
import cn.whu.wy.learnjava.base.utils.log.impl.BetterLog;

import java.util.concurrent.TimeUnit;

/**
 * @author WangYong
 * Date 2022/06/16
 */
public class LockTest {
    final Object pausedLock = new Object();
    volatile boolean pause = false;

    private static Log log = new BetterLog();

    public static void main(String[] args) throws InterruptedException {
        LockTest test = new LockTest();
        test.run();

        TimeUnit.SECONDS.sleep(2);
        test.pause();

        TimeUnit.SECONDS.sleep(3);
        test.resume();
    }


    void pause() {
        pause = true;
        log.info("paused");
    }

    void resume() {
        pause = false;
        log.info("resumed");
        synchronized (pausedLock) {
            log.info("notify...");
            pausedLock.notify();
        }
    }

    void run() {
        Thread biz = new Thread(() -> {
            while (true) {
                if (pause) {
                    synchronized (pausedLock) {
                        try {
                            log.info("wait...");
                            pausedLock.wait();
                            log.info("get notified");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                log.info("do some biz");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        biz.setName("t_biz");
        biz.start();
    }

}
