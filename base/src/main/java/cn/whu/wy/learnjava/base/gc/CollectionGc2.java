package cn.whu.wy.learnjava.base.gc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 看到这篇文章做的测试：https://www.cnblogs.com/fulongyuanjushi/p/16938497.html
 * 实际上不是作者说的那样。
 * <p>
 * 在IDEA设置JVM参数为-Xmx3m -Xms3m，一直跑到5万多也没oom。日志会持续打印：
 * user55332: Finalize method is called
 * user55346: Finalize method is called
 * 可见map里面的对象是会回收的。
 * 这里的关键在于100ms的sleep，也就是给GC时间。如果不sleep的话，到1500的时候就oom了。
 * <p>
 * 作者说的没回收，除非是还持有了每个返回的map的引用。这是不合理的，每次调用结束后，就没有引用指向这个map的了：
 * <p>
 * Map<Integer, User> map = collectionGc2.getUserMap(i);
 */
public class CollectionGc2 {
    public static void main(String[] args) {
        CollectionGc2 collectionGc2 = new CollectionGc2();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                Map<Integer, User> map = collectionGc2.getUserMap(i);
                map.forEach((k, v) -> {
                });
                if (i % 100 == 0) {
                    // 可以不调用
                    // System.gc();
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (Throwable e) {
                System.out.println("oom, i=" + i);
                e.printStackTrace();
            }

        }
    }

    Map<Integer, User> getUserMap(int startIndex) {
        Map<Integer, User> map = new HashMap<>();
        for (int i = startIndex; i < startIndex + 10; i++) {
            User user = new User("user" + i);
            map.put(i, user);
        }
        return map;
    }
}
