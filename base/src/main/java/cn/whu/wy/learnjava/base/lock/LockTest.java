package cn.whu.wy.learnjava.base.lock;

import cn.whu.wy.learnjava.base.utils.log.Logger;
import cn.whu.wy.learnjava.base.utils.log.impl.SimpleLogger;

import java.util.concurrent.TimeUnit;

/**
 * @author WangYong
 * Date 2022/06/16
 */
public class LockTest {
    final Object pausedLock = new Object();
    volatile boolean pause = false;

    private static final Logger log = new SimpleLogger();

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
                            TimeUnit.SECONDS.sleep(10); // sleep 不会释放锁
                            pausedLock.wait(); // wait会释放锁
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

/**
 * output: 从日志可以看出，resumed 在打印之后隔了7s才打印notify...，这是因为sleep() 10s期间没有释放锁，知道调用wait()
 * <p>
 * > Task :base:LockTest.main()
 * 2022-06-17 17:06:33.205 21540  --- [          t_biz] y.learnjava.base.lock.LockTest	: do some biz
 * 2022-06-17 17:06:34.244 21540  --- [          t_biz] y.learnjava.base.lock.LockTest	: do some biz
 * 2022-06-17 17:06:35.158 21540  --- [           main] y.learnjava.base.lock.LockTest	: paused
 * 2022-06-17 17:06:35.244 21540  --- [          t_biz] y.learnjava.base.lock.LockTest	: wait...
 * 2022-06-17 17:06:38.158 21540  --- [           main] y.learnjava.base.lock.LockTest	: resumed
 * 2022-06-17 17:06:45.244 21540  --- [           main] y.learnjava.base.lock.LockTest	: notify...
 * 2022-06-17 17:06:45.244 21540  --- [          t_biz] y.learnjava.base.lock.LockTest	: get notified
 * 2022-06-17 17:06:45.244 21540  --- [          t_biz] y.learnjava.base.lock.LockTest	: do some biz
 * 2022-06-17 17:06:46.245 21540  --- [          t_biz] y.learnjava.base.lock.LockTest	: do some biz
 * 2022-06-17 17:06:47.245 21540  --- [          t_biz] y.learnjava.base.lock.LockTest	: do some biz
 */
