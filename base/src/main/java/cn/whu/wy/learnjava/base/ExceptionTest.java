package cn.whu.wy.learnjava.base;

import cn.whu.wy.learnjava.base.utils.log.Logger;
import cn.whu.wy.learnjava.base.utils.log.impl.BetterLogger;

import java.util.concurrent.Executors;

/**
 * Author WangYong
 * Date 2020/08/28
 * Time 14:34
 */
public class ExceptionTest {

    private static Logger log = new BetterLogger();

    public static void main(String[] args) {
        ExceptionTest exceptionTest = new ExceptionTest();
        exceptionTest.printHeartbeat();
//        exceptionTest.cacheException();
        exceptionTest.unCacheException();

        log.info("main done");
    }

    /**
     * 抛出异常时，由于进行了捕获，该线程能继续运行，继续打印6，7，8，...
     */
    void cacheException() {
        Executors.newSingleThreadExecutor().execute(() -> {
            int i = 0;

            while (true) {
                try {
                    i++;
                    if (i == 5) {
                        throw new NullPointerException();
                    }
                    log.info(i + "");
                    Thread.sleep(500);
                } catch (NullPointerException npe) {
                    log.info("NullPointerException");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 异常未处理，导致该线程终止。但不影响其他线程的正常运行。
     * ==》因此，可以考虑两个线程互相守护，发现对方不在了就重启一个。就像流氓APP。
     */
    void unCacheException() {
        Executors.newSingleThreadExecutor().execute(() -> {
            int i = 0;

            while (true) {
                i++;
                if (i == 5) {
                    throw new NullPointerException();
                }
                log.info(i + "");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    void printHeartbeat() {
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                log.info("I am still alive.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
