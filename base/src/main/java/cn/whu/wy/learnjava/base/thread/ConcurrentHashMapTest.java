package cn.whu.wy.learnjava.base.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author WangYong
 * Date 2022/06/30
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) throws InterruptedException {

        // test several times
        int testCases = 5;
        while (testCases-- > 0) {
            Map<String, Integer> map = new ConcurrentHashMap<>();

            // Hashtable is also wrong, although get() and put() is synchronized, but they are not atomic as a whole
            // Map<String, Integer> map = new Hashtable<>();

            map.put("key", 0);

            // start some threads, each of them do "+1" many times
            final int THREADS = 4;
            final int TIMES = 1000;
            Runnable runnable = () -> {
                for (int i = 0; i < TIMES; i++) {
                    int key = map.get("key") + 1; // step 1
                    map.put("key", key); // step 2
                }
            };
            List<Thread> lt = new ArrayList<>(THREADS);
            for (int i = 0; i < THREADS; i++) {
                Thread t = new Thread(runnable);
                t.start();
                lt.add(t);
            }

            // wait all threads completed
            while (true) {
                long terminatedThreads = lt.stream().filter(thread -> thread.getState() == Thread.State.TERMINATED).count();
                if (terminatedThreads == THREADS) {
                    break;
                } else {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }

            // print result
            int expected = TIMES * THREADS;
            if (map.get("key") != expected) {
                System.out.println("error! expected=" + expected + ", actual=" + map.get("key"));
            }
        }
    }
}

/**
 * output:
 * error! expected=4000, actual=2342
 * error! expected=4000, actual=2816
 * error! expected=4000, actual=3178
 * error! expected=4000, actual=2012
 * error! expected=4000, actual=3246
 * <p>
 * solution 1:
 * Map<String, AtomicInteger> map;
 * map.get("key").getAndAdd(1);
 * or
 * map.get("key").incrementAndGet();
 * <p>
 * solution 2:
 * synchronized (map) {
 * int key = map.get("key") + 1; // step 1
 * map.put("key", key); // step 2
 * }
 */
